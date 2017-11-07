package dao.impl;

import dao.SubjectDao;
import entity.Subject;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDaoImpl implements SubjectDao {
    private JdbcUtil util;
    public SubjectDaoImpl() {
        this.util = new JdbcUtil();
    }

    @Override
    public Subject getOne(int SubjectID){
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM subject WHERE SubjectId=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Subject subject= null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, SubjectID);
            rs = pst.executeQuery();
            if (rs.next()) {
                subject = buildSubject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }

        return subject;
    }
    private Subject buildSubject(ResultSet rs) throws SQLException {
        //SubjectId,SubjectName
        return new Subject(rs.getInt("SubjectId"),rs.getString("SubjectName"));
    }
}
