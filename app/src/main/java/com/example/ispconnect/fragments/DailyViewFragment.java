package com.example.ispconnect.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ispconnect.R;
import com.example.ispconnect.adapters.EventAdapter;
import com.example.ispconnect.utils.CalendarDatabase;
import com.example.ispconnect.utils.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyViewFragment extends Fragment {
    private static final String TAG = "DailyViewFragment";
    private RecyclerView recyclerView;
    private EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_view, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.dailyEventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "RecyclerView initialized.");

        // Fetch events from the database
        CalendarDatabase dbHelper = new CalendarDatabase(getContext());
        List<Event> allEvents = dbHelper.getEvents();
        Log.d(TAG, "Total events fetched from database: " + allEvents.size());

        // Log all fetched events
        for (Event event : allEvents) {
            Log.d(TAG, "Fetched Event: " + event.getTitle() + " on " + event.getDate());
        }

        // Filter events for today
        List<Event> dailyEvents = filterEventsForToday(allEvents);
        Log.d(TAG, "Filtered daily events: " + dailyEvents.size());

        // Add placeholder if no events are found
        if (dailyEvents.isEmpty()) {
            Log.d(TAG, "No events found for today. Adding placeholder.");
            dailyEvents.add(new Event(0, "No Events", "N/A", "", "There are no events scheduled for today."));
        }

        // Log filtered events
        for (Event event : dailyEvents) {
            Log.d(TAG, "Daily Event: " + event.getTitle() + " on " + event.getDate());
        }

        // Set up RecyclerView with the adapter
        adapter = new EventAdapter(dailyEvents);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "Adapter set with daily events.");

        return view;
    }

    private List<Event> filterEventsForToday(List<Event> events) {
        List<Event> dailyEvents = new ArrayList<>();
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Log.d(TAG, "Today's date: " + todayDate);

        // Iterate through events and check for matches
        for (Event event : events) {
            Log.d(TAG, "Checking event: " + event.getTitle() + " with date " + event.getDate());
            if (event.getDate().equals(todayDate)) {
                Log.d(TAG, "Event matches today's date: " + event.getTitle());
                dailyEvents.add(event);
            }
        }
        return dailyEvents;
    }
}
