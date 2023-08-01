package com.example.adminapp.dao;

import com.example.adminapp.models.Comment;
import com.example.adminapp.models.Product;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_BY_CATEGORY_ID = "SELECT * FROM webshop_ip.proizvod a where a.kategorija_id=?;";


    public static List<Product> getProductsById(Integer id) {
        List<Product> products = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_BY_CATEGORY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product(rs.getInt("id"));
                product.setSlike(ImageDAO.getImagesById(product.getId()));
                product.setKomentari(CommentarDAO.getCommentsById(product.getId()));
                products.add(product);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return products;
    }
}
