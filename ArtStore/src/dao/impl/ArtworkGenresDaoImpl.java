package dao.impl;

import dao.ArtworkGenresDao;
import entity.Artwork;
import entity.Artworkgenres;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtworkGenresDaoImpl implements ArtworkGenresDao {
    private JdbcUtil util;

    public ArtworkGenresDaoImpl() {
        this.util = new JdbcUtil();
    }
    @Override
    public Artworkgenres getOne(int ArtWorkID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM artworkgenres WHERE ArtWorkID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Artworkgenres artworkgenres = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ArtWorkID);
            rs = pst.executeQuery();
            if (rs.next()) {
                artworkgenres = buildArtworkgenres(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artworkgenres;
    }
    private Artworkgenres buildArtworkgenres(ResultSet rs)throws SQLException{
        //ArtWorkGenreID,ArtWorkID,GenreID
        return new Artworkgenres(rs.getInt("ArtWorkGenreID"),
                rs.getInt("ArtWorkID"),rs.getInt("GenreID"));
    }
}
