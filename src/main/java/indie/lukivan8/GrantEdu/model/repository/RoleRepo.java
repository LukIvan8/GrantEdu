package indie.lukivan8.GrantEdu.model.repository;

import indie.lukivan8.GrantEdu.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
