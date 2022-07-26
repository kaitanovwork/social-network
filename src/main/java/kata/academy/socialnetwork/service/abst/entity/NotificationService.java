package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService extends AbstractService <Notification, Long> {

    Page<Notification> findAll(Pageable pageable);
    Page<Notification> findAll(Boolean isViewed, Pageable pageable);

}
