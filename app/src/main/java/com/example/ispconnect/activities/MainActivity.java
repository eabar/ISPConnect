package com.example.ispconnect.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;

import com.example.ispconnect.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Create Navigation Buttons
        Button buttonPrograms = findViewById(R.id.button_programs);
        Button buttonSchedule = findViewById(R.id.button_schedule);
        Button buttonCalendar = findViewById(R.id.button_calendar);
        Button buttonTransit = findViewById(R.id.button_transit);
        Button buttonNews = findViewById(R.id.button_news);
        Button buttonContacts = findViewById(R.id.button_contacts);
        Button buttonInfo = findViewById(R.id.button_info);

        // Assign Navigation to Buttons
        buttonPrograms.setOnClickListener(view -> openActivity(ProgramsActivity.class));
        buttonSchedule.setOnClickListener(view -> openActivity(ScheduleActivity.class));
        buttonCalendar.setOnClickListener(view -> openActivity(CalendarActivity.class));
        buttonTransit.setOnClickListener(view -> openActivity(PublicTransitInformationActivity.class));
        buttonNews.setOnClickListener(view -> openActivity(NewsActivity.class));
        buttonContacts.setOnClickListener(view -> openActivity(ContactsActivity.class));
        buttonInfo.setOnClickListener(view -> openActivity(ApplicationInformationActivity.class));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}