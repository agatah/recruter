package pl.unity.vgp.recruter.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unity.vgp.recruter.domain.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByNameAndSurname(String name, String surname);
    
    List<Employee> findAllByActiveInADTrue();

    List<Employee> findAllByActiveInADAndProjectsNull(boolean activeInAD);

    List<Employee> findAllByActiveInADTrueAndProjectsName(String projectName);

}
