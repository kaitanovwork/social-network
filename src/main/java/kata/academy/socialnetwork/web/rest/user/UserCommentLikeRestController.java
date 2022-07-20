package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.entity.CommentLike;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.entity.CommentLikeService;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
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



@Tag(name = "UserCommentLikeRestController", description = "Контроллер для работы с лайками комментов")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/comment-likes")
public class UserCommentLikeRestController {

    private final CommentLikeService commentLikeService;
    private final CommentService commentService;



    @Operation(summary = "Эндпоинт для получения количества лайков коммента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество лайков успешно получено"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping("/{commentId}/count")
    public Response<Integer> getCommentLikeCount(@PathVariable @Positive Long commentId, @RequestParam("positive") Boolean positive) {
        ApiValidationUtil.requireTrue(commentService.existsById(commentId), "Comment not found");
        return Response.ok(commentLikeService.countCommentLikesByIdAndPositive(commentId, positive));
    }


    @Operation(summary = "эндпоинт для добавления лайка или дизлайка комменту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк добавлен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PostMapping("/{commentId}")
    public Response<Void> addLikeIfNotExist(@PathVariable @Positive Long commentId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiValidationUtil.requireNull(commentLikeService.findByCommentIdAndUserId(commentId, user.getId()), "comment does not exist");
        CommentLike commentLike = new CommentLike();
        commentLike.setComment(commentService.findById(commentId).get());
        commentLike.setUser(user);
        commentLike.setPositive(positive);
        commentLikeService.save(commentLike);
        return Response.ok();
    }

    @Operation(summary = "эндпоинт для удаления лайка или дизлайка комменту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк удален"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @DeleteMapping("/{commentId}")
    public Response<Void> deleteLikeIfExist(@PathVariable @Positive Long commentId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CommentLike commentLike = commentLikeService.findByCommentIdAndUserId(commentId, user.getId());
        ApiValidationUtil.requireNotNull(commentLike, "comment does not exist");
        commentLikeService.delete(commentLike);
        return Response.ok();
    }

    @Operation(summary = "эндпоинт для обновления лайка или дизлайка комменту")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк или дизлайк обновлен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping("/{commentId}")
    public Response<Void> updateLikeIfExist(@PathVariable @Positive Long commentId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CommentLike commentLike = commentLikeService.findByCommentIdAndUserId(commentId, user.getId());
        ApiValidationUtil.requireNotNull(commentLike, "comment does not exist");
        commentLikeService.update(commentLike);
        return Response.ok();
    }




}
