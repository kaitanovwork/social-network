package kata.academy.socialnetwork.web.init;

import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.entity.UserInfo;
import kata.academy.socialnetwork.model.enums.Gender;
import kata.academy.socialnetwork.model.enums.RoleName;
import kata.academy.socialnetwork.service.abst.entity.RoleService;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.save(userRole);

        User user = new User();
        user.setEmail("user");
        user.setPassword("password");
        user.setUserInfo(new UserInfo(
                "David",
                "Beckham",
                "LA",
                25,
                Gender.MALE
        ));
        userService.save(user);
    }
}
