package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "OFFICES",schema = "MA_STUDENT")
public class Office {
    @Id
    @Column(name = "OFFICE")
    private BigDecimal office;
    @Column(name = "CITY")
    private String city;
    @Column(name = "REGION")
    private String region;
    @Column(name = "TARGET")
    private BigDecimal target;
    @Column(name = "SALES")
    private BigDecimal sales;

    public Office() {
    }

    public Office(BigDecimal office) {
        this.office = office;
    }

    public Office(BigDecimal office, String city, String region, BigDecimal target, BigDecimal sales) {
        this.office = office;
        this.city = city;
        this.region = region;
        this.target = target;
        this.sales = sales;
    }

    public BigDecimal getOffice() {
        return office;
    }

    public void setOffice(BigDecimal office) {
        this.office = office;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Office{" +
                "office=" + office +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", target=" + target +
                ", sales=" + sales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office1 = (Office) o;
        return Objects.equals(getOffice(), office1.getOffice()) &&
                Objects.equals(getCity(), office1.getCity()) &&
                Objects.equals(getRegion(), office1.getRegion()) &&
                Objects.equals(getTarget(), office1.getTarget()) &&
                Objects.equals(getSales(), office1.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOffice(), getCity(), getRegion(), getTarget(), getSales());
    }
}