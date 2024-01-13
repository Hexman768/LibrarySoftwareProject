package com.pedigo.libraryproject;

import java.util.UUID;

public class Book {
    private UUID uuid;
    private String title;
    private String author;
    private String status;
    private String type;

    public Book() {}

    public Book(UUID uuid, String title, String author, String status, String type) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public UUID getUUID() { return uuid; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getStatus() { return status; }

    public String getType() { return type; }
}
