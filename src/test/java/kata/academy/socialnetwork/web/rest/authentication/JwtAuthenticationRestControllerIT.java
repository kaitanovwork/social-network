package kata.academy.socialnetwork.web.rest.authentication;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import kata.academy.socialnetwork.model.dto.request.token.JwtRequestDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JwtAuthenticationRestControllerIT extends SpringSimpleContextTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_SuccessfulTest/AfterTest.sql")
    public void getToken_SuccessfulTest() throws Exception {
        getToken("test@gmail.com", "pass");
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_UsernameNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_UsernameNotFoundTest/AfterTest.sql")
    public void getToken_UsernameNotFoundTest() throws Exception {
        JwtRequestDto dto = new JwtRequestDto("test1@gmail.com", "pass");

        mockMvc.perform(post("/api/v1/authentication/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Логин и/или пароль указаны неверно")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_PasswordNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/authentication/JwtAuthenticationRestController/getToken_PasswordNotFoundTest/AfterTest.sql")
    public void getToken_PasswordNotFoundTest() throws Exception {
        JwtRequestDto dto = new JwtRequestDto("test@gmail.com", "pass1");

        mockMvc.perform(post("/api/v1/authentication/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Логин и/или пароль указаны неверно")));
    }
}
