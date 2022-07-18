package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.enums.RoleName;
import kata.academy.socialnetwork.repository.abst.entity.UserRepository;
import kata.academy.socialnetwork.service.abst.entity.RoleService;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName(RoleName.USER));
        return super.save(user);
    }

    @Override
    public User updatePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return update(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
