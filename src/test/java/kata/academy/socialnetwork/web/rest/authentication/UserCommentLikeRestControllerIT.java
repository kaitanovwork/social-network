package kata.academy.socialnetwork.web.rest.authentication;

import kata.academy.socialnetwork.SpringSimpleContextTest;



import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserCommentLikeRestControllerIT extends SpringSimpleContextTest {


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_TrueValueSuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_TrueValueSuccessfulTest/AfterTest.sql")
    public void getCommentLikeCount_TrueValueSuccessfulTest() throws Exception {

        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/comment-likes/101/count?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Is.is(1)));

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_FalseValueSuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_FalseValueSuccessfulTest/AfterTest.sql")
    public void getCommentLikeCount_FalseValueSuccessfulTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/comment-likes/101/count?positive=false")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Is.is(1)));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_CommentNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_CommentNotFound/AfterTest.sql")
    public void getCommentLikeCount_CommentNotFound() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(get("/api/v1/user/comment-likes/101/count?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment not found")));
    }


    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_SuccessfulTest/AfterTest.sql")
    @Test
    public void addCommentLike_SuccessfulTest() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(post("/api/v1/user/comment-likes/101?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));

    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_CommentNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_CommentNotFound/AfterTest.sql")
    public void addCommentLike_CommentNotFound() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(post("/api/v1/user/comment-likes/101?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment not found")));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_CommentLikeAlreadyExist/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/addCommentLike_CommentLikeAlreadyExist/AfterTest.sql")
    public void addCommentLike_CommentLikeAlreadyExist() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(post("/api/v1/user/comment-likes/101?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment like already exist")));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_SuccessfulTest/AfterTest.sql")
    public void deleteCommentLike_SuccessfulTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("/api/v1/user/comment-likes/101")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_CommentNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_CommentNotFound/AfterTest.sql")
    public void deleteCommentLike_CommentNotFound() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(delete("/api/v1/user/comment-likes/101")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_CommentLikeDoesNotExist/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/deleteCommentLike_CommentLikeDoesNotExist/AfterTest.sql")
    public void deleteCommentLike_CommentLikeDoesNotExist() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(delete("/api/v1/user/comment-likes/101")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment like does not exist")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/updateCommentLike_SuccessfulTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/updateCommentLike_SuccessfulTest/AfterTest.sql")
    public void updateCommentLike_SuccessfulTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(put("/api/v1/user/comment-likes/101?positive=false")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_CommentNotFound/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/UserCommentLikeRestControllerIT/getCommentLikeCount_CommentNotFound/AfterTest.sql")
    public void updateCommentLike_CommentLikeDoesNotExist() throws Exception {
        String token = getToken("user", "user");

        mockMvc.perform(put("/api/v1/user/comment-likes/101?positive=true")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Comment like does not exist")));
    }
}
