package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MentoResponseVO implements Serializable {
    private String content;
    private LocalDateTime endTime;
    private MentoInfo introduce;
    private LocalDateTime startTime;
    private String title;
    private long id;
    private Author mento;
    private Author[] mentees;
    private Boolean use;

    public MentoResponseVO() {
    }

    public MentoResponseVO(String content, LocalDateTime endTime, MentoInfo introduce, LocalDateTime startTime, String title, long id, Author mento, Author[] mentees, Boolean use) {
        this.content = content;
        this.endTime = endTime;
        this.introduce = introduce;
        this.startTime = startTime;
        this.title = title;
        this.id = id;
        this.mento = mento;
        this.mentees = mentees;
        this.use = use;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public MentoInfo getIntroduce() {
        return introduce;
    }

    public void setIntroduce(MentoInfo introduce) {
        this.introduce = introduce;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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

    public Boolean getUse() {
        return use;
    }

    public void setUse(Boolean use) {
        this.use = use;
    }
}