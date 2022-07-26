package kata.academy.socialnetwork.service.abst.dto;

import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;

import java.util.Optional;

public interface PostResponseDtoService {
    Optional<PostResponseDto> findById(Long postId);
}
