package pl.unity.vgp.recruter.domain.model;

import javax.persistence.*;

@Embeddable
public class ServiceFee {

    private String monthly;

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    @Override
    public String toString() {
        return "ServiceFee{" +
                ", monthly='" + monthly + '\'' +
                '}';
    }
}
