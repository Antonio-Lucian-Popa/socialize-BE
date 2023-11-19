package com.asusoftware.socialapp.post.model.dto;

import com.asusoftware.socialapp.post.model.Comment;
import com.asusoftware.socialapp.user.model.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CommentDto {
    private UUID id;
    private String value;
    private LocalDateTime createdAt;
    private UUID postId;
    private UserDto user;
    private UUID parentCommentId;

    public static CommentDto fromEntity(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setValue(comment.getValue());
        commentDto.setCreatedAt(comment.getCreatedAt());
      //  commentDto.setSubcomments(fromEntityList(comment.getSubcomments()));
        return commentDto;
    }

    public static List<CommentDto> fromEntityList(List<Comment> comments) {
        return comments.stream().map(CommentDto::fromEntity).toList();
    }
}
