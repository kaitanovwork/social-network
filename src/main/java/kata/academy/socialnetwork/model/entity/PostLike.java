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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "post_likes")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean positive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLike postLike = (PostLike) o;
        return Objects.equals(id, postLike.id) && Objects.equals(post, postLike.post) &&
                Objects.equals(user, postLike.user) && Objects.equals(positive, postLike.positive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, post, user, positive);
    }
}
