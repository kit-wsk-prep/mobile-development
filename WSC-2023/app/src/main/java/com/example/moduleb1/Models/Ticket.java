package com.example.moduleb1.Models;

public class Ticket {
    private String name;
    private String imageUri;

    public Ticket(String name, String imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }
}
