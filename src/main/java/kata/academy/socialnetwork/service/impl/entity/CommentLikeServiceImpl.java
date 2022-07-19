package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;
import kata.academy.socialnetwork.repository.abst.entity.CommentLikeRepository;
import kata.academy.socialnetwork.service.abst.entity.CommentLikeService;

public class CommentLikeServiceImpl extends AbstractServiceImpl<CommentLike, Long> implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository) {
        super(commentLikeRepository);
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive) {
        return commentLikeRepository.countCommentLikesByIdAndPositive(commentId, positive);
    }

    @Override
    public boolean existsByCommentIdAndUserId(Long commentId, Long userId) {
        return commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
    }
}
