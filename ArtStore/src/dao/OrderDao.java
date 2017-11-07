package dao;

import entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getByCustomer(int customerID);
    Order add(Order order);
    Order getOne(int artworkID);
}
