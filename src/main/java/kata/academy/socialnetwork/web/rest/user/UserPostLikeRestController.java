package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.PostLike;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.entity.PostLikeService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import kata.academy.socialnetwork.web.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Tag(name = "UserPostLikeRestController", description = "Контроллер для работы с лайками поста")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/post-likes")
public class UserPostLikeRestController {

    private final PostLikeService postLikeService;
    private final PostService postService;

    @Operation(summary = "Эндпоинт для получения количества лайков поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество лайков успешно получено"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/{postId}/count")
    public Response<Integer> getPostLikeCount(@PathVariable @Positive Long postId, @RequestParam("positive") Boolean positive) {
        ApiValidationUtil.requireTrue(postService.existsById(postId), "Post not found");
        return Response.ok(postLikeService.countPostLikesByIdAndPositive(postId, positive));
    }

    @Operation(summary = "Эндпойнт для добавления лайка, или дизлайка посту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк добавле"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PostMapping("/{postId}")
    public Response<Void> setPostLikeOrDislike(@PathVariable @Positive Long postId, @RequestParam("positive") Boolean positive) {
        User user = getUser();
        Post post = getPost(postId);
        ApiValidationUtil.requireNull(getPostLike(user, post), "Post like or dislike already exists");
        postLikeService.save(new PostLike(0L, post, user, positive));
        return Response.ok();
    }

    @Operation(summary = "Эндпойнт для удаления лайка или дизлайка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк удалён"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @DeleteMapping("/{postId}")
    public Response<Void> deleteLikeOrDislike(@PathVariable @Positive Long postId) {
        User user = getUser();
        Post post = getPost(postId);
        PostLike postLike = getPostLike(user, post);
        ApiValidationUtil.requireNotNull(postLike, "Post like or dislike not found");
        postLikeService.delete(postLike);
        return Response.ok();
    }

    @Operation(summary = "Эндпойнт для обновления лайка или дизлайка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк обновлён"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping("/{postId}")
    public Response<Void> updateLikeOrDislike(@PathVariable @Positive Long postId, @RequestParam("positive") Boolean positive) {
        User user = getUser();
        Post post = getPost(postId);
        PostLike postLike = getPostLike(user, post);
        ApiValidationUtil.requireNotNull(postLike, "Post like or dislike not found");
        postLike.setPositive(positive);
        postLikeService.save(postLike);
        return Response.ok();
    }

    private User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Post getPost(Long postId) {
        ApiValidationUtil.requireTrue(postService.existsById(postId), "Post not found");
        return postService.findById(postId).get();
    }

    private PostLike getPostLike(User user, Post post) {
        return postLikeService.findByPostAndUser(post, user);
    }

}
