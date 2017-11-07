package dao.impl;

import entity.Typesstatuscodes;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypesstatuscodesDaoImpl {
    private JdbcUtil util;
    public TypesstatuscodesDaoImpl(){this.util=new JdbcUtil();}
    private Typesstatuscodes buildTypesstatuscodes(ResultSet rs)throws SQLException{
        //StatusID,Status
        return new Typesstatuscodes(rs.getInt("StatusID"),rs.getString("Status"));
    }
}
