package dao.impl;

import dao.TypesshippersDao;
import entity.Typesshippers;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypesshippersDaoImpl implements TypesshippersDao {
    private JdbcUtil util;
    public TypesshippersDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public Typesshippers getOne(int shipperID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM typesshippers WHERE shipperID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Typesshippers typesshippers= null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, shipperID);
            rs = pst.executeQuery();
            if (rs.next()) {
                typesshippers = buildTypesShipers(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return typesshippers;
    }
    private Typesshippers buildTypesShipers(ResultSet rs)throws SQLException{
        //shipperID,shipperName,shipperDescription,shipperAvgTime,shipperClass,shipperBaseFee,shipperWeightFee
        return new Typesshippers(rs.getInt("shipperID"),rs.getString("shipperName"),
                rs.getString("shipperDescription"),rs.getString("shipperAvgTime"),
                rs.getInt("shipperClass"),rs.getBigDecimal("shipperBaseFee"),
                rs.getBigDecimal("shipperWeightFee"));
    }
}
