package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/scorer/{project_id}")
    public ResponseEntity<Applicant> createScorer(@RequestParam String uid, @PathVariable Long project_id) {
        return ResponseEntity.ok(adminService.addModerator(uid, project_id));
    }

    @GetMapping("/grants")
    public List<Grant> getAdminGrants() {
        return adminService.getAdminGrants();
    }


}
