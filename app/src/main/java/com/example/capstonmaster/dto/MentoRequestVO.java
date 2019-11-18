package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class MentoRequestVO implements Serializable {
    private String content;
    private LocalDateTime endTime;
    private MentoInfo introduceRequestDto;
    private LocalDateTime startTime;
    private String title;
    private long id;
    private Author mento;
    private Author[] mentees;
    private Boolean use;


    public MentoRequestVO(String content, LocalDateTime endTime, MentoInfo introduceRequestDto, LocalDateTime startTime, String title, long id, Author mento, Author[] mentees, Boolean use) {
        this.content = content;
        this.endTime = endTime;
        this.introduceRequestDto = introduceRequestDto;
        this.startTime = startTime;
        this.title = title;
        this.id = id;
        this.mento = mento;
        this.mentees = mentees;
        this.use = use;
    }
    public MentoRequestVO(String content, LocalDateTime endTime, MentoInfo introduceRequestDto, LocalDateTime startTime, String title) {
        this.content = content;
        this.endTime = endTime;
        this.introduceRequestDto = introduceRequestDto;
        this.startTime = startTime;
        this.title = title;
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

    public MentoInfo getIntroduceRequestDto() {
        return introduceRequestDto;
    }

    public void setIntroduceRequestDto(MentoInfo introduceRequestDto) {
        this.introduceRequestDto = introduceRequestDto;
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