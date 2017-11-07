package service.impl;

import dao.CartDao;
import dao.DaoFactory.DaoFactory;
import entity.Cart;
import service.CartService;

import java.sql.Timestamp;
import java.util.List;

public class CartServiceImpl implements CartService{
    private CartDao cartDao;
    public CartServiceImpl(){
        cartDao=DaoFactory.getCartDaoInstance();
    }
    @Override
    public List<Cart> getByCustomer(int customerID){
        return cartDao.getByCustomer(customerID);
    }
    @Override
    public Cart add(int customerID,int artworkID){
        Cart cart=new Cart();
            cart.setArtworkId(artworkID);
        cart.setCustomerId(customerID);
        cart.setDateCreated(new Timestamp(System.currentTimeMillis()));
        cartDao.add(cart);
        return cart;
    }
    @Override
    public boolean artworkCanBeAdded(int customerID,int artworkID){
        for (Cart cart:cartDao.getByCustomer(customerID)){
            if (cart.getArtworkId()==artworkID){
                return false;
            }
        }
        return true;
    }
    @Override
    public Cart getOne(int cartID){
        return cartDao.getOne(cartID);
    }
    @Override
    public void delete(int cartID){
        cartDao.delete(cartID);
    }
}
