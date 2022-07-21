package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Integer countCommentLikesByIdAndPositive(Long commentId, Boolean positive);
    Optional <CommentLike> findByCommentIdAndUserId (Long commentId, Long userId);
}
