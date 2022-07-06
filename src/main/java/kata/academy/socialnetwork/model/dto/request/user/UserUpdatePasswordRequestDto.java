package kata.academy.socialnetwork.model.dto.request.user;

import javax.validation.constraints.NotBlank;

public record UserUpdatePasswordRequestDto(@NotBlank String password) {
}
