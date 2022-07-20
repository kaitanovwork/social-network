package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.dto.response.comment.CommentResponseDto;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserCommentRestController", description = "Контроллер для работы c комментариями")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/comments")
public class UserCommentRestController {

    private final CommentService commentService;

    @Operation(summary = "Эндпоинт для получения коментариев по postId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Коментарии поста успешно получены"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/post/{postId}")
    public Response<Page<CommentResponseDto>> getComments(@PathVariable Long postId, Pageable pageable) {
        return Response.ok(commentService.getCommentsByPostId(postId, pageable));
    }

}