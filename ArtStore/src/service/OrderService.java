package service;

import entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getByCustomer(int CustomerID);
    Order add(Order order);
    boolean sold(int artworkID);

}
