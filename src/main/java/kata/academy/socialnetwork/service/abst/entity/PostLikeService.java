package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.PostLike;

public interface PostLikeService extends AbstractService<PostLike, Long> {

    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);

    PostLike findByPostIdAndUserId(Long post, Long user);
}
