package com.neonflame.myproject.dto;

import com.neonflame.myproject.model.Comment;
import com.neonflame.myproject.model.Post;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PostExtDto extends PostDto {

    List<CommentDto> commentsDto;

    public PostExtDto(Post post) {
        super(post);
        this.commentsDto = new LinkedList<>();
        this.commentsDto = mapCommentsToCommentsDto(post.getComments());
    }

    private List<CommentDto> mapCommentsToCommentsDto (List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsDto() {
        return commentsDto;
    }

    public void setCommentsDto(List<CommentDto> commentsDto) {
        this.commentsDto = commentsDto;
    }
}
