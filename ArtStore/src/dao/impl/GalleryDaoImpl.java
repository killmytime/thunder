package dao.impl;

import entity.Gallery;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleryDaoImpl {
    private JdbcUtil util;

    private GalleryDaoImpl() {
        this.util = new JdbcUtil();
    }

    private Gallery buildGAllery(ResultSet rs) throws SQLException {
        //GalleryID,GalleryName,GalleryNA=ativeName,GalleryCity,GalleryCountry,Latitude,Longitude,GalleryWebSite
        return new Gallery(rs.getInt("GalleryID"), rs.getString("GalleryName"),
                rs.getString("GalleryNativeName"), rs.getString("GalleryCity"),
                rs.getString("GalleryCountry"), rs.getDouble("Latitude"),
                rs.getDouble("Longitude"), rs.getString("GalleryWebSite"));
    }
}
