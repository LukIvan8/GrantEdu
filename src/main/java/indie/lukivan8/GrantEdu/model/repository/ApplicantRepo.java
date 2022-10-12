package indie.lukivan8.GrantEdu.model.repository;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {
    @Transactional
    Applicant findByUid(String uid);
}
