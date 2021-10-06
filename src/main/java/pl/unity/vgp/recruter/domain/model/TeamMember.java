package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;

@Entity
@Table(name = "team_member")
public class TeamMember {

    @Id
    @SequenceGenerator(name = "team_member_generator", sequenceName = "team_member_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_member_generator")
    @JsonIgnore
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JacksonXmlProperty(localName = "name")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;

    private String reservation;

    public Employee getEmployee() {
        return employee;
    }

    public TeamMember setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public TeamMember setTeam(Team team) {
        this.team = team;
        return this;
    }

    public String getReservation() {
        return reservation;
    }

    public TeamMember setReservation(String reservation) {
        this.reservation = reservation;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", employee=" + employee.getName() + " " + employee.getSurname() +
                ", team=" + team.getName() +
                ", reservation='" + reservation + '\'' +
                '}';
    }

}
