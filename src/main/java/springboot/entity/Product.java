package springboot.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "MFR_ID")
    private String mfrId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "QTY_ON_HAND")
    private BigDecimal qtyOnHand;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    Set<Order> orders = new HashSet<Order>(0);

    public Product() {
    }

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String mfrId, String description, BigDecimal price, BigDecimal qtyOnHand,
                   Set<Order> orders) {
        this.productId = productId;
        this.mfrId = mfrId;
        this.description = description;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
        this.orders = orders;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMfrId() {
        return this.mfrId;
    }

    public void setMfrId(String mfrId) {
        this.mfrId = mfrId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQtyOnHand() {
        return this.qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", mfrId=" + mfrId + ", description=" + description + ", price="
                + price + ", qtyOnHand=" + qtyOnHand + "]";
    }
}