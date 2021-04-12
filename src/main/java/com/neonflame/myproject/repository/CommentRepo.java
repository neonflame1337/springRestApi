package com.neonflame.myproject.repository;

import com.neonflame.myproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    public Comment findById(long id);
}
