package indie.lukivan8.GrantEdu.model.repository;

import indie.lukivan8.GrantEdu.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
