package dao.impl;

import dao.TypesmattDao;
import entity.Typesmatt;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypesmattDaoImpl implements TypesmattDao{
    private JdbcUtil util;
    public TypesmattDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public Typesmatt getOne(int mattID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM typesmatt WHERE MattID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Typesmatt typesmatt = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, mattID);
            rs = pst.executeQuery();
            if (rs.next()) {
                typesmatt = buildTypesmatt(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return typesmatt;
    }
    private Typesmatt buildTypesmatt(ResultSet rs)throws SQLException{
        //MattID,Title,ColorCode
        return new Typesmatt(rs.getInt("MattID"),rs.getString("Title"),
                rs.getString("ColorCode"));
    }
}
