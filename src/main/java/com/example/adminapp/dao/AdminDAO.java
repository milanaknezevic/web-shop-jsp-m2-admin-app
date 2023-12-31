package com.example.adminapp.dao;

import com.example.adminapp.models.Admin;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM webshop_ip.admin WHERE BINARY korisnicko_ime=?;";

    private AdminDAO() {
    }

    public static Admin getUserByUsername(String korisnicko_ime) {
        Admin admin = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_USER_BY_USERNAME, false);
            ps.setString(1, korisnicko_ime);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = new Admin(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("korisnicko_ime"),
                        rs.getString("lozinka"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return admin;
    }
}
