package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "fees")
public class Fees {

    @Id
    @SequenceGenerator(name = "fees_generator", sequenceName = "fees_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fees_generator")
    @JsonIgnore
    private Long id;

    private String hourly;

    @Embedded
    private ServiceFee serviceFee;

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    public ServiceFee getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(ServiceFee serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "id=" + id +
                ", hourly='" + hourly + '\'' +
                ", serviceFee=" + serviceFee +
                '}';
    }
}
