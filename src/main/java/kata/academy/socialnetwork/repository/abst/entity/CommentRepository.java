package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
