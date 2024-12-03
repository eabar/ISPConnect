package com.example.ispconnect.activities;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ispconnect.R;

public class ApplicationInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_information);

        // Back Button to Home Page
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }
}
