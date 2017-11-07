package service;

import entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getByCustomer(int customerID);
    Cart add(int customerID,int artworkID);
    boolean artworkCanBeAdded(int customerID,int artworkID);
    void delete(int cartID);
    Cart getOne(int cartID);
}
