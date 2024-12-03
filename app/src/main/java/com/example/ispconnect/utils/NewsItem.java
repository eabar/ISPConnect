package com.example.ispconnect.utils;

public class NewsItem {
    private String title;
    private String description;
    private String link;
    private String imageUrl;

    public NewsItem(String title, String description, String link, String imageUrl) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
