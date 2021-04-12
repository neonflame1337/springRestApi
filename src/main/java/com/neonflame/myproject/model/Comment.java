package com.neonflame.myproject.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseContentEntity {

    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Post post;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
