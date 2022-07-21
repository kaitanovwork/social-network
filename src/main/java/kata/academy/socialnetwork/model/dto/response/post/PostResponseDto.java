package kata.academy.socialnetwork.model.dto.response.post;

import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;

import java.util.List;

public record PostResponseDto(
        Long id,
        String title,
        String text,
        List<String> tags,
        UserResponseDto userResponseDto) {
    public PostResponseDto(Long id, String title, String text, List<String> tags, User user) {
        this(id, title, text, tags, UserMapper.toDto(user));
    }
}
