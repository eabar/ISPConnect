package com.example.ispconnect.utils;

public class Event {
    private int id;
    private String title;
    private String date;
    private String time;
    private String description;

    // Default Constructor
    public Event() {}

    // Constructor with parameters
    public Event(int id, String title, String date, String time, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
