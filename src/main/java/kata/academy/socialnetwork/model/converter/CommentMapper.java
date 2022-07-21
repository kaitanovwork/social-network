package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.response.comment.CommentResponseDto;
import kata.academy.socialnetwork.model.entity.Comment;

public final class CommentMapper {

    private CommentMapper() {
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getText(),
                PostMapper.toDto(comment.getPost()),
                UserMapper.toDto(comment.getUser())
        );
    }
}
