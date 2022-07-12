package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);
}
