package kata.academy.socialnetwork.repository.abst.dto;

import kata.academy.socialnetwork.model.dto.response.post.PostResponseDto;
import kata.academy.socialnetwork.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostResponseDtoRepository extends JpaRepository<Post, Long> {

    @Query(
            """
            SELECT new kata.academy.socialnetwork.model.dto.response.post.PostResponseDto(p)
            FROM Post p
            WHERE p.id = :postId
            """
    )
    Optional<PostResponseDto> findDtoById(@Param("postId") Long postId);
}
