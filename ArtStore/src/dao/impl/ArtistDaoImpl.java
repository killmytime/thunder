package dao.impl;

import dao.ArtistDao;
import entity.Artist;
import util.JdbcUtil;
import util.PageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl implements ArtistDao{
    private JdbcUtil util;
    public ArtistDaoImpl() {
        this.util = new JdbcUtil();
    }
    @Override
    public List<Artist> getAll() {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artist";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artist> artists = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artists.add(buildArtist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artists;
    }
    @Override
    public Artist getOne(int ArtistID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM artist WHERE ArtistID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Artist artist = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ArtistID);
            rs = pst.executeQuery();
            if (rs.next()) {
                artist = buildArtist(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artist;
    }
    @Override
    public List<Integer> getArtistByfilter(String keyWord) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artist WHERE FirstName LIKE '%"+keyWord+"%' OR LastName  LIKE '%"+keyWord+"%'";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Integer> artistIDs = new ArrayList<>();

        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artistIDs.add(rs.getInt("ArtistID"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
        return artistIDs;
    }

    private Artist buildArtist(ResultSet rs) throws SQLException {
        //ArtistID,FirstName,LastName,Nationality,YearOfBirth,YearOfDeath,Details,ArtistLink
        return new Artist(rs.getInt("ArtistID"), rs.getString("FirstName"),
                rs.getString("LastName"), rs.getString("Nationality"),
                rs.getInt("YearOfBirth"), rs.getInt("YearOfDeath"),
                rs.getString("Details"),rs.getString("ArtistLink")
                );
    }
}
