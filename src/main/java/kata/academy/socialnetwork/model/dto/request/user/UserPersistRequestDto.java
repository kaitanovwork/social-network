package kata.academy.socialnetwork.model.dto.request.user;

import kata.academy.socialnetwork.model.enums.Gender;

import javax.validation.constraints.NotBlank;

public record UserPersistRequestDto(
        @NotBlank String email,
        @NotBlank String password,
        String firstName,
        String lastName,
        String city,
        Integer age,
        Gender gender) {
}
