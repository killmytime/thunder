package dao.impl;

import dao.GenreDao;
import entity.Genre;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDaoImpl implements GenreDao {
    private JdbcUtil util;

    public GenreDaoImpl() {
        this.util = new JdbcUtil();
    }
    @Override
    public Genre getOne(int GenreID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM genre WHERE GenreID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Genre genre = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, GenreID);
            rs = pst.executeQuery();
            if (rs.next()) {
                genre = buildGenre(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return genre;
    }
    private Genre buildGenre(ResultSet rs) throws SQLException {
        //GenreID,GenreName,Era,Description,Link
        return new Genre(rs.getInt("GenreID"),rs.getString("GenreName"),
                rs.getInt("Era"),rs.getString("Description"),
                rs.getString("Link"));
    }
    }

