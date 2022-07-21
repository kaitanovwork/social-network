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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Optional;

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
    public Response<Void> addPostLike(@PathVariable @Positive Long postId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Post> optionalPost = postService.findById(postId);
        ApiValidationUtil.requireTrue(optionalPost.isPresent(), "Post not found");
        ApiValidationUtil.requireFalse(postLikeService.existsByPostIdAndUserId(postId, user.getId()), "Post like or dislike already exists");
        postLikeService.save(new PostLike(null, optionalPost.get(), user, positive));
        return Response.ok();
    }

    @Operation(summary = "Эндпойнт для удаления лайка или дизлайка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк удалён"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @DeleteMapping("/{postId}")
    public Response<Void> deletePostLike(@PathVariable @Positive Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<PostLike> optionalPostLike = postLikeService.findByPostIdAndUserId(postId, user.getId());
        ApiValidationUtil.requireTrue(optionalPostLike.isPresent(), "Post like or dislike not found");
        postLikeService.delete(optionalPostLike.get());
        return Response.ok();
    }

    @Operation(summary = "Эндпойнт для обновления лайка или дизлайка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк обновлён"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping("/{postId}")
    public Response<Void> updatePostLike(@PathVariable @Positive Long postId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<PostLike> optionalPostLike = postLikeService.findByPostIdAndUserId(postId, user.getId());
        ApiValidationUtil.requireTrue(optionalPostLike.isPresent(), "Post like or dislike not found");
        PostLike postLike = optionalPostLike.get();
        postLike.setPositive(positive);
        postLikeService.save(postLike);
        return Response.ok();
    }
}
