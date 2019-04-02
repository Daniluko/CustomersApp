package springboot.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SALESREPS", schema = "MA_STUDENT")
public class Salesrep implements java.io.Serializable {
    @Id
    @Column(name = "EMPL_NUM")
    private BigDecimal emplNum;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private BigDecimal age;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REP_OFFICE")
    private Office repOffice;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "HIRE_DATE")
    private Date hireDate;
    @Column(name = "MANAGER")
    private BigDecimal manager;
    @Column(name = "QUOTA")
    private BigDecimal quota;
    @Column(name = "SALES")
    private BigDecimal sales;

    public Salesrep() {
    }

    public Salesrep(BigDecimal emplNum) {
        this.emplNum = emplNum;
    }

    public Salesrep(BigDecimal emplNum, String name, BigDecimal age, Office repOffice, String title, Date hireDate,
                     BigDecimal manager, BigDecimal quota, BigDecimal sales) {
        this.emplNum = emplNum;
        this.name = name;
        this.age = age;
        this.repOffice = repOffice;
        this.title = title;
        this.hireDate = hireDate;
        this.manager = manager;
        this.quota = quota;
        this.sales = sales;
    }

    public BigDecimal getEmplNum() {
        return emplNum;
    }

    public void setEmplNum(BigDecimal emplNum) {
        this.emplNum = emplNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public Office getRepOffice() {
        return repOffice;
    }

    public void setRepOffice(Office repOffice) {
        this.repOffice = repOffice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getHirerDate() {
        return hireDate;
    }

    public void setHirerDate(Date hirerDate) {
        this.hireDate = hirerDate;
    }

    public BigDecimal getManager() {
        return manager;
    }

    public void setManager(BigDecimal manager) {
        this.manager = manager;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Salesreps{" + "emplNum=" + emplNum + ", name='" + name + '\'' + ", age=" + age + ", repOffice="
                + repOffice + ", title='" + title + '\'' + ", hireDate=" + hireDate + ", manager=" + manager
                + ", quota=" + quota + ", sales=" + sales + '}';
    }
}