package kata.academy.socialnetwork.web.rest.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.dto.request.token.JwtRequestDto;
import kata.academy.socialnetwork.model.dto.response.token.JwtResponseDto;
import kata.academy.socialnetwork.web.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "JwtAuthenticationRestController", description = "Аутентификация пользователя в системе")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authentication")
public class JwtAuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenUtil;

    @Operation(summary = "Получение токена")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Токен успешно получен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибку в запросе")
    })
    @PostMapping("/token")
    public Response<JwtResponseDto> getToken(@RequestBody @Valid JwtRequestDto dto) {
        authenticate(dto.username(), dto.password());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.username());
        return Response.ok(new JwtResponseDto(jwtTokenUtil.generateToken(userDetails)));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
