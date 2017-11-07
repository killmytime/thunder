package dao.impl;

import dao.ArtworkSubjectsDao;
import entity.Artworksubjects;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtworkSubjectsDaoImpl  implements ArtworkSubjectsDao {
    private JdbcUtil util;
    public  ArtworkSubjectsDaoImpl(){this.util=new JdbcUtil();}
    @Override
    public Artworksubjects getOne(int ArtWorkID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM artworksubjects WHERE ArtWorkID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Artworksubjects artworksubjects=null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ArtWorkID);
            rs = pst.executeQuery();
            if (rs.next()) {
                artworksubjects= buildArtworksubjects(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artworksubjects;
    }
    private Artworksubjects buildArtworksubjects(ResultSet rs)throws SQLException{
        //ArtWorkSubjectID,ArtWorkID,SubjectID
        return new Artworksubjects(rs.getInt("ArtWorkSubjectID"),
                rs.getInt("ArtWorkID"),rs.getInt("SubjectID"));
    }
}
