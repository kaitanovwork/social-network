package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.repository.abst.entity.PostRepository;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends AbstractServiceImpl<Post, Long> implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
