package kata.academy.socialnetwork.web.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.PostMapper;
import kata.academy.socialnetwork.model.dto.request.post.PostPersistRequestDto;
import kata.academy.socialnetwork.model.dto.request.post.PostUpdateRequestDto;
import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.service.abst.dto.PostResponseDtoService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import kata.academy.socialnetwork.web.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Tag(name = "UserPostRestController", description = "Контроллер для работы с постами")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/posts")
public class UserPostRestController {

    private final PostService postService;
    private final PostResponseDtoService postResponseDtoService;

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

    @Operation(summary = "Эндпоинт для получения поста по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успешно получен"),
            @ApiResponse(responseCode = "400", description = "Пост не найден или клиент допустил ошибки в запросе")
    })
    @GetMapping("/{postId}")
    public Response<PostResponseDto> getPostById(@PathVariable Long postId) {
        Optional<PostResponseDto> postResponseDto = null;
        try {
            postResponseDto = postResponseDtoService.findById(postId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiValidationUtil.requireFalse(postResponseDto.isPresent(), "Post not found");

        return Response.ok(postResponseDto.get());
    }

    @Operation(summary = "Эндпоинт для удаления своего поста по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успешно удален"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @DeleteMapping("/{postId}")
    public Response<Void> removePostById(@PathVariable Long postId) {
        Optional<PostResponseDto> postResponseDto = postResponseDtoService.findById(postId);
        ApiValidationUtil.requireFalse(postResponseDto.isPresent(), "Post not found");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApiValidationUtil.requireTrue(postResponseDto.get().userResponseDto().id().equals(user.getId()),
                "You are not the author of this post");
        postService.deleteById(postResponseDto.get().id());
        return Response.ok();
    }

    @Operation(summary = "Эндпоинт для редактирования своего поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пост успешно изменен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @PutMapping
    public Response<PostResponseDto> updatePost(@RequestBody @Valid PostUpdateRequestDto dto) {
        Optional<PostResponseDto> postResponseDto = postResponseDtoService.findById(dto.id());
        ApiValidationUtil.requireFalse(postResponseDto.isPresent(), "Post not found");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ApiValidationUtil.requireTrue(postResponseDto.get().userResponseDto().id().equals(user.getId()),
                "You are not the author of this post");
        return Response.ok(PostMapper.toDto(postService.update(PostMapper.postUpdate(postResponseDto.get().id(), dto))));
    }
}
