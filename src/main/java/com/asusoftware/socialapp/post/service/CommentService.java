package com.asusoftware.socialapp.post.service;

import com.asusoftware.socialapp.post.exception.CommentNotFoundException;
import com.asusoftware.socialapp.post.exception.PostNotFoundException;
import com.asusoftware.socialapp.post.model.Comment;
import com.asusoftware.socialapp.post.model.Post;
import com.asusoftware.socialapp.post.model.dto.CommentDto;
import com.asusoftware.socialapp.post.model.dto.CreateCommentDto;
import com.asusoftware.socialapp.post.repository.CommentRepository;
import com.asusoftware.socialapp.user.model.User;
import com.asusoftware.socialapp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
   // private final PostRepository postRepository;
    private final PostService postService;
    private final UserService userService;

    /**
     * Create a comment
     * @param postId
     * @param commentDto
     * @return CommentDto
     */
    public CommentDto createComment(UUID postId,UUID userId, CreateCommentDto commentDto) {
        Post post = postService.findById(postId);
        User user = userService.findById(userId);
        Comment comment = commentDto.toEntity(commentDto);
        comment.setUser(user);
        comment.setPost(post);
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    /**
     * Get a comment by id
     * @param commentId
     * @return CommentDto
     */
    public CommentDto getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found with id: " + commentId));
        return CommentDto.fromEntity(comment);
    }

    /**
     * Delete a comment by id
     * @param commentId
     */
    public void deleteComment(UUID commentId, UUID userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found with id: " + commentId));
        if(!comment.getUser().getId().equals(userId)) {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        } else {
            commentRepository.delete(comment);
        }
    }

    /**
     * Update a comment by id
     * @param commentId
     * @param updatedComment
     * @return CommentDto
     */
    public CommentDto updateComment(UUID commentId, UUID userId, CreateCommentDto updatedComment) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException("Comment not found with id: " + commentId));
        if(!comment.getUser().getId().equals(userId)) {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        } else {
            comment.setValue(updatedComment.getValue());
        }

        comment.setValue(updatedComment.getValue());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    /**
     * Get all comments by post id
     * @param postId
     * @return List<CommentDto>
     */
    public List<CommentDto> getCommentsByPostId(UUID postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return CommentDto.fromEntityList(comments);
    }

    // Other methods
}

