package kata.academy.socialnetwork.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    private String text;
    private LocalDateTime time;
    private Boolean isViewed;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRecipient(), that.getRecipient()) && Objects.equals(getText(), that.getText()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getIsViewed(), that.getIsViewed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRecipient(), getText(), getTime(), getIsViewed());
    }
}
