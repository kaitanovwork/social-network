package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
