package pl.unity.vgp.recruter.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.unity.vgp.recruter.dataExport.dto.EmployeeDto;
import pl.unity.vgp.recruter.dataExport.dto.EmployeeMapper;
import pl.unity.vgp.recruter.domain.model.Employee;
import pl.unity.vgp.recruter.domain.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployeesData(Iterable<Employee> employees){

        for(Employee employee: employees){
            Optional<Employee> employeeOpt = employeeRepository
                    .findEmployeeByNameAndSurname(employee.getName(), employee.getSurname());

            if(employeeOpt.isPresent()){
                Employee dbEmployee = employeeOpt.get();
                dbEmployee
                        .setActiveInAD(employee.isActiveInAD())
                        .setSalary(employee.getSalary())
                        .setEmployeeType(employee.getEmployeeType());

                employeeRepository.save(dbEmployee);

            } else {
                employeeRepository.save(employee);
            }
        }
    }

    public void saveEmployeesWithAddress(Iterable<Employee> employees){
        for(Employee employee: employees){
            Optional<Employee> employeeOpt = employeeRepository
                    .findEmployeeByNameAndSurname(employee.getName(), employee.getSurname());

            if(employeeOpt.isPresent()){
                Employee dbEmployee = employeeOpt.get();
                dbEmployee.setAddress(employee.getAddress());
                employeeRepository.save(dbEmployee);

            } else {
                employeeRepository.save(employee);
            }
        }
    }

    public List<EmployeeDto> exportAllEmployees(){
        List<Employee> employees = employeeRepository.findAllByActiveInADTrue();
        return employees.stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
    }

    public List<Employee> exportEmployeesWithoutProject(){
        return employeeRepository.findAllByActiveInADAndProjectsNull(true);
    }

    public String exportSalaries() {
        List<Employee> employees = employeeRepository.findAllByActiveInADTrue();
        return employeesToSalaryCsv(employees);
    }

    public String exportSalariesPerProject(String projectName){
        List<Employee> employees = employeeRepository.findAllByActiveInADTrueAndProjectsName(projectName);
        return employeesToSalaryCsv(employees);
    }

    private String employeesToSalaryCsv(List<Employee> employees){
        StringBuilder sb = new StringBuilder();
        sb.append("name;surname;gross;net");
        for(Employee employee: employees){
            sb.append("\n").append(employee.employeeWithSalaryToString());
        }
        return sb.toString();
    }
}
