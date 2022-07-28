package kata.academy.socialnetwork.model.converter;


import kata.academy.socialnetwork.model.dto.response.notification.NotificationResponseDto;

import kata.academy.socialnetwork.model.entity.Notification;

public class NotificationMapper {
    public NotificationMapper(){

    }

    public static NotificationResponseDto toDto(Notification notification) {
        return new NotificationResponseDto(
                notification.getId(),
                notification.getText(),
                notification.getTime(),
                UserMapper.toDto(notification.getRecipient()),
                notification.getIsViewed()
        );
    }

}
