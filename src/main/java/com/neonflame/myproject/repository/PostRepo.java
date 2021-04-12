package com.neonflame.myproject.repository;

import com.neonflame.myproject.model.Post;
import com.neonflame.myproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findAllByUser(User user);

    public Post findById(long id);
}
