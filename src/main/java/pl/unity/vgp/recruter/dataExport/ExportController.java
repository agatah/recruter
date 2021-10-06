package pl.unity.vgp.recruter.dataExport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.unity.vgp.recruter.dataExport.dto.EmployeeDto;
import pl.unity.vgp.recruter.domain.model.Employee;
import pl.unity.vgp.recruter.domain.model.Project;
import pl.unity.vgp.recruter.domain.service.EmployeeService;
import pl.unity.vgp.recruter.domain.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/export")
public class ExportController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService){
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> exportProjects(){
        return ResponseEntity.ok().body(projectService.exportProjects());
    }

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> exportAllEmployees(){
        return ResponseEntity.ok().body(employeeService.exportAllEmployees());
    }

    @GetMapping(value = "/employees/without-project", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> exportEmployeesWithoutProjects(){
        return ResponseEntity.ok().body(employeeService.exportEmployeesWithoutProject());
    }

    @GetMapping(value = "/salaries", produces = "text/csv")
    public ResponseEntity<String> exportSalaries(){
        return ResponseEntity.ok().body(employeeService.exportSalaries());
    }

    @GetMapping(value = "/salaries/{projectName}", produces = "text/csv")
    public ResponseEntity<String> exportSalariesPerProject(@PathVariable String projectName){

        if(!projectService.ifProjectExist(projectName)){
            return new ResponseEntity<>("Project " + projectName + " can't be found in database", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(employeeService.exportSalariesPerProject(projectName));
    }
}
