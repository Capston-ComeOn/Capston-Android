package com.example.capstonmaster.dto;

public class MessageVO {
    String content,created;
    long id;
    Author from,to;

    public MessageVO(long id, Author from, Author to,String content, String created) {
        this.content = content;
        this.created = created;
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public MessageVO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getFrom() {
        return from;
    }

    public void setFrom(Author from) {
        this.from = from;
    }

    public Author getTo() {
        return to;
    }

    public void setTo(Author to) {
        this.to = to;
    }
}
