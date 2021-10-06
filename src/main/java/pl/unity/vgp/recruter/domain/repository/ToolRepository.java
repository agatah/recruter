package pl.unity.vgp.recruter.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.unity.vgp.recruter.domain.model.Tool;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findByName(String name);
}
