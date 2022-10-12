package indie.lukivan8.GrantEdu.utils;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public Applicant mapToApplicant(RegisterDTO dto) {
        Applicant applicantEntity = new Applicant();
        applicantEntity.setFullName(dto.getFullName());
        applicantEntity.setUid(dto.getUid());
        applicantEntity.setEmail(dto.getEmail());
        applicantEntity.setPhoneNum(dto.getPhoneNum());
        applicantEntity.setPassword(dto.getPassword());
        return applicantEntity;
    }
}
