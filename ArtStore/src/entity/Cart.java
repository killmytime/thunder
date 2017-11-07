package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
public class Cart {
    private Integer cartId;
    private Integer customerId;
    private Integer artworkId;
    private Timestamp dateCreated;

    @OneToOne
    private Cart cart;
    public Cart(){}
    public Cart(int cartId){this.cartId=cartId;}
    public Cart(int cartId,int customerId,int artworkId,Timestamp dateCreated){
        this.cartId=cartId;
        this.customerId=customerId;
        this.artworkId=artworkId;
        this.dateCreated=dateCreated;
    }
    @Basic
    @Column(name = "CartID")
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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
    @Column(name = "ArtworkID")
    public Integer getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Integer artworkId) {
        this.artworkId = artworkId;
    }

    @Basic
    @Column(name = "DateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (cartId != null ? !cartId.equals(cart.cartId) : cart.cartId != null) return false;
        if (customerId != null ? !customerId.equals(cart.customerId) : cart.customerId != null) return false;
        if (artworkId != null ? !artworkId.equals(cart.artworkId) : cart.artworkId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(cart.dateCreated) : cart.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (artworkId != null ? artworkId.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
