package pl.unity.vgp.recruter.dataExport.dto;

import pl.unity.vgp.recruter.domain.model.Address;
import pl.unity.vgp.recruter.domain.model.Salary;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDto {

    private String name;
    private String surname;
    private Salary salary;
    private Address address;
    private String employeeType;
    private List<ReservationDto> reservations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public EmployeeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeeDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Salary getSalary() {
        return salary;
    }

    public EmployeeDto setSalary(Salary salary) {
        this.salary = salary;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public EmployeeDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public EmployeeDto setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public EmployeeDto setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
        return this;
    }
}
