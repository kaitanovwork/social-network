package kata.academy.socialnetwork.web.rest.user;

import kata.academy.socialnetwork.SpringSimpleContextTest;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserPostRestControllerIT extends SpringSimpleContextTest {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostPage_PositiveTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostPage_PositiveTest/AfterTest.sql")
    public void getPostPage_PositiveTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/posts").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.numberOfElements", Is.is(3)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostPage_NoPostsTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostPage_NoPostsTest/AfterTest.sql")
    public void getPostPage_NoPostsTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/posts").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.numberOfElements", Is.is(0)));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PositiveTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PositiveTest/AfterTest.sql")
    public void addPost_PositiveTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        post("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"title\",\"text\": \"text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Is.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text", Is.is("text")));
        assertNotNull(entityManager.createNativeQuery("select * from posts").getSingleResult());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PostTitleIsBlankTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PostTitleIsBlankTest/AfterTest.sql")
    public void addPost_PostTitleIsBlankTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        post("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"\",\"text\": \"text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [title]")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PostTextIsBlankTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/addPost_PostTextIsBlankTest/AfterTest.sql")
    public void addPost_PostTextIsBlankTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        post("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"title\",\"text\": \"\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [text]")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostById_PositiveTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostById_PositiveTest/AfterTest.sql")
    public void getPostById_PositiveTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/posts/101").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Is.is(101)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Is.is("post title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text", Is.is("post text")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostById_PostsNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/getPostById_PostsNotFoundTest/AfterTest.sql")
    public void getPostById_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(get("/api/v1/user/posts/101").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/removePostById_PositiveTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/removePostById_PositiveTest/AfterTest.sql")
    public void removePostById_PositiveTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("/api/v1/user/posts/101").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", IsNull.nullValue()));
        assertEquals(0, entityManager.createNativeQuery("select * from posts where id = 101").getResultList().size());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/removePostById_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/removePostById_PostNotFoundTest/AfterTest.sql")
    public void removePostById_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(delete("/api/v1/user/posts/101").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PositiveTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PositiveTest/AfterTest.sql")
    public void updatePost_PositiveTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        put("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 101,\"title\": \"updated title\",\"text\": \"updated text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Is.is("updated title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.text", Is.is("updated text")));
        assertEquals("updated title", entityManager.createNativeQuery("select title from posts").getSingleResult());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PostNotFoundTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PostNotFoundTest/AfterTest.sql")
    public void updatePost_PostNotFoundTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        put("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 101,\"title\": \"updated title\",\"text\": \"updated text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("Post not found")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_WrongPostAuthorTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_WrongPostAuthorTest/AfterTest.sql")
    public void updatePost_WrongPostAuthorTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        put("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 101,\"title\": \"updated title\",\"text\": \"updated text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("You are not the author of this post")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_WrongPostAuthorTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_WrongPostAuthorTest/AfterTest.sql")
    public void updatePost_PostTitleIsBlankTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        put("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 101,\"title\": \"\",\"text\": \"updated text\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [title]")));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PostTextIsBlankTest/BeforeTest.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = "/scripts/user/UserPostRestController/updatePost_PostTextIsBlankTest/AfterTest.sql")
    public void updatePost_PostTextIsBlankTest() throws Exception {
        String token = getToken("user", "user");
        mockMvc.perform(
                        put("/api/v1/user/posts")
                                .header("Authorization", token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 101,\"title\": \"updated title\",\"text\": \"\",\"tags\": []}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Is.is("В теле запроса допущены ошибки в следующих полях: [text]")));
    }
}