package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.enums.RoleName;
import kata.academy.socialnetwork.repository.abst.entity.RoleRepository;
import kata.academy.socialnetwork.service.abst.entity.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
