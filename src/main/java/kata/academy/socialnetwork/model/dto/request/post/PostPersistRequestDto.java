package kata.academy.socialnetwork.model.dto.request.post;

import java.util.List;

public record PostPersistRequestDto(
        String title,
        String text,
        List<String> tags) {
}
