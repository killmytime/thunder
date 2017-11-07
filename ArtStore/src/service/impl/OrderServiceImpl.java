package service.impl;

import dao.DaoFactory.DaoFactory;
import dao.OrderDao;
import entity.Order;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    public OrderServiceImpl(){
        orderDao= DaoFactory.getOrderDaoInstance();
    }
    @Override
    public List<Order> getByCustomer(int customerID){
        return orderDao.getByCustomer(customerID);
    }
    @Override
    public Order add(Order order){return orderDao.add(order);}
    @Override
    public boolean sold(int artworkID){
        if (orderDao.getOne(artworkID)!=null){
            //System.out.println("被买了");
            return true;
        }
        //System.out.println("没被买");
        return false;
    }
}
