package com.example.adminapp.dao;

import com.example.adminapp.models.Attribute;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttributeDAO {
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String INSERT= "INSERT INTO  webshop_ip.atribut (naziv,kategorija_id,tip) values (?,?,?);";
    private static final String UPDATE="UPDATE web_shop.user set name=?, type=? where id=?";
    private static final String DELETE = "DELETE FROM webshop_ip.atribut WHERE id = ?;";
    private static final String SELECT_ALL_BY_CATEGORY_ID="SELECT * FROM webshop_ip.atribut a where a.kategorija_id=?;";

    public static boolean insertAttribute(Attribute attribute,Integer category_id) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DAOUtil.prepareStatement(c, INSERT, false);
            ps.setString(1,attribute.getNaziv());
            ps.setString(3,attribute.getTip());
            ps.setInt(2,category_id);
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static boolean updateAttribute(Attribute attribute) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DAOUtil.prepareStatement(c, UPDATE, false);
            ps.setString(1,attribute.getNaziv());
            ps.setString(2,attribute.getTip());
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static void deleteAttribute(Integer id) {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            ProizvodAtributDAO.deleteAttributeValue(id);
            c = connectionPool.checkOut();
            ps =DAOUtil.prepareStatement(c, DELETE, false);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
    public static List<Attribute> getAttributesById(Integer id) {
        List<Attribute> attributes = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_BY_CATEGORY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
           while(rs.next()) {
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
