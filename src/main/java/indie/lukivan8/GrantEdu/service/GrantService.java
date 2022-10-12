package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.model.repository.GrantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrantService {
    private final GrantRepo grantRepo;
    private final ApplicantRepo applicantRepo;

    public List<Grant> getAllGrants(){
        return grantRepo.findAll();
    }
    public Grant getGrant(Long id) {
        return grantRepo.findById(id).orElseThrow(() -> new RuntimeException("file not found with id " + id));
    }

    public Grant uploadGrant(MultipartFile multiFile, Principal principal) throws IOException {
        Applicant applicant = applicantRepo.findByUid(principal.getName());
        Grant grant = new Grant();
        grant.setFileName(multiFile.getOriginalFilename());
        grant.setFileType(multiFile.getContentType());
        grant.setData(multiFile.getBytes());
        grant.setAuthor(applicant);
        grantRepo.save(grant);
        applicant.setGrant(grant);
        applicantRepo.save(applicant);
        return grant;
    }
}
