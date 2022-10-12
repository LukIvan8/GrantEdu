package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Role;
import indie.lukivan8.GrantEdu.model.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
}
