package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.User;

public interface UserService extends AbstractService<User, Long> {

    User updatePassword(User user);

    boolean existsByEmail(String email);
}
