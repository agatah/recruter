package pl.unity.vgp.recruter.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.unity.vgp.recruter.domain.model.Framework;

import java.util.Optional;

public interface FrameworkRepository extends JpaRepository<Framework, Long> {

    Optional<Framework> findFrameworkByName(String name);
}
