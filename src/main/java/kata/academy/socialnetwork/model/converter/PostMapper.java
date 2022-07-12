package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.response.user.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;

public final class PostMapper {
    public static PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getText(),
                post.getTags(),
                UserMapper.toDto(post.getUser())
        );
    }
}
