package kata.academy.socialnetwork.model.dto.response.post;

import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.Post;

import java.util.List;

public record PostResponseDto(
        Long id,
        String title,
        String text,
        List<String> tags,
        UserResponseDto userResponseDto) {

    public PostResponseDto(Post post) {
        this(post.getId(), post.getTitle(), post.getText(), post.getTags(), UserMapper.toDto(post.getUser()));
    }
}
