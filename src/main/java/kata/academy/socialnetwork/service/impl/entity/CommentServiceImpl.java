package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.converter.CommentMapper;
import kata.academy.socialnetwork.model.dto.response.comment.CommentResponseDto;
import kata.academy.socialnetwork.model.entity.Comment;
import kata.academy.socialnetwork.repository.abst.entity.CommentRepository;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl extends AbstractServiceImpl<Comment, Long> implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.getCommentsByPostId(postId, pageable).map(CommentMapper::toDto);
    }
}
