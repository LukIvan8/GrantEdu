package indie.lukivan8.GrantEdu.model.repository;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrantRepo extends JpaRepository<Grant, Long> {

    public List<Grant> findByAuthor(Applicant author);
}
