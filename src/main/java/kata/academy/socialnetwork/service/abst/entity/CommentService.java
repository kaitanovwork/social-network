package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.dto.response.comment.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentResponseDto> getCommentPageByPostId(Long postId, Pageable pageable);
}
