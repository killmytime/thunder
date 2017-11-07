package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Order {
    private int orderId;
    private Integer customerId;
    private Timestamp dateCreated;
    private Timestamp dateCompleted;
    private Integer artworkID;

    @OneToOne
    private Order order;

    public Order() {
    }

    public Order(int orderID) {
        this.orderId = orderID;
    }

    public Order(int orderID, int customerID, Timestamp dateCreated, Timestamp dateCompleted, int artworkID) {
        this.orderId = orderID;
        this.customerId = customerID;
        this.dateCreated = dateCreated;
        this.dateCreated = dateCompleted;
        this.artworkID = artworkID;
    }

    @Id
    @Column(name = "OrderID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CustomerID")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "DateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    @Basic
    @Column(name = "DateCompleted")
    public Timestamp getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Timestamp dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Basic
    @Column(name = "ArtworkID")
    public Integer getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(Integer artworkID) {
        this.artworkID = artworkID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (customerId != null ? !customerId.equals(order.customerId) : order.customerId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(order.dateCreated) : order.dateCreated != null) return false;
        if (dateCompleted != null ? !dateCompleted.equals(order.dateCompleted) : order.dateCompleted != null)
            return false;
        if (artworkID != null ? !artworkID.equals(order.artworkID) : order.artworkID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateCompleted != null ? dateCompleted.hashCode() : 0);
        result = 31 * result + (artworkID != null ? artworkID.hashCode() : 0);
        return result;
    }
}
