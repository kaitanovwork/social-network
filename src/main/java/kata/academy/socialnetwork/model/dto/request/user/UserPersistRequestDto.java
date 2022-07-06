package kata.academy.socialnetwork.model.dto.request.user;

import javax.validation.constraints.NotBlank;

public record UserPersistRequestDto(
        @NotBlank String email,
        @NotBlank String password,
        String firstName,
        String lastName) {
}
