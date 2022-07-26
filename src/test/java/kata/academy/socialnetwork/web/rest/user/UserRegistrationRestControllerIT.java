package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import kata.academy.socialnetwork.model.entity.User;
import org.hamcrest.core.Is;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static kata.academy.socialnetwork.model.enums.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRegistrationRestControllerIT extends SpringSimpleContextTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_SuccessfulTest/AfterTest.sql")
    void registerNewUser_SuccessfulTest() throws Exception {

        var request = new UserPersistRequestDto(
                "mary@gmail.com",
                "123",
                "Mary",
                "Smith",
                "Moscow",
                33,
                FEMALE
        );

        mockMvc.perform(post("/api/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is(200)))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.email", Is.is(request.email())))
                .andExpect(jsonPath("$.data.firstName", Is.is(request.firstName())))
                .andExpect(jsonPath("$.data.lastName", Is.is(request.lastName())))
                .andExpect(jsonPath("$.data.city", Is.is(request.city())))
                .andExpect(jsonPath("$.data.age", Is.is(request.age())))
                .andExpect(jsonPath("$.data.gender", Is.is(request.gender().name())));

        var user = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email", User.class)
                .setParameter("email", request.email())
                .getSingleResult();
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getPassword());
        assertEquals(request.email(), user.getEmail());
        assertEquals(request.firstName(), user.getUserInfo().getFirstName());
        assertEquals(request.lastName(), user.getUserInfo().getLastName());
        assertEquals(request.city(), user.getUserInfo().getCity());
        assertEquals(request.age(), user.getUserInfo().getAge());
        assertEquals(request.gender(), user.getUserInfo().getGender());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_WithExistingUsernameTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_WithExistingUsernameTest/AfterTest.sql")
    void registerNewUser_WithExistingUsernameTest() throws Exception {
        var request = new UserPersistRequestDto(
                "test@gmail.com",
                "123",
                "Mary",
                "Smith",
                "Moscow",
                33,
                FEMALE
        );

        mockMvc.perform(post("/api/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is(400)))
                .andExpect(jsonPath("$.error", Is.is("Email is being used by another user")))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void registerNewUser_EmailAndPasswordIsEmptyTest(String value) throws Exception {
        var request = new UserPersistRequestDto(
                value,
                value,
                "Mary",
                "Smith",
                "Moscow",
                33,
                FEMALE
        );

        mockMvc.perform(post("/api/v1/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is(400)))
                .andExpect(jsonPath("$.error").value(StringContains.containsString("В теле запроса допущены ошибки в следующих полях:")))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}