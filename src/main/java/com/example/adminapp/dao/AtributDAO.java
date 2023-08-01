package com.example.adminapp.dao;

import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Category;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtributDAO {
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_BY_CATEGORY_ID="SELECT * FROM webshop_ip.atribut a where a.kategorija_id=?;";


    public static List<Attribute> getAttributesById(Integer id) {
        List<Attribute> attributes = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_BY_CATEGORY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                attributes.add(new Attribute(rs.getInt("id"), rs.getString("naziv"),rs.getString("tip")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return attributes;
    }

}
