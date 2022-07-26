package kata.academy.socialnetwork.service.impl.dto;

import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;
import kata.academy.socialnetwork.repository.abst.dto.PostResponseDtoRepository;
import kata.academy.socialnetwork.service.abst.dto.PostResponseDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostResponseDtoServiceImpl implements PostResponseDtoService {

    private final PostResponseDtoRepository postResponseDtoRepository;

    @Override
    public Optional<PostResponseDto> findById(Long postId) {
        return postResponseDtoRepository.findDtoById(postId);
    }
}