package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.entity.Role;
import indie.lukivan8.GrantEdu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/save")
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }
}
