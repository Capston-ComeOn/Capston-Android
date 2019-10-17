package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {
    private String title;
    private String contents;
    private long id;
    private Author author;

    public Board(String title, String contents, long id, Author author) {
        this.title = title;
        this.contents = contents;
        this.id = id;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
