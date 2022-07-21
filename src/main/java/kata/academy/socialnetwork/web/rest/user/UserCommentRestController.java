package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.dto.response.comment.CommentResponseDto;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import kata.academy.socialnetwork.web.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@Tag(name = "UserCommentRestController", description = "Контроллер для работы c комментариями")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/comments")
public class UserCommentRestController {

    private final CommentService commentService;
    private final PostService postService;

    @Operation(summary = "Эндпоинт для получения коментариев по postId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коментарии поста успешно получены"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/post/{postId}")
    public Response<Page<CommentResponseDto>> getCommentPage(@PathVariable @Positive Long postId, Pageable pageable) {
        ApiValidationUtil.requireTrue(postService.existsById(postId), "Post not found");
        return Response.ok(commentService.getCommentPageByPostId(postId, pageable));
    }
}
