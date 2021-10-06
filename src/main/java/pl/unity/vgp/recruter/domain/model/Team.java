package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @SequenceGenerator(name = "team_generator", sequenceName = "team_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_generator")
    @JsonIgnore
    private Long id;

    @OneToOne(mappedBy = "team")
    @JsonIgnore
    private Project project;

    private String name;
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    private Employee manager;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeamMember> teamMembers = new ArrayList<>();

    private String methodology;

    @JacksonXmlProperty(localName = "MPK")
    private String mpk;

    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private Fees fees;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Technology> technologies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Team setCode(String code) {
        this.code = code;
        return this;
    }

    public Employee getManager() {
        return manager;
    }

    public Team setManager(Employee manager) {
        this.manager = manager;
        return this;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public Team setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
        for(TeamMember teamMember: teamMembers){
            teamMember.setTeam(this);
        }
        return this;
    }

    public String getMethodology() {
        return methodology;
    }

    public Team setMethodology(String methodology) {
        this.methodology = methodology;
        return this;
    }

    public String getMpk() {
        return mpk;
    }

    public Team setMpk(String mpk) {
        this.mpk = mpk;
        return this;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public Team setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Team setClient(Client client) {
        this.client = client;
        return this;
    }

    private Team setClient(String clientName){
        if(client == null){
            client = new Client();
        }
        client.setName(clientName);
        return this;
    }

    public Fees getFees() {
        return fees;
    }

    public Team setFees(Fees fees) {
        this.fees = fees;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Team setProject(Project project) {
        this.project = project;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", manager=" + manager +
                ", teamMembers=" + teamMembers +
                ", methodology='" + methodology + '\'' +
                ", mpk='" + mpk + '\'' +
                ", client=" + client +
                ", fees=" + fees +
                ", technologies=" + technologies +
                '}';
    }
}
