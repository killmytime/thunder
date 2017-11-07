package dao.impl;

import dao.TypesframesDao;
import entity.Typesframes;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypesframesDaoImpl implements TypesframesDao {
    private JdbcUtil util;
    public TypesframesDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public Typesframes getOne(int FrameID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM typesframes WHERE FrameID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Typesframes typesframes = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, FrameID);
            rs = pst.executeQuery();
            if (rs.next()) {
                typesframes = buildTypesframe(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return typesframes;
    }
    private Typesframes buildTypesframe(ResultSet rs)throws SQLException{
        //FrameID,Title,Price,Color,Style
        return new Typesframes(rs.getInt("FrameID"),rs.getString("Title"),
                rs.getBigDecimal("Price"),rs.getString("Color"),
                rs.getString("Style"));
    }
}
