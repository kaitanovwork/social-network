package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.PostLike;
import kata.academy.socialnetwork.model.entity.User;

public interface PostLikeService extends AbstractService<PostLike, Long> {

    Integer countPostLikesByIdAndPositive(Long postId, Boolean positive);

    PostLike findByPostAndUser(Post post, User user);
}
