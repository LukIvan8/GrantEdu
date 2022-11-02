package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.model.entity.Project;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.repository.GrantRepo;
import indie.lukivan8.GrantEdu.model.repository.ProjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AdminService {
    private final ApplicantRepo applicantRepo;
    private final GrantRepo grantRepo;
    private final ProjectRepo projectRepo;

    public Applicant addModerator(String uid, Long project_id) {
        Applicant mod = applicantRepo.findByUid(uid);
        if (mod == null) throw new UsernameNotFoundException("user with such username not found");
        Project project = projectRepo.findById(project_id).orElseThrow(() -> new RuntimeException("no such project"));
        Set<Applicant> mods = project.getModerators();
        mods.add(mod);
        projectRepo.save(project);
        return mod;
    }

    public List<Grant> getAdminGrants() {
        return grantRepo.findAll();
    }
}
