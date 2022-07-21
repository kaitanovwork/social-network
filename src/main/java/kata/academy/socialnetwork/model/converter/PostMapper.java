package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.request.post.PostPersistRequestDto;
import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.User;

public final class PostMapper {

    private PostMapper(){
    }

    public static PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getText(),
                post.getTags(),
                UserMapper.toDto(post.getUser())
        );
    }

    public static Post toEntity(PostPersistRequestDto dto, User user) {
        Post post = new Post();
        post.setTitle(dto.title());
        post.setText(dto.text());
        post.setTags(dto.tags());
        post.setUser(user);
        return post;
    }
}
