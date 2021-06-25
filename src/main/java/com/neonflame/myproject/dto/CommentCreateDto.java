package com.neonflame.myproject.dto;

public class CommentCreateDto {

    private long postId;
    private String username;
    private String content;

    public CommentCreateDto() {
    }

    public long getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
