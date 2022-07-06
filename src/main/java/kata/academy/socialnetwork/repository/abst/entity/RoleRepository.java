package kata.academy.socialnetwork.repository.abst.entity;

import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);
}
