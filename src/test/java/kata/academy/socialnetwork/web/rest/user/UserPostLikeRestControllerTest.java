package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserPostLikeRestControllerTest extends SpringSimpleContextTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_PostNotFoundTest/AfterTest.sql")
    void getPostLikeCount_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/post-likes/103/count?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_PositiveCountSuccessTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_PositiveCountSuccessTest/AfterTest.sql")
    void getPostLikeCount_PositiveCountTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/post-likes/101/count?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Is.is(2)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_NegativeCountSuccessTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/getPostLikeCount_NegativeCountSuccessTest/AfterTest.sql")
    void getPostLikeCount_NegativeCountTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/post-likes/101/count?positive=false")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Is.is(2)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostNotFoundTest/AfterTest.sql")
    void addPostLike_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(post("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostLikeExistsTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostLikeExistsTest/AfterTest.sql")
    void addPostLike_PostLikeExistsTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(post("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post like or dislike already exists")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostLikeAddSuccessTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/addPostLike_PostLikeAddSuccessTest/AfterTest.sql")
    void addPostLike_PostLikeAddSuccessTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(post("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
        assertNotNull(entityManager.createNativeQuery("select * from post_likes where user_id = 101 and post_id = 101 and positive = true").getSingleResult());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostNotFoundTest/AfterTest.sql")
    void deletePostLike_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("http://localhost:8088/api/v1/user/post-likes/101")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post like or dislike not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostLikeNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostLikeNotFoundTest/AfterTest.sql")
    void deletePostLike_PostLikeNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("http://localhost:8088/api/v1/user/post-likes/101")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post like or dislike not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostLikeDeleteSuccessTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/deletePostLike_PostLikeDeleteSuccessTest/AfterTest.sql")
    void deletePostLike_PostLikeDeleteSuccessTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("http://localhost:8088/api/v1/user/post-likes/101")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
        assertEquals(entityManager.createNativeQuery("select * from post_likes where post_id = 101").getFirstResult(), 0);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostNotFoundTest/AfterTest.sql")
    void updatePostLike_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(put("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post like or dislike not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostLikeNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostLikeNotFoundTest/AfterTest.sql")
    void updatePostLike_PostLikeNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(put("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post like or dislike not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostLikeUpdateSuccessTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostLikeRestController/updatePostLike_PostLikeUpdateSuccessTest/AfterTest.sql")
    void updatePostLike_PostLikeUpdateSuccessTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(put("http://localhost:8088/api/v1/user/post-likes/101?positive=true")
                        .header("Authorization", token)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
        assertNotNull(entityManager.createNativeQuery("select * from post_likes where user_id = 101 and post_id = 101 and positive = true").getSingleResult());
    }
}