package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.util.Date;

public class ArticleVO implements Serializable {
    private long id;
    private String title;
    private String contents;
    private long categoryId;
    private Author author;
    public ArticleVO(String title, String contents, long categoryId){
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
    }
    public ArticleVO(long id,String title, String contents, long categoryId, Author author) {
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
        this.author = author;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
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
        return categoryId;
    }

    public void setId(long id) {
        this.categoryId = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
