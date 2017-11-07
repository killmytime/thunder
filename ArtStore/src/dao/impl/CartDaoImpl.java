package dao.impl;

import dao.CartDao;
import entity.Cart;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    JdbcUtil util;
    public CartDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public Cart add(Cart cart){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "INSERT INTO cart( CustomerID, ArtworkID,DateCreated)" +
                " VALUES (?,?,?)"; // 11
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,cart.getCustomerId());
            pst.setInt(2,cart.getArtworkId());
            pst.setTimestamp(3,cart.getDateCreated());
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
        return flag ? cart : null;
    }
    @Override
    public void delete(int cartID) {
        Connection conn = util.getConnection();
        String sql = "DELETE FROM cart WHERE CartID = ?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cartID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
    }
    @Override
    public List<Cart> getByCustomer(int customerID){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM cart";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Cart> carts = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                carts.add(buildCart(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return carts;
    }
    @Override
    public Cart getOne(int cartID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM cart WHERE CartID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Cart cart = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cartID);
            rs = pst.executeQuery();
            if (rs.next()) {
                cart = buildCart(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return cart;
    }
    @Override
    public Cart getByArtwork(int artworkID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM cart WHERE ArtworkID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Cart cart = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, artworkID);
            rs = pst.executeQuery();
            if (rs.next()) {
                cart = buildCart(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return cart;
    }
    private Cart buildCart(ResultSet rs) throws SQLException{
        //CartID,CustomerID,ArtworkID,DateCreated
        return new Cart(rs.getInt("CartID"),rs.getInt("CustomerID"),
                rs.getInt("ArtworkID"),rs.getTimestamp("DateCreated"));
    }
}
