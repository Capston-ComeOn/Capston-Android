package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.util.Date;

public class ArticleVO implements Serializable {
    private long id;
    private String title;
    private String contents;
    private long categoryId;
    private Author author;
    public ArticleVO(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
    }
    public ArticleVO(long id,String title, String contents, long categoryId, Author author) {
        this.id =id;
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
        this.author = author;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public void setId(Long id) {
        this.categoryId = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
