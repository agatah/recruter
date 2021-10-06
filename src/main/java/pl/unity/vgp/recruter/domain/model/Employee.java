package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @JsonIgnore
    private Long id;

    private String name;
    private String surname;

    @JsonProperty("empolyeeType")
    private String employeeType;

    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    private boolean activeInAD;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeamMember> teamMembers = new ArrayList<>();

    public Employee(){

    }

    public Employee(String fullName){
        this.name = fullName.substring(0, fullName.indexOf(" "));
        this.surname = fullName.substring(fullName.indexOf(" ") + 1);
    }

    public String employeeWithSalaryToString(){
        return name + ";" + surname + ";" + salary.getGross() + ";" + salary.getNet();
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Employee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Salary getSalary() {
        return salary;
    }

    public Employee setSalary(Salary salary) {
        this.salary = salary;
        return this;
    }

    public boolean isActiveInAD() {
        return activeInAD;
    }

    public Employee setActiveInAD(boolean activeInAD) {
        this.activeInAD = activeInAD;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Employee setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public Employee setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addTeamMember(TeamMember teamMember){
        teamMembers.add(teamMember);
    }

    private void setStreet(String street) {
        if(address == null){
            address = new Address();
        }
        this.address.setStreet(street);
    }

    private void setStreetNumber(String streetNumber) {
        if(address == null){
            address = new Address();
        }
        this.address.setStreetNumber(streetNumber);
    }

    private void setFlatNumber(String flatNumber) {
        if(address == null){
            address = new Address();
        }
        this.address.setFlatNumber(flatNumber);
    }

    private void setZipCode(String zipCode) {
        if(address == null){
            address = new Address();
        }
        this.address.setZipCode(zipCode);
    }

    private void setCity(String city) {
        if(address == null){
            address = new Address();
        }
        this.address.setCity(city);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", salary=" + salary +
                ", activeInAD=" + activeInAD +
                ", address=" + address +
                '}';
    }
}
