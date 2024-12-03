package com.example.ispconnect.activities;

import android.os.Bundle;

import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ispconnect.adapters.NewsAdapter;
import com.example.ispconnect.utils.NewsItem;
import com.example.ispconnect.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNews;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsItem> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        recyclerViewNews = findViewById(R.id.recyclerViewNews);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(this));

        newsList = new ArrayList<>();
        fetchNewsFeed();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back Button to Home Page
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void fetchNewsFeed() {
        new Thread(() -> {
            try {
                URL url = new URL("https://www.cna.nl.ca/rss/news-feed.aspx"); // RSS Formatted News Feed
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(inputStream, null);

                String tag = "";
                String title = "";
                String description = "";
                String link = "";
                String imageUrl = "";

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    tag = parser.getName();

                    if (eventType == XmlPullParser.START_TAG) {
                        if (tag.equalsIgnoreCase("title")) {
                            title = parser.nextText();
                        } else if (tag.equalsIgnoreCase("description")) {
                            description = parser.nextText();
                        } else if (tag.equalsIgnoreCase("link")) {
                            link = parser.nextText();
                        } else if (tag.equalsIgnoreCase("image")) {
                            int imgStart = parser.nextText().indexOf("http");
                            if (imgStart != -1) {
                                imageUrl = parser.nextText().substring(imgStart);
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && tag.equalsIgnoreCase("item")) {
                        newsList.add(new NewsItem(title, description, link, imageUrl));
                        title = "";
                        description = "";
                        link = "";
                    }
                    eventType = parser.next();
                }

                runOnUiThread(() -> {
                    if (!newsList.isEmpty()) {
                        newsAdapter = new NewsAdapter(newsList, NewsActivity.this);
                        recyclerViewNews.setAdapter(newsAdapter);
                    } else {
                        showErrorPrompt("No news feed available.");
                    }
                });

            } catch (Exception e) {
                runOnUiThread(() -> showErrorPrompt("Failed to load news. Please check your internet connection."));
                e.printStackTrace();
            }
        }).start();
    }
    private void showErrorPrompt(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

}