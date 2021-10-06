package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "tool")
public class Tool {

    @Id
    @SequenceGenerator(name = "tool_generator", sequenceName = "tool_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_generator")
    @JsonIgnore
    private Long id;

    private String name;

    public Tool(){

    }

    public Tool(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
