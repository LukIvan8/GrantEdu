package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.dto.GrantResponse;
import indie.lukivan8.GrantEdu.model.dto.ScoreDTO;
import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.model.entity.Project;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.repository.GrantRepo;
import indie.lukivan8.GrantEdu.model.repository.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GrantService {
    private final GrantRepo grantRepo;
    private final ApplicantRepo applicantRepo;
    private final ProjectRepo projectRepo;

    public List<GrantResponse> getModGrants() {
        List<GrantResponse> grantList = new ArrayList<>();
        grantRepo.findAll().forEach(grant ->
                grantList.add(new GrantResponse(grant.getFileName(),
                        grant.getFileType(),
                        grant.getData(),
                        grant.getScored(),
                        grant.getIdeaScore(),
                        grant.getPlanScore(),
                        grant.getActualityScore())));
        return grantList;
    }


    public Grant getGrant(Long id) {
        return grantRepo.findById(id).orElseThrow(() -> new RuntimeException("file not found with id " + id));
    }

    public Grant uploadGrant(MultipartFile multiFile, Principal principal, Long project_id) throws IOException {
        Applicant applicant = applicantRepo.findByUid(principal.getName());
        Project project = projectRepo.findById(project_id).orElseThrow(()-> new RuntimeException("No such project"));
        Grant grant = new Grant();
        grant.setFileName(multiFile.getOriginalFilename());
        grant.setFileType(multiFile.getContentType());
        grant.setData(multiFile.getBytes());
        grant.setAuthor(applicant);
        grantRepo.save(grant);
        applicantRepo.save(applicant);
        Set<Grant> grants = project.getGrantList();
        grants.add(grant);
        projectRepo.save(project);
        return grant;
    }

    @Transactional
    public Grant setScore(Long id, ScoreDTO score, Principal principal) {
        Applicant moderator = applicantRepo.findByUid(principal.getName());
        Grant grant = grantRepo.findById(id).orElseThrow(() -> new RuntimeException("no such grant"));
        grant.setActualityScore(score.getActuality());
        grant.setPlanScore(score.getPlan());
        grant.setIdeaScore(score.getIdea());
        grant.setScorer(moderator);
        grant.setScored(true);
        grantRepo.save(grant);
        return grant;
    }
}
