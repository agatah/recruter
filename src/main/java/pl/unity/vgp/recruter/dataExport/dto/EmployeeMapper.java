package pl.unity.vgp.recruter.dataExport.dto;

import pl.unity.vgp.recruter.domain.model.Employee;
import pl.unity.vgp.recruter.domain.model.TeamMember;

import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto toEmployeeDto(Employee employee) {

        return new EmployeeDto()
                .setName(employee.getName())
                .setSurname(employee.getSurname())
                .setEmployeeType(employee.getEmployeeType())
                .setAddress(employee.getAddress())
                .setSalary(employee.getSalary())
                .setReservations(employee.getTeamMembers()
                        .stream()
                        .map(EmployeeMapper::toReservationDto)
                        .collect(Collectors.toList()));
    }

    public static ReservationDto toReservationDto(TeamMember teamMember){
        return new ReservationDto()
                .setPercentage(teamMember.getReservation())
                .setProjectName(teamMember.getTeam().getProject().getName());
    }
}
