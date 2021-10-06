package pl.unity.vgp.recruter.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @SequenceGenerator(name = "salary_generator", sequenceName = "salary_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_generator")
    @JsonIgnore
    private Long id;

    @JsonProperty("brutto")
    private BigDecimal gross;

    @JsonProperty("netto")
    private BigDecimal net;

    private String contractType;

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = new BigDecimal(gross);
        this.net = this.gross.multiply(BigDecimal.valueOf(0.78));
    }

    public void setGross(BigDecimal gross){
        this.gross = gross;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = new BigDecimal(net);
        this.gross = this.net.divide(BigDecimal.valueOf(0.78), BigDecimal.ROUND_HALF_EVEN);
    }

    public void setNet(BigDecimal net){
        this.net = net;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", gross=" + gross +
                ", net=" + net +
                ", contractType='" + contractType + '\'' +
                '}';
    }
}
