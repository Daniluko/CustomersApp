package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMERS", schema = "MA_STUDENT")
public class Customer implements java.io.Serializable {

    @Id
    @Column(name = "CUST_NUM")
    private BigDecimal custNum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUST_REP")
    private Salesrep custRep;
    @Column(name = "CREDIT_LIMIT")
    private BigDecimal creditLimit;
    @Column(name = "COMPANY")
    private String company;

    public Customer() {
    }

    public Customer(BigDecimal custNum) {
        this.custNum = custNum;
    }

    public Customer(BigDecimal custNum, Salesrep custRep, BigDecimal creditLimit, String company) {
        this.custNum = custNum;
        this.custRep = custRep;
        this.creditLimit = creditLimit;
        this.company = company;
    }

    public BigDecimal getCustNum() {
        return custNum;
    }

    public void setCustNum(BigDecimal cust_num) {
        this.custNum = cust_num;
    }

    public Salesrep getCustRep() {
        return custRep;
    }

    public void setCustRep(Salesrep custRep) {
        this.custRep = custRep;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal credit_limit) {
        this.creditLimit = credit_limit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustNum(), customer.getCustNum()) &&
                Objects.equals(getCustRep(), customer.getCustRep()) &&
                Objects.equals(getCreditLimit(), customer.getCreditLimit()) &&
                Objects.equals(getCompany(), customer.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustNum(), getCustRep(), getCreditLimit(), getCompany());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custNum=" + custNum +
                ", custRep=" + custRep +
                ", creditLimit=" + creditLimit +
                ", company='" + company + '\'' +
                '}';
    }
}