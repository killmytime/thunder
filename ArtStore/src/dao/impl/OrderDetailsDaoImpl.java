package dao.impl;

import dao.OrderDetailsDao;
import entity.Orderdetails;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao{
    private JdbcUtil util;
    public OrderDetailsDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public List<Orderdetails> getByOrderID(int orderID){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM orderdetails WHERE OrderID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Orderdetails> orderdetails= new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, orderID);
            rs = pst.executeQuery();
            while (rs.next()) {
                orderdetails.add(buildOrderDetails(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return orderdetails;
    }
    @Override
    public List<Orderdetails> sold(int artworkID){
        Connection conn=util.getConnection();
        //language=MySQL
        String sql="SELECT * FROM orderdetails WHERE ArtWorkID=?";
        PreparedStatement pst=null;
        ResultSet rs=null;
        List<Orderdetails> orderdetails=new ArrayList<>();
        try{
            pst=conn.prepareStatement(sql);
            pst.setInt(1,artworkID);
            rs=pst.executeQuery();
            while (rs.next()){
                orderdetails.add(buildOrderDetails(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return orderdetails;
    }
    @Override
    public Orderdetails add(Orderdetails orderdetails) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "INSERT INTO orderdetails(OrderDetailsID, ArtWorkID, OrderID, FrameID, MattID, GlassID)" +
                " VALUES (?,?,?,?,?,?)"; // 6
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,orderdetails.getOrderDetailsId());
            pst.setInt(2,orderdetails.getArtWorkId());
            pst.setInt(3,orderdetails.getOrderId());
            pst.setInt(4,orderdetails.getFrameId());
            pst.setInt(5,orderdetails.getMattId());
            pst.setInt(6,orderdetails.getGlassId());
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
        return flag ? orderdetails : null;
    }
    @Override
    public void delete(int artworkID,int orderID) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "DELETE FROM orderdetails WHERE ArtWorkID=? AND OrderID=?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, artworkID);
            pst.setInt(2,orderID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
    }
    @Override
    public Orderdetails getOne(int artworkID){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM orderdetails WHERE ArtWorkID = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Orderdetails orderdetails = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, artworkID);
            rs =  pst.executeQuery();
            if (rs.next()) {
                orderdetails= buildOrderDetails(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return orderdetails;
    }
    private Orderdetails buildOrderDetails(ResultSet rs) throws SQLException {
        //OrderDetailsID,ArtWorkID,OrderID,FrameID,MattID,GlassID
        return new Orderdetails(rs.getInt("OrderDetailsID"),rs.getInt("ArtWorkID"),
                rs.getInt("OrderID"),rs.getInt("FrameID"),
                rs.getInt("MattID"),rs.getInt("GlassID"));
    }
}
