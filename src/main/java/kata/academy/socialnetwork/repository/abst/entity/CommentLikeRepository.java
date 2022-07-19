package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive);
    boolean existsByCommentIdAndUserId(Long commentId,Long userId);
}
