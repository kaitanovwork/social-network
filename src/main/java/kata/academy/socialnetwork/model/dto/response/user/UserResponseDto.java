package kata.academy.socialnetwork.model.dto.response.user;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName) {
}
