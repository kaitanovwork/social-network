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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerIT extends SpringSimpleContextTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUser_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUser_SuccessfulTest/AfterTest.sql")
    void updateUser_SuccessfulTest() throws Exception {
        var request = new UserUpdateRequestDto(
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
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is(200)))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.firstName", Is.is(request.firstName())))
                .andExpect(jsonPath("$.data.lastName", Is.is(request.lastName())))
                .andExpect(jsonPath("$.data.age", Is.is(request.age())))
                .andExpect(jsonPath("$.data.city", Is.is(request.city())))
                .andExpect(jsonPath("$.data.gender", Is.is(request.gender().name())));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_SuccessfulTest/AfterTest.sql")
    void updateUserPassword_SuccessfulTest() throws Exception {
        var existUser = entityManager.find(User.class, 101L);

        var request = new UserUpdatePasswordRequestDto("12345");
        String token = getToken("test", "test");
        mockMvc.perform(put("/api/v1/user/password")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is(200)))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(existUser.getId()));

        existUser = entityManager.find(User.class, 101L);
        assertTrue(passwordEncoder.matches(request.password(), existUser.getPassword()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_PasswordIsEmptyTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRestController/updateUserPassword_PasswordIsEmptyTest/AfterTest.sql")
    void updateUserPassword_PasswordIsEmptyTest(String password) throws Exception {
        var request = new UserUpdatePasswordRequestDto(password);
        String token = getToken("test", "test");
        mockMvc.perform(put("/api/v1/user/password")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is(400)))
                .andExpect(jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [password]")));
    }
}