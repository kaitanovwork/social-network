package kata.academy.socialnetwork.model.dto.response.comment;

import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;

public record CommentResponseDto(Long id, String text, UserResponseDto userResponseDto) {}
