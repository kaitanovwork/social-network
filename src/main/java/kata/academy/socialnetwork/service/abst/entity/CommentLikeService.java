package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;


public interface CommentLikeService  extends AbstractService<CommentLike, Long>  {
    Integer countPostLikesByIdAndPositive(Long commId, Boolean positive);
}
