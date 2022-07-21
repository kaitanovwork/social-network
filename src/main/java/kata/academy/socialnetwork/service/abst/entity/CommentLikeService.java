package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;

import java.util.Optional;

public interface CommentLikeService extends AbstractService<CommentLike, Long> {

    Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive);

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);

    boolean existsByCommentIdAndUserId(Long commentId, Long userId);
}
