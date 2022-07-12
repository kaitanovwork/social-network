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
        UserInfo userInfo = new UserInfo();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        userInfo.setFirstName(dto.firstName());
        userInfo.setLastName(dto.lastName());
        userInfo.setCity(dto.city());
        userInfo.setAge(dto.age());
        userInfo.setGender(dto.gender());
        user.setUserInfo(userInfo);
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
        UserInfo userInfo = user.getUserInfo();
        userInfo.setFirstName(dto.firstName());
        userInfo.setLastName(dto.lastName());
        userInfo.setCity(dto.city());
        userInfo.setAge(dto.age());
        userInfo.setGender(dto.gender());
        user.setUserInfo(userInfo);
        return user;
    }

    public static User updatePassword(User user, UserUpdatePasswordRequestDto dto) {
        user.setPassword(dto.password());
        return user;
    }
}
