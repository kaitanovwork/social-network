package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService extends AbstractService<Post, Long> {

    Page<Post> findAll(Pageable pageable);
}
