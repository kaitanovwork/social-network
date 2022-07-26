package kata.academy.socialnetwork.service.impl.entity;



import kata.academy.socialnetwork.model.entity.Notification;
import kata.academy.socialnetwork.repository.abst.entity.NotificationRepository;
import kata.academy.socialnetwork.service.abst.entity.NotificationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends AbstractServiceImpl<Notification, Long> implements NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationServiceImpl (NotificationRepository notificationRepository ) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
    }


    @Override
    public Page<Notification> findAll(Pageable pageable) {
       return notificationRepository.findAll(pageable);
    }

    @Override
    public Page<Notification> findAll(Boolean isViewed,Pageable pageable ) {
        return notificationRepository.findAll(isViewed, pageable);
    }


}
