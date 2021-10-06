package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @SequenceGenerator(name = "project_generator", sequenceName = "project_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    @JsonIgnore
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Team team;

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public Project setTeam(Team team) {
        this.team = team;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team.getName() +
                '}';
    }
}
