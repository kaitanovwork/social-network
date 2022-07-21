package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive);

    Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);

    @Query("""
            SELECT CASE WHEN COUNT(cl) > 0 THEN true ELSE false END
            FROM CommentLike cl
            WHERE cl.comment.id = :commentId AND cl.user.id = :userId
            """)
    boolean existsByCommentIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
