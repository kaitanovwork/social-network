package kata.academy.socialnetwork.model.converter;

import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdatePasswordRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdateRequestDto;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.entity.UserInfo;

public final class UserMapper {

    private UserMapper(){
    }

    public static User toEntity(UserPersistRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUserInfo(new UserInfo(
                dto.firstName(),
                dto.lastName(),
                dto.city(),
                dto.age(),
                dto.gender()
        ));
        return user;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getCity(),
                user.getUserInfo().getAge(),
                user.getUserInfo().getGender()
        );
    }

    public static User updateUser(User user, UserUpdateRequestDto dto) {
        user.setUserInfo(new UserInfo(
                dto.firstName(),
                dto.lastName(),
                dto.city(),
                dto.age(),
                dto.gender()
        ));
        return user;
    }

    public static User updatePassword(User user, UserUpdatePasswordRequestDto dto) {
        user.setPassword(dto.password());
        return user;
    }
}
