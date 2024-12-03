package com.example.ispconnect.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ispconnect.R;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView newsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Initialize WebView
        newsWebView = findViewById(R.id.newsWebView);

        // Get the URL passed from the NewsAdapter
        String link = getIntent().getStringExtra("link");

        // Configure WebView settings
        WebSettings webSettings = newsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the URL in WebView
        newsWebView.setWebViewClient(new WebViewClient()); // Prevents opening the link in an external browser
        newsWebView.loadUrl(link);

        // Back Button to Home Page
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        if (newsWebView.canGoBack()) {
            newsWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
