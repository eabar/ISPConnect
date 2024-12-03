package com.example.ispconnect.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.ispconnect.adapters.CalendarPagerAdapter;
import com.example.ispconnect.R;
import com.example.ispconnect.utils.CalendarDatabase;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CalendarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        CalendarPagerAdapter adapter = new CalendarPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Tab View for Daily, Weekly, and Monthly Calendar Views
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Daily");
                    break;
                case 1:
                    tab.setText("Weekly");
                    break;
                case 2:
                    tab.setText("Monthly");
                    break;
            }
        }).attach();

        // Back Button to Home Page
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        populateDatabaseWithEvents();
    }


    private void populateDatabaseWithEvents() {
        CalendarDatabase dbHelper = new CalendarDatabase(this);

        // Check for Duplicates then Insert Calendar Event
        if (dbHelper.getEvents().isEmpty()) {
            dbHelper.insertEvent("Online Registration - Fall", "2024-07-29", "08:00", "Online registration begins.");
            dbHelper.insertEvent("Labour Day, College Closed", "2024-09-02", "All Day", "College closed for Labour Day.");
            dbHelper.insertEvent("Fall Semester Begins", "2024-09-03", "09:00", "Fall semester officially begins.");
            dbHelper.insertEvent("Student Services Orientation", "2024-09-03", "11:00", "MS Teams session for Student Services.");
            dbHelper.insertEvent("Classes Begin", "2024-09-04", "08:00", "Classes officially begin.");
            dbHelper.insertEvent("Brightspace Orientation", "2024-09-05", "10:00", "Orientation session for Brightspace.");
            dbHelper.insertEvent("Supplementary Exam Deadline", "2024-09-05", "17:00", "Deadline to apply for a supplementary exam for Intersession 2024.");
            dbHelper.insertEvent("Deferred/Supplementary Exams", "2024-09-09", "09:00", "Deferred/Supplementary Exams for Intersession 2024.");
            dbHelper.insertEvent("PLAR (Challenge) Exams Start", "2024-09-09", "09:00", "PLAR (Challenge) Exams period begins.");
            dbHelper.insertEvent("PLAR/Exemption Deadline", "2024-09-10", "17:00", "PLAR/Exemption/Credit Transfer application deadline.");
            dbHelper.insertEvent("Last Day to Add Courses", "2024-09-17", "17:00", "Last day to add courses.");
            dbHelper.insertEvent("National Day of Truth and Reconciliation", "2024-09-30", "All Day", "College closed for National Day of Truth and Reconciliation.");
            dbHelper.insertEvent("Last Day for Full Refund", "2024-09-30", "17:00", "Last day for a full refund.");
            dbHelper.insertEvent("Last Day to Opt-Out of Health & Dental", "2024-10-01", "17:00", "Deadline to opt out of Health & Dental (Fall Semester).");
            dbHelper.insertEvent("Fall Semester Fees Due", "2024-10-01", "17:00", "Fall Semester fees are due.");
            dbHelper.insertEvent("Exam Registration Due", "2024-10-04", "17:00", "Registration deadline for exams in various programs.");
            dbHelper.insertEvent("Thanksgiving, College Closed", "2024-10-14", "All Day", "College closed for Thanksgiving.");
            dbHelper.insertEvent("Midterm Exams Begin", "2024-10-15", "09:00", "Midterm exam period starts.");
            dbHelper.insertEvent("Deferred Midterm Exams Start", "2024-10-28", "09:00", "Deferred Midterm Exams period begins.");
            dbHelper.insertEvent("Last Day to Drop Courses", "2024-10-29", "17:00", "Last day to drop courses without academic prejudice.");
            dbHelper.insertEvent("Remembrance Day, College Closed", "2024-11-11", "All Day", "College closed for Remembrance Day.");
            dbHelper.insertEvent("Online Registration - Winter", "2024-11-25", "08:00", "Winter Semester registration begins.");
            dbHelper.insertEvent("Final Exams Begin (ECE Programs)", "2024-12-09", "09:00", "Final exams for ECE programs start.");
            dbHelper.insertEvent("Final Exams Begin (All Programs)", "2024-12-10", "09:00", "Final exams for all programs except ECE start.");
            dbHelper.insertEvent("Grades Released", "2024-12-20", "17:00", "Fall Semester grades released.");
            dbHelper.insertEvent("Winter Semester Begins", "2025-01-06", "09:00", "Winter semester officially begins.");
            dbHelper.insertEvent("Winter Break", "2025-02-06", "All Day", "Winter Semester break starts.");
            dbHelper.insertEvent("Midterm Exams (Winter)", "2025-02-17", "09:00", "Winter Semester midterm exams begin.");
            dbHelper.insertEvent("Good Friday, College Closed", "2025-04-18", "All Day", "College closed for Good Friday.");
        } else {
            Log.d("Database", "Database already populated.");
        }
    }


}
