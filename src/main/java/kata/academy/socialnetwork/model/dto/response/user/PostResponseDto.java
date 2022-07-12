package kata.academy.socialnetwork.model.dto.response.user;

import java.util.List;

public record PostResponseDto(
        Long id,
        String title,
        String text,
        List<String> tags,
        UserResponseDto userResponseDto) {
}
