package com.example.adminapp.dao;

import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProizvodAtributDAO {
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String DELETE = "DELETE FROM webshop_ip.proizvod_atribut WHERE atribut_id=?;";


    public static void deleteAttributeValue(Integer attributeId) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DAOUtil.prepareStatement(c, DELETE, false);
            ps.setInt(1, attributeId);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
}
