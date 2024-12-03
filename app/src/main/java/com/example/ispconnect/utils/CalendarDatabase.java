package com.example.ispconnect.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CalendarDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calendar.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "personal_events";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EVENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TIME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT);";

    public CalendarDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    // Insert an event into the database
    public void insertEvent(String title, String date, String time, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    // Retrieve all events from the database
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Event event = new Event();
            event.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            event.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
            event.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            event.setTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
            event.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
            events.add(event);
        }
        cursor.close();
        db.close();
        return events;
    }

}
