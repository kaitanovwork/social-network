package kata.academy.socialnetwork.model.dto.response.post;

import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;

import java.util.List;

public record PostResponseDto(
        Long id,
        String title,
        String text,
        List<String> tags,
        UserResponseDto userResponseDto) {
}
