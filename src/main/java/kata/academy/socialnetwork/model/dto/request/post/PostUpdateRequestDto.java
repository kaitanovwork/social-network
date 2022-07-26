package kata.academy.socialnetwork.model.dto.request.post;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record PostUpdateRequestDto(
        Long id,
        @NotBlank String title,
        @NotBlank String text,
        List<String> tags
) {
}
