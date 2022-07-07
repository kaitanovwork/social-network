package kata.academy.socialnetwork.web.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KalachikovMP
 **/
@Tag(name = "AdminUserRestController", description = "Контроллер для администратора")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminUserRestController {
    private final UserService userService;

    @Operation(summary = "Эндпоинт для получения списка пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успешно выгружен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/user")
    public Response<Page<UserResponseDto>> getAllUser(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return Response.ok(users.map(UserMapper::toDto));
    }
}
