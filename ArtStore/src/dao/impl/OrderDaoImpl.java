package dao.impl;

import dao.OrderDao;
import entity.Order;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private JdbcUtil util;
    public OrderDaoImpl() {
        this.util = new JdbcUtil();
    }
    @Override
    public List<Order> getByCustomer(int customerID) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM `order` WHERE CustomerID=? ";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Order> artworks = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, customerID);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artworks.add(buildOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artworks;
    }
    @Override
    public Order add(Order order){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "INSERT INTO `order`(OrderID,CustomerID,DateCreated,DateCompleted,ArtworkID)" +
                " VALUES (?,?,?,?,?)"; // 5
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,order.getOrderId());
            pst.setInt(2,order.getCustomerId());
            pst.setTimestamp(3,order.getDateCreated());
            pst.setTimestamp(4,order.getDateCompleted());
            pst.setInt(5,order.getArtworkID());
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
        return flag ? order : null;
    }
    @Override
    public Order getOne(int artworkID){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM `order` WHERE ArtWorkID = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Order order = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, artworkID);
            rs =  pst.executeQuery();
            if (rs.next()) {
                order= buildOrder(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return order;
    }
    private Order buildOrder(ResultSet rs)throws SQLException {

        //OrderID,CustomerID,DataCreated,DataCompleted,ArtworkID
        return new Order(rs.getInt("OrderID"), rs.getInt("CustomerID"),
                rs.getTimestamp("DateCreated"),rs.getTimestamp("DateCompleted"),
                rs.getInt("ArtworkID"));
    }
}
