package kata.academy.socialnetwork.web.rest.user;


import kata.academy.socialnetwork.SpringSimpleContextTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserNotificationRestControllerIT extends SpringSimpleContextTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulTrueValueTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulTrueValueTest/AfterTest.sql")
    public void getNotificationPage_SuccessfulTrueValueTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/notifications?isViewed=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].text", Is.is("notification text of true")));


    }


    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulFalseValueTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulFalseValueTest/AfterTest.sql")
    @Test
    public void getNotificationPage_SuccessfulFalseValueTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/notifications?isViewed=false")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].text", Is.is("notification text of false")));
    }

    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulEmptyValueTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserNotificationRestController/getNotificationPage_SuccessfulEmptyValueTest/AfterTest.sql")
    @Test
    public void getNotificationPage_SuccessfulEmptyValueTest() throws Exception{
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/notifications")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[0].text", Is.is("notification text of true")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content[1].text", Is.is("notification text of false")));

    }





}
