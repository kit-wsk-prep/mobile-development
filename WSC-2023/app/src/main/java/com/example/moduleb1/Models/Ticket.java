package com.example.moduleb1.Models;

public class Ticket {
    private String name;
    private String imageUri;

    private String eventTitle;

    public Ticket(String name, String imageUri, String eventTitle) {
        this.name = name;
        this.imageUri = imageUri;
        this.eventTitle = eventTitle;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getEventTitle() {
        return eventTitle;
    }
}
