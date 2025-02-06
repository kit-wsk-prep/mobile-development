package com.example.moduleb1.Models;

import java.util.List;

public class Event {
    private String eventId;
    private String eventTitle;
    private String eventText;
    private boolean eventReadStatus;
    private List<String> eventPictures;

    public Event(String eventId, String eventTitle, String eventText, boolean eventReadStatus, List<String> eventPictures) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventText = eventText;
        this.eventReadStatus = eventReadStatus;
        this.eventPictures = eventPictures;
    }

    // Геттеры и сеттеры
    public String getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventText() {
        return eventText;
    }

    public boolean isEventReadStatus() {
        return eventReadStatus;
    }

    public List<String> getEventPictures() {
        return eventPictures;
    }
}
