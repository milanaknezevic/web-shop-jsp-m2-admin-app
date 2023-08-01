package com.example.adminapp.dao;

import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Image;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO
{

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_BY_PRODUCT_ID="SELECT * FROM webshop_ip.slika a where a.proizvod_id=?;";


    public static List<Image> getImagesById(Integer id) {
        List<Image> images = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_BY_PRODUCT_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                images.add(new Image(rs.getInt("id")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return images;
    }
}