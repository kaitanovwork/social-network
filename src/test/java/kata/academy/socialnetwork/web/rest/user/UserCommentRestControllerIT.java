package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserCommentRestControllerIT extends SpringSimpleContextTest {
    @Value("${jwt.header}")
    private String header;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_PostNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_PostNotFound/AfterTest.sql")
    public void getCommentPage_PostNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/user/comments/post/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(header, getToken("test@gmail.com", "pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_PostExist/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_PostExist/AfterTest.sql")
    public void getCommentPage_PostExist() throws Exception {
        mockMvc.perform(get("/api/v1/user/comments/post/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(header, getToken("test@gmail.com", "pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data", IsNull.notNullValue()));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_CommentsNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_CommentsNotFound/AfterTest.sql")
    public void getCommentPage_CommentsNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/user/comments/post/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(header, getToken("test@gmail.com", "pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.content", empty()));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_CommentsExist/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserCommentRestController/getCommentPage_CommentsExist/AfterTest.sql")
    public void getCommentPage_CommentsExist() throws Exception {
        mockMvc.perform(get("/api/v1/user/comments/post/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(header, getToken("test@gmail.com", "pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.data.content[0].text", Is.is("my test comment")));
    }
}
