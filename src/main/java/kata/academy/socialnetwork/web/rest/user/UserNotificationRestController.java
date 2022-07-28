package kata.academy.socialnetwork.web.rest.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.socialnetwork.model.api.Response;
import kata.academy.socialnetwork.model.converter.NotificationMapper;

import kata.academy.socialnetwork.model.dto.response.notification.NotificationResponseDto;
import kata.academy.socialnetwork.model.entity.Notification;
import kata.academy.socialnetwork.service.abst.entity.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Tag(name = "UserNotificationRestController", description = "Контроллер для работы с уведомлениями")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/notifications")
public class UserNotificationRestController {

    private final NotificationService notificationService;

    @Operation(summary = "Эндпоинт для получения списка уведомлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успешно выгружен"),
            @ApiResponse(responseCode = "400", description = "Клиент допустил ошибки в запросе")
    })
    @GetMapping
    public Response<Page<NotificationResponseDto>> getNotificationPage(Pageable pageable, @RequestParam(value = "isViewed", required = false) Boolean isViewed) {
        Page<Notification> notifications;
        if (isViewed!= null ) {
            notifications = notificationService.findAll(isViewed, pageable);
        } else {
            notifications = notificationService.findAll(pageable);
        }
        return Response.ok(notifications.map(NotificationMapper::toDto));
    }
}
