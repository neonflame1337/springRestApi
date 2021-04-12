package com.neonflame.myproject.dto;

import com.neonflame.myproject.model.Post;

import java.util.Date;

public class PostDto {

    protected long id;
    protected String owner;
    protected Date date;
    protected String title;
    protected String content;
    protected int commentsNumber;

    public PostDto(Post post) {
        this.id = post.getId();
        this.owner = post.getUser().getUsername();
        this.date = post.getDate();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.commentsNumber = post.getComments().size();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }
}
