package com.example.moduleb1.Models;

public enum TicketType {
    VIP("VIP", "Event 1"),
    REGULAR("Regular", "Event 2"),
    STUDENT("Student", "Event 3");

    private final String displayName;
    private final String eventTitle;

    TicketType(String displayName, String eventTitle) {
        this.displayName = displayName;
        this.eventTitle = eventTitle;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public static TicketType fromString(String text) {
        for (TicketType type : TicketType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
