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
    public Integer countPostLikesByIdAndPositive(Long postId, Boolean positive) {
        return commentLikeRepository.countPostLikesByIdAndPositive(postId, positive);
    }
}
