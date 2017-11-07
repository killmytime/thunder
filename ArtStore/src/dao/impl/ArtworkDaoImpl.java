package dao.impl;


import dao.ArtistDao;
import dao.ArtworkDao;
import dao.DaoFactory.DaoFactory;
import entity.Artwork;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtworkDaoImpl implements ArtworkDao {
    private JdbcUtil util;

    public ArtworkDaoImpl() {
        this.util = new JdbcUtil();
    }
    @Override
    public List<Artwork> getAll() {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artwork WHERE Status=0";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artwork> artworks = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artworks.add(buildArtWork(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artworks;
    }
    @Override
    public Artwork getOne(int ArtWorkID) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM artwork WHERE ArtWorkID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Artwork artwork = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ArtWorkID);
            rs = pst.executeQuery();
            if (rs.next()) {
                artwork = buildArtWork(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artwork;
    }
    @Override
    public List<Artwork> sortedByClick(){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artwork WHERE Status=0 ORDER BY ClickNumber DESC LIMIT 3";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artwork> artworks = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artworks.add(buildArtWork(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return artworks;
    }
    @Override
    public List<Artwork> sortedByNew(){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artwork ORDER BY ArtWorkID DESC LIMIT 3";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artwork> artworks = new ArrayList<>();
        try {
        pst = conn.prepareStatement(sql);
        rs =  pst.executeQuery();
        while (rs.next()) {
            artworks.add(buildArtWork(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        util.close(rs, pst, conn);
    }
        return artworks;
}
    @Override
    public List<Artwork> sortedInArtist(int ArtistID){
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * FROM artwork WHERE ArtistID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Artwork artwork = null;
        List<Artwork> artworks = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ArtistID);
            rs = pst.executeQuery();
            while (rs.next()) {
                artworks.add(buildArtWork(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
        return artworks;
    }
    @Override
    public void update(int artworkID,int clickNumber){
        Connection conn = util.getConnection();
        //ClickNumber
        //language=MySQL
        String sql = "UPDATE  artwork SET ClickNumber = ? " +
                "WHERE ArtWorkID = ?";
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, clickNumber);
            pst.setInt(2,artworkID);
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
    }
    @Override
    public void update(Artwork artwork){
        Connection conn = util.getConnection();
        //ClickNumber
        //language=MySQL
        String sql = "UPDATE  artwork SET Status=? " +
                "WHERE ArtWorkID = ?";
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, artwork.getStatus());
            pst.setInt(2,artwork.getArtWorkId());
            pst.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(null, pst, conn);
        }
    }
    @Override
    public List<Artwork> filterByTitle(String keyWord) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * from artwork WHERE Title LIKE '%"+keyWord+"%' AND Status=0";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artwork> artwork = new ArrayList<>();

        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artwork.add(buildArtWork(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
        return artwork;
    }

    @Override
    public List<Artwork> filterByDescription(String keyWord) {
        Connection conn = util.getConnection();
        //language=MySQL
        String sql = "SELECT * from artwork WHERE artwork.Description LIKE '%"+keyWord+"%'AND Status=0";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Artwork> artworks = new ArrayList<>();

        try {
            pst = conn.prepareStatement(sql);
            rs =  pst.executeQuery();
            while (rs.next()) {
                artworks.add(buildArtWork(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
        return artworks;
    }

    @Override
    public List<Artwork> filterByArtist(String keyWord) {
        Connection conn = util.getConnection();
        ArtistDao artistDao = DaoFactory.getArtistDaoInstance();
        List<Integer> artistIDs = artistDao.getArtistByfilter(keyWord);
        List<Artwork> artwork = new ArrayList<>();
        int num = artistIDs.size();

        for (int i = 0;i < num;i++) {
            //language=MySQL
            int artistID = artistIDs.get(i);
            String sql = "SELECT * from artwork WHERE ArtistID = ? AND Status=0";
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                pst = conn.prepareStatement(sql);
                pst.setInt(1,artistID);
                rs =  pst.executeQuery();
                while (rs.next()) {
                    artwork.add(buildArtWork(rs));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs, pst, conn);
            }
        }
        return artwork;
    }

    private Artwork buildArtWork(ResultSet rs) throws SQLException {
        // ArtWorkID,ArtistID,ImageFileName,title,Description,Excerpt,ArtWorkType,YearOfWork,Width,Height,Medium,OriginalHome,GalleryID,Cost,MSRP,ArtWorkLink,GoogleLink,ClickNumber,Status
        return new Artwork(rs.getInt("ArtWorkID"), rs.getInt("ArtistID"),
                rs.getString("ImageFileName"), rs.getString("Title"),
                rs.getString("Description"), rs.getString("Excerpt"),
                rs.getInt("ArtWorkType"), rs.getInt("YearOfWork"),
                rs.getInt("Width"), rs.getInt("Height"),
                rs.getString("Medium"), rs.getString("OriginalHome"),
                rs.getInt("GalleryID"), rs.getBigDecimal("Cost"),
                rs.getBigDecimal("MSRP"), rs.getString("ArtWorkLink"),
                rs.getString("GoogleLink"),rs.getInt("ClickNumber"),
                rs.getInt("Status"));
    }
}



