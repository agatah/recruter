package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;

@Entity
@Table(name = "framework")
public class Framework {

    @Id
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @JsonIgnore
    private Long id;

    @JacksonXmlProperty(localName = "name")
    private String name;


    public Framework(){

    }

    public Framework(String name){
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
        return "Framework{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
