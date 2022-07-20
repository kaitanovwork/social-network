package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;


public interface CommentLikeService  extends AbstractService<CommentLike, Long>  {
    Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive);
    CommentLike findByCommentIdAndUserId(Long commentId, Long userId);
}
