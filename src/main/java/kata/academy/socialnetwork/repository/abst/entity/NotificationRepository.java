package kata.academy.socialnetwork.repository.abst.entity;


import kata.academy.socialnetwork.model.entity.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query("""
            SELECT n
            FROM Notification n
            WHERE n.isViewed = :isViewed
            """)
    Page<Notification> findAll(@Param("isViewed") Boolean isViewed, Pageable pageable);

}
