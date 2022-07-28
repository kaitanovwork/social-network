package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdatePasswordRequestDto;
import kata.academy.socialnetwork.model.dto.request.user.UserUpdateRequestDto;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.enums.Gender;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerIT extends SpringSimpleContextTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUser_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUser_SuccessfulTest/AfterTest.sql")
    public void updateUser_SuccessfulTest() throws Exception {
        UserUpdateRequestDto dto = new UserUpdateRequestDto(
                "NewFirstName",
                "LastName",
                "My city",
                33,
                Gender.FEMALE
        );

        String token = getToken("test", "test");
        mockMvc.perform(put("/api/v1/user")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName", Is.is(dto.firstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName", Is.is(dto.lastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.age", Is.is(dto.age())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.city", Is.is(dto.city())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender", Is.is(dto.gender().name())));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_SuccessfulTest/AfterTest.sql")
    public void updateUserPassword_SuccessfulTest() throws Exception {
        User existUser = entityManager.find(User.class, 101L);

        UserUpdatePasswordRequestDto dto = new UserUpdatePasswordRequestDto("12345");
        String token = getToken("test", "test");
        mockMvc.perform(put("/api/v1/user/password")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(existUser.getId()));

        existUser = entityManager.find(User.class, 101L);
        assertTrue(passwordEncoder.matches(dto.password(), existUser.getPassword()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_PasswordIsEmptyTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_PasswordIsEmptyTest/AfterTest.sql")
    public void updateUserPassword_PasswordIsEmptyTest(String password) throws Exception {
        UserUpdatePasswordRequestDto dto = new UserUpdatePasswordRequestDto(password);
        String token = getToken("test", "test");
        mockMvc.perform(put("/api/v1/user/password")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [password]")));
    }
}