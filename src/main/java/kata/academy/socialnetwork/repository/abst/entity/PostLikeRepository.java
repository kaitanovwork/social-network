package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);
}
