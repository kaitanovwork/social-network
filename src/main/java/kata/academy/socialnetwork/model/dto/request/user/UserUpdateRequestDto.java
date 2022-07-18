package kata.academy.socialnetwork.model.dto.request.user;

import kata.academy.socialnetwork.model.enums.Gender;

public record UserUpdateRequestDto(
        String firstName,
        String lastName,
        String city,
        Integer age,
        Gender gender) {
}
