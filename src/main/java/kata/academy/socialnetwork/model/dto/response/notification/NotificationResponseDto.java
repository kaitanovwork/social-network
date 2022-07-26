package kata.academy.socialnetwork.model.dto.response.notification;

import kata.academy.socialnetwork.model.converter.UserMapper;
import kata.academy.socialnetwork.model.dto.response.user.UserResponseDto;
import kata.academy.socialnetwork.model.entity.Notification;

import java.time.LocalDateTime;

public record NotificationResponseDto(
        Long id,
        String text,
        LocalDateTime time,
        UserResponseDto userResponseDto,
        Boolean isViewed
) {

    public NotificationResponseDto(Notification notification) {
        this(notification.getId(), notification.getText(), notification.getTime(), UserMapper.toDto(notification.getRecipient()), notification.getIsViewed());
    }
}


