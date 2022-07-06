package kata.academy.socialnetwork.service.abst.entity;

import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.enums.RoleName;

public interface RoleService extends AbstractService<Role, Long> {

    Role findByName(RoleName name);
}
