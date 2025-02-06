package com.example.moduleb1.Models;

public enum TicketType {
    EVENT_0000("WorldSkills Competition 2022 Special Edition", "VIP"),
    EVENT_0001("A unique format for international skill competitions", "Regular"),
    EVENT_0002("Thanks to partners", "Student"),
    EVENT_0003("The countries and regions hosting skill competitions", "VIP"),
    EVENT_0004("A unforgettable Competition", "Regular"),
    EVENT_0005("Competition will be held in Bordeaux, France", "Student"),
    EVENT_0006("Competition will be held in Goyang, Korea", "VIP"),
    EVENT_0007("WorldSkills history", "Regular"),
    EVENT_0008("Competition will be held in Salzburg, Austria", "Student"),
    EVENT_0009("Sponsorship and Partnership", "VIP");

    private final String eventTitle;
    private final String ticketType;

    TicketType(String eventTitle, String ticketType) {
        this.eventTitle = eventTitle;
        this.ticketType = ticketType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getTicketType() {
        return ticketType;
    }

    public static TicketType fromEventTitle(String title) {
        for (TicketType type : TicketType.values()) {
            if (type.eventTitle.trim().equalsIgnoreCase(title.trim())) { // Убираем лишние пробелы и учитываем регистр
                return type;
            }
        }
        return null;
    }
}
