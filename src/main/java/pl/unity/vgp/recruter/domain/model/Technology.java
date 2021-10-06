package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.unity.vgp.recruter.dataImport.deserializer.JsonUnwrapProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "technology")
public class Technology {

    @Id
    @SequenceGenerator(name = "technology_generator", sequenceName = "technology_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technology_generator")
    @JsonIgnore
    private Long id;

    private String name;
    private String percentage;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("frameworks")
    @JsonUnwrapProperty
    private List<Framework> frameworks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tool> tools = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Technology setName(String name) {
        this.name = name;
        return this;
    }

    public String getPercentage() {
        return percentage;
    }

    public Technology setPercentage(String percentage) {
        this.percentage = percentage;
        return this;
    }

    public List<Framework> getFrameworks() {
        return frameworks;
    }

    public Technology setFrameworks(List<Framework> frameworks) {
        this.frameworks = frameworks;
        return this;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public Technology setTools(List<Tool> tools) {
        this.tools = tools;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Technology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage='" + percentage + '\'' +
                ", frameworks=" + frameworks +
                ", tools=" + tools +
                '}';
    }
}
