package kata.academy.socialnetwork.model.dto.request.token;

import javax.validation.constraints.NotBlank;

public record JwtRequestDto(
        @NotBlank String username,
        @NotBlank String password) {
}
