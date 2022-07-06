package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdatePasswordRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdateRequestDto;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "UserRestController", description = "Контроллер для редактирования пользователем своего профиля")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "Эндпоинт для обновления профиля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Профиль пользователя успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping
    public Response<UserResponseDto> updateUser(@RequestBody UserUpdateRequestDto dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Response.ok(UserMapper.toDto(userService.update(UserMapper.updateUser(user, dto))));
    }

    @Operation(summary = "Эндпоинт для обновления пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль пользователя успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping("/password")
    public Response<UserResponseDto> updateUserPassword(@RequestBody @Valid UserUpdatePasswordRequestDto dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Response.ok(UserMapper.toDto(userService.updatePassword(UserMapper.updatePassword(user, dto))));
    }
}
