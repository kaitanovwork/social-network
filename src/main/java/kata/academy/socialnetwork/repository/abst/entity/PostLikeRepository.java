package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

    @Query("""
            SELECT CASE WHEN COUNT(pl) > 0 THEN true ELSE false END
            FROM PostLike pl
            WHERE pl.post.id = :postId AND pl.user.id = :userId
            """)
    boolean existsByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);
}
