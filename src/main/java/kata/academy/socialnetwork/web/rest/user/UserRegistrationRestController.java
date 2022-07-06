package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import kata.academy.socialnetwork.web.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "UserRegistrationRestController", description = "Контроллер для регистрации нового пользователя")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/registration")
public class UserRegistrationRestController {

    private final UserService userService;

    @Operation(summary = "Эндпоинт для регистрации нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый пользователь успешно зарегестрирован"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PostMapping
    public Response<UserResponseDto> registerNewUser(@RequestBody @Valid UserPersistRequestDto dto) {
        ApiValidationUtil.requireFalse(userService.existsByEmail(dto.email()), "Email is being used by another user");
        return Response.ok(UserMapper.toDto(userService.save(UserMapper.toEntity(dto))));
    }
}
