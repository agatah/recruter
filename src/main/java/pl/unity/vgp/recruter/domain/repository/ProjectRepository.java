package pl.unity.vgp.recruter.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unity.vgp.recruter.domain.model.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsProjectByName(String name);

}
