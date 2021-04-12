package com.neonflame.myproject.dto;

import com.neonflame.myproject.model.Comment;

import java.util.Date;

public class CommentDto {

    protected long id;
    protected String owner;
    protected Date date;
    protected String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.owner = comment.getUser().getUsername();
        this.date = comment.getDate();
        this.content = comment.getContent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
