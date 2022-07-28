package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import kata.academy.socialnetwork.model.dto.request.user.UserPersistRequestDto;
import org.hamcrest.core.Is;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static kata.academy.socialnetwork.model.enums.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserRegistrationRestControllerIT extends SpringSimpleContextTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_SuccessfulTest/AfterTest.sql")
    public void registerNewUser_SuccessfulTest() throws Exception {

        UserPersistRequestDto dto = new UserPersistRequestDto(
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
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email", Is.is(dto.email())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName", Is.is(dto.firstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName", Is.is(dto.lastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.city", Is.is(dto.city())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.age", Is.is(dto.age())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender", Is.is(dto.gender().name())));

        var isUserExists = entityManager.createQuery(
                "SELECT " +
                        "CASE WHEN COUNT(u.id) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM User as u WHERE u.email = :email",
                        Boolean.class
                )
                .setParameter("email", dto.email())
                .getSingleResult();

        assertTrue(isUserExists);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_WithExistingUsernameTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_WithExistingUsernameTest/AfterTest.sql")
    public void registerNewUser_WithExistingUsernameTest() throws Exception {
        UserPersistRequestDto dto = new UserPersistRequestDto(
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
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Email is being used by another user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_EmailAndPasswordIsEmptyTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserRegistrationRestController/registerNewUser_EmailAndPasswordIsEmptyTest/AfterTest.sql")
    public void registerNewUser_EmailAndPasswordIsEmptyTest(String value) throws Exception {
        UserPersistRequestDto dto = new UserPersistRequestDto(
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
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(StringContains.containsString("В теле запроса допущены ошибки в следующих полях:")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
    }
}