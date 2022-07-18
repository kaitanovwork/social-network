package kata.academy.socialnetwork.web.init;

import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.entity.UserInfo;
import kata.academy.socialnetwork.model.enums.RoleName;
import kata.academy.socialnetwork.service.abst.entity.RoleService;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("David");
        userInfo.setLastName("Beckham");

        user.setUserInfo(userInfo);
        userService.save(user);
    }
}
