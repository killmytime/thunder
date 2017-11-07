package dao.impl;

import entity.Review;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDaoImpl {
    private JdbcUtil util;
    public ReviewDaoImpl(){this.util=new JdbcUtil();}
    private Review buildReview(ResultSet rs)throws SQLException{
       //ReviewID,ArtWorkId,CustomerId,ReviewDate,Rating,comment
       return new Review(rs.getInt("Review"),rs.getInt("ArtWorkId"),
               rs.getInt("CustomerId"),rs.getTimestamp("ReviewDate"),
               rs.getInt("Rating"),rs.getString("comment"));
    }
}
