package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdatePasswordRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdateRequestDto;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;

public final class UserMapper {

    public static User toEntity(UserPersistRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        return user;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static User updateUser(User user, UserUpdateRequestDto dto) {
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        return user;
    }

    public static User updatePassword(User user, UserUpdatePasswordRequestDto dto) {
        user.setPassword(dto.password());
        return user;
    }
}
