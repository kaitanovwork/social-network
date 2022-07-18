package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.PostMapper;
import kata.academy.socialnetwork.model.dto.request.post.PostPersistRequestDto;
import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "UserPostRestController", description = "Контроллер для работы с постами")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/posts")
public class UserPostRestController {

    private final PostService postService;

    @Operation(summary = "Эндпоинт для получения списка постов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успешно выгружен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping
    public Response<Page<PostResponseDto>> getPostPage(@PageableDefault(sort = "id") Pageable pageable) {
        Page<Post> posts = postService.findAll(pageable);
        return Response.ok(posts.map(PostMapper::toDto));
    }

    @Operation(summary = "Эндпоинт для добавления нового поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успешно создан"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PostMapping
    public Response<PostResponseDto> addPost(@RequestBody @Valid PostPersistRequestDto dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Response.ok(PostMapper.toDto(postService.save(PostMapper.toEntity(dto, user))));
    }

    //TODO добавить эндпоинт для получения по id GET/{postId} PostResponseDto
    //TODO добавить эндпоинт для удаления (только свой пост) по id DELETE/{postId} Void
    //TODO добавить эндпоинт для обновления (только свой пост) по id PUT/ PostResponseDto
}
