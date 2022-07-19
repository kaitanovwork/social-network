package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.entity.Comment;
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
import java.util.List;
import java.util.Optional;


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
    @GetMapping("/{commId}/count")
    public Response<Integer> getCommentLikeCount(@PathVariable @Positive Long commId, @RequestParam("positive") Boolean positive) {
        ApiValidationUtil.requireTrue(commentService.existsById(commId), "Post not found");
        return Response.ok(commentLikeService.countPostLikesByIdAndPositive(commId, positive));
    }


    @Operation(summary = "эндпоинт для добавления лайка или дизлайка комменту")
    @PostMapping("/{commId}")
    public void getCommentLikeChecker(@PathVariable @Positive Long commId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(commentService.existsById(commId)) {
            CommentLike commentLike = new CommentLike();
            Optional<Comment> optionalComment = commentService.findById(commId);
            Comment comment = optionalComment.get();
            List <CommentLike> likeList = commentLikeService.findAll();
            if( likeList.stream().noneMatch(l -> l.getUser().equals(user) && l.getComment().equals(comment))){
                commentLike.setComment(comment);
                commentLike.setUser(user);
                commentLike.setPositive(positive);
                commentLikeService.save(commentLike);
            }

        }
    }

    @Operation(summary = "эндпоинт для удаления лайка или дизлайка комменту")
    @DeleteMapping("/{commId}")
    public void getCommentLikeDeleter(@PathVariable @Positive Long commId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(commentService.existsById(commId)) {
            Optional<Comment> optionalComment = commentService.findById(commId);
            Comment comment = optionalComment.get();
            List <CommentLike> likeList = commentLikeService.findAll();
            likeList.stream()
                    .filter(l -> l.getUser().equals(user))
                    .filter(l -> l.getComment().equals(comment))
                    .findFirst().ifPresent(commentLikeService::delete);

        }
    }

    @Operation(summary = "эндпоинт для обновления лайка или дизлайка комменту")
    @PutMapping("/{commId}")
    public void getCommentLikeUpdater(@PathVariable @Positive Long commId, @RequestParam("positive") Boolean positive) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(commentService.existsById(commId)) {
            Optional<Comment> optionalComment = commentService.findById(commId);
            Comment comment = optionalComment.get();
            List <CommentLike> likeList = commentLikeService.findAll();
            if( likeList.stream().anyMatch(l -> l.getUser().equals(user) && l.getComment().equals(comment))){
                likeList.stream()
                        .filter(l -> l.getUser().equals(user))
                        .filter(l -> l.getComment().equals(comment))
                        .peek(l -> l.setPositive(positive))
                        .forEach(commentLikeService::update);
            }

        }
    }



}
