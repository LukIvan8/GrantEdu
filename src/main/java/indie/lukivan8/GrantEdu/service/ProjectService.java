package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.model.entity.Project;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.repository.ProjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

@Service@AllArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final ApplicantRepo applicantRepo;
    public void createNewProject(Project project, Principal principal){
        Applicant creator = applicantRepo.findByUid(principal.getName());
        project.setCreator(creator);
        projectRepo.save(project);
    }

    public boolean checkModList(Long project_id, Principal principal){
        Project project = projectRepo.findById(project_id).orElseThrow(()->new RuntimeException("no such project"));
        Applicant mod = applicantRepo.findByUid(principal.getName());
        return project.getModerators().contains(mod);
    }
}
