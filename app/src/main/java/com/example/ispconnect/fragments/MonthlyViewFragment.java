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

public class MonthlyViewFragment extends Fragment {
    private static final String TAG = "MonthlyViewFragment";
    private RecyclerView recyclerView;
    private EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_view, container, false);

        recyclerView = view.findViewById(R.id.monthlyEventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "RecyclerView initialized.");

        // Fetch events from the database
        CalendarDatabase dbHelper = new CalendarDatabase(getContext());
        List<Event> allEvents = dbHelper.getEvents();
        Log.d(TAG, "Total events fetched from database: " + allEvents.size());

        // Filter events for the current month
        List<Event> monthlyEvents = filterEventsForCurrentMonth(allEvents);
        Log.d(TAG, "Filtered monthly events: " + monthlyEvents.size());

        // Add placeholder if no events are found
        if (monthlyEvents.isEmpty()) {
            Log.d(TAG, "No events found for the current month. Adding placeholder.");
            monthlyEvents.add(new Event(0, "No Events", "N/A", "", "No events scheduled for this month."));
        }

        // Set up RecyclerView with the adapter
        adapter = new EventAdapter(monthlyEvents);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "Adapter set with monthly events.");

        return view;
    }

    private List<Event> filterEventsForCurrentMonth(List<Event> events) {
        List<Event> monthlyEvents = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        String currentMonth = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
        Log.d(TAG, "Current month: " + currentMonth);

        for (Event event : events) {
            Log.d(TAG, "Checking event: " + event.getTitle() + " with date " + event.getDate());
            if (event.getDate().startsWith(currentMonth)) {
                Log.d(TAG, "Event matches current month: " + event.getTitle());
                monthlyEvents.add(event);
            }
        }
        return monthlyEvents;
    }
}
