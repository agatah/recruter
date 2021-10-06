package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @SequenceGenerator(name = "client_generator", sequenceName = "client_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @JsonIgnore
    private Long id;

    private String name;
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Embedded
    private Contact contact;

    public Client(){

    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Client setLocation(String location) {
        this.location = location;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Client setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public Client setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                '}';
    }
}
