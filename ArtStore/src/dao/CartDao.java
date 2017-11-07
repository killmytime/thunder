package dao;

import entity.Cart;

import java.util.List;

public interface CartDao {
    Cart add(Cart cart);
   void delete(int cartID);
   List<Cart> getByCustomer(int customerID);
   Cart getOne(int cartID);
   Cart getByArtwork(int artworkID);
}
