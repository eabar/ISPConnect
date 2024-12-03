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

public class WeeklyViewFragment extends Fragment {
    private static final String TAG = "WeeklyViewFragment";
    private RecyclerView recyclerView;
    private EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_view, container, false);

        recyclerView = view.findViewById(R.id.weeklyEventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "RecyclerView initialized.");

        // Fetch events from the database
        CalendarDatabase dbHelper = new CalendarDatabase(getContext());
        List<Event> allEvents = dbHelper.getEvents();
        Log.d(TAG, "Total events fetched from database: " + allEvents.size());

        // Filter events for the current week
        List<Event> weeklyEvents = filterEventsForCurrentWeek(allEvents);
        Log.d(TAG, "Filtered weekly events: " + weeklyEvents.size());

        // Add placeholder if no events are found
        if (weeklyEvents.isEmpty()) {
            Log.d(TAG, "No events found for the current week. Adding placeholder.");
            weeklyEvents.add(new Event(0, "No Events", "N/A", "", "No events scheduled for this week."));
        }

        // Set up RecyclerView with the adapter
        adapter = new EventAdapter(weeklyEvents);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "Adapter set with weekly events.");

        return view;
    }

    private List<Event> filterEventsForCurrentWeek(List<Event> events) {
        List<Event> weeklyEvents = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        // Get start and end dates of the current week
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        String startOfWeek = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Log.d(TAG, "Start of week: " + startOfWeek);

        calendar.add(Calendar.DAY_OF_WEEK, 6);
        String endOfWeek = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Log.d(TAG, "End of week: " + endOfWeek);

        // Check if events fall within the week range
        for (Event event : events) {
            Log.d(TAG, "Checking event: " + event.getTitle() + " with date " + event.getDate());
            if (event.getDate().compareTo(startOfWeek) >= 0 && event.getDate().compareTo(endOfWeek) <= 0) {
                Log.d(TAG, "Event matches current week: " + event.getTitle());
                weeklyEvents.add(event);
            }
        }
        return weeklyEvents;
    }
}
