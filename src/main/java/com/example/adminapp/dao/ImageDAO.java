package com.example.adminapp.dao;

import com.example.adminapp.models.Image;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_BY_PRODUCT_ID = "SELECT * FROM webshop_ip.slika a where a.proizvod_id=?;";
    private static final String DELETE_IMAGE = "DELETE FROM webshop_ip.slika WHERE id=?;";


    public static List<Image> getImagesById(Integer id) {
        List<Image> images = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_BY_PRODUCT_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public static void deleteImage(Integer id) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DAOUtil.prepareStatement(c, DELETE_IMAGE, false);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
}
