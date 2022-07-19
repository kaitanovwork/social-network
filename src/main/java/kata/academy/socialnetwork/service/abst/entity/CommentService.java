package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.Comment;
import kata.academy.socialnetwork.model.entity.CommentLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService extends AbstractService<Comment, Long>{

    Page<Comment> findAll(Pageable pageable);
}
