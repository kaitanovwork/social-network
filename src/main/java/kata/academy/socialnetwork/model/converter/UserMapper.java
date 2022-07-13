package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdatePasswordRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdateRequestDto;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.entity.UserInfo;

public final class UserMapper {

    public static User toEntity(UserPersistRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(dto.firstName());
        userInfo.setLastName(dto.lastName());

        user.setUserInfo(userInfo);
        return user;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName()
        );
    }

    public static User updateUser(User user, UserUpdateRequestDto dto) {
        user.getUserInfo().setFirstName(dto.firstName());
        user.getUserInfo().setLastName(dto.lastName());
        return user;
    }

    public static User updatePassword(User user, UserUpdatePasswordRequestDto dto) {
        user.setPassword(dto.password());
        return user;
    }
}
