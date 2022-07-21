package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.PostLike;

import java.util.Optional;

public interface PostLikeService extends AbstractService<PostLike, Long> {

    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    boolean existsByPostIdAndUserId(Long postId, Long userId);
}
