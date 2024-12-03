package com.example.ispconnect.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ispconnect.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);

        // Button to Call
        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:18889822268"));
            startActivity(intent);
        });

        // Button to Email
        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:help@cna.nl.ca"));
            startActivity(intent);
        });

        // Button to Website
        Button btnWebsite = findViewById(R.id.btnWebsite);
        btnWebsite.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cna.nl.ca"));
            startActivity(intent);
        });

        // Create MapView
        mapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = savedInstanceState != null ? savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY) : null;
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        // Back Button to Home Page
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(47.58723573054395, -52.73456177776287); // Coordinates for ISP
        googleMap.addMarker(new MarkerOptions().position(location).title("Institute of the South Pacific"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }
}
