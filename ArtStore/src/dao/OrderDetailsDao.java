package dao;

import entity.Orderdetails;

import java.util.List;

public interface OrderDetailsDao {
    //这里需要好好看看几个id的关系
    List<Orderdetails> getByOrderID(int OrderID);
    List<Orderdetails> sold(int artworkID);
    Orderdetails add(Orderdetails orderdetails);
    void delete(int artworkID,int orderID);
    Orderdetails getOne(int artworkID);
}
