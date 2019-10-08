package com.example.capstonmaster.dto;

import java.io.Serializable;
import java.util.Date;

public class List_item implements Serializable {
    private String nickname;
    private String title;
    private Date write_date;
    private String content;

    public List_item(String nickname, String title, Date write_date, String content) {
        this.nickname = nickname;
        this.title = title;
        this.write_date = write_date;
        this.content = content;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
