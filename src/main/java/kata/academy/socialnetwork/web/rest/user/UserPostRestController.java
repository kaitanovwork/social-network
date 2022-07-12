package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.PostMapper;
import kata.academy.socialnetwork.model.dto.response.user.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.service.abst.entity.PostLikeService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserPostRestController", description = "Контроллер для работы с постами")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/post")
public class UserPostRestController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @Operation(summary = "Эндпоинт для получения списка постов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успешно выгружен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping
    public Response<Page<PostResponseDto>> getPostPage(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        return Response.ok(posts.map(PostMapper::toDto));
    }

    @Operation(summary = "Эндпоинт для получения количества лайков поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество лайков успешно получено"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/{postId}/postlike/count")
    public Response<Integer> getPostLikeCount(@PathVariable Long postId, @RequestParam("positive") Boolean positive) {
        Integer count = postLikeService.countPostLikesByIdAndPositive(postId, positive);
        return Response.ok(count);
    }
}
