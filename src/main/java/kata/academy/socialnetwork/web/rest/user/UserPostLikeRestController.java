package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.service.abst.entity.PostLikeService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import kata.academy.socialnetwork.web.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    //TODO добавить эндпоинт для добавления лайка или дизлайка посту POST/{postId} @RequestParam("positive") Boolean positive Void Метод ничего не возвращает, метод должен проверять на существование поста, метод должен проверить, что юзер не ставил лайк или дизлайк этому посту
    //TODO добавить эндпоинт для удаления лайка или дизлайка посту DELETE/{postId} Void Метод ничего не возвращает, метод должен проверять на существование поста, метод должен проверить, что юзер ставил лайк или дизлайк этому посту
    //TODO добавить эндпоинт для обновления лайка или дизлайка посту PUT/{postId} @RequestParam("positive") Boolean positive Void Метод ничего не возвращает, метод должен проверять на существование поста, метод должен проверить, что юзер ставил лайк или дизлайк этому посту
}
