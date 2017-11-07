package dao.impl;

import dao.TypesglassDao;
import entity.Typesglass;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypesglassDaoImpl implements TypesglassDao{
    private JdbcUtil util;
    public TypesglassDaoImpl(){this.util=new JdbcUtil();}

    @Override
    public Typesglass getOne(int GlassID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM typesglass WHERE GlassID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Typesglass typesglass = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, GlassID);
            rs = pst.executeQuery();
            if (rs.next()) {
                typesglass = buildTypesglass(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return typesglass;
    }
    private Typesglass buildTypesglass(ResultSet rs)throws SQLException{
        //GlassID,Title,Description,Price
        return new Typesglass(rs.getInt("GlassID"),rs.getString("Title"),
                rs.getString("Description"),rs.getBigDecimal("Price"));
    }
}
