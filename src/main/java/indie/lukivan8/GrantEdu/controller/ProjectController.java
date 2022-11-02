package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.entity.Project;
import indie.lukivan8.GrantEdu.model.repository.ProjectRepo;
import indie.lukivan8.GrantEdu.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectRepo projectRepo;
    private final ProjectService projectService;

    @GetMapping("/list")
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @PostMapping("/create")
    public void createProject(@RequestBody Project project, Principal principal){
        projectService.createNewProject(project, principal);
    }
}
