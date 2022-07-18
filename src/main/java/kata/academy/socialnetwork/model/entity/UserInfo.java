package kata.academy.socialnetwork.model.entity;

import kata.academy.socialnetwork.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    private String firstName;
    private String lastName;
    private String city;
    private Integer age;
    private Gender gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(firstName, userInfo.firstName) && Objects.equals(lastName, userInfo.lastName) && Objects.equals(city, userInfo.city) && Objects.equals(age, userInfo.age) && gender == userInfo.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, city, age, gender);
    }
}
