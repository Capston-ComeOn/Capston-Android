package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class MentoVO implements Serializable {
    private String content;
    private Timestamp endTime;
    private MentoInfo introduce;
    private Timestamp startTime;
    private String title;
    private long id;
    private Author mento;
    private Author[] mentees;

    public MentoVO(String content, Timestamp endTime, MentoInfo introduce, Timestamp startTime, String title, long id, Author mento, Author[] mentees) {
        this.content = content;
        this.endTime = endTime;
        this.introduce = introduce;
        this.startTime = startTime;
        this.title = title;
        this.id = id;
        this.mento = mento;
        this.mentees = mentees;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public MentoInfo getIntroduce() {
        return introduce;
    }

    public void setIntroduce(MentoInfo introduce) {
        this.introduce = introduce;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getMento() {
        return mento;
    }

    public void setMento(Author mento) {
        this.mento = mento;
    }

    public Author[] getMentees() {
        return mentees;
    }

    public void setMentees(Author[] mentees) {
        this.mentees = mentees;
    }
}