package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.PostLike;
import kata.academy.socialnetwork.repository.abst.entity.PostLikeRepository;
import kata.academy.socialnetwork.service.abst.entity.PostLikeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeServiceImpl extends AbstractServiceImpl<PostLike, Long> implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    public PostLikeServiceImpl(PostLikeRepository postLikeRepository) {
        super(postLikeRepository);
        this.postLikeRepository = postLikeRepository;
    }

    @Override
    public Integer countPostLikesByIdAndPositive(Long postId, Boolean positive) {
        return postLikeRepository.countPostLikesByIdAndPositive(postId, positive);
    }

    @Override
    public Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId) {
        return postLikeRepository.findByPostIdAndUserId(postId, userId);
    }

    @Override
    public boolean existsByPostIdAndUserId(Long postId, Long userId) {
        return postLikeRepository.existsByPostIdAndUserId(postId, userId);
    }
}
