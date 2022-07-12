package kata.academy.socialnetwork.model.entity;

import kata.academy.socialnetwork.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

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


}
