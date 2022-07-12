package kata.academy.socialnetwork.model.dto.response.user;

import kata.academy.socialnetwork.model.enums.Gender;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String city,
        Integer age,
        Gender gender) {
}
