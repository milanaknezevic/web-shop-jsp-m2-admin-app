package com.example.adminapp.dao;

import com.example.adminapp.models.Log;
import com.example.adminapp.models.User;
import com.example.adminapp.models.enums.Role;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_LOGS = "SELECT * FROM loger;";

    public LogDAO() {
    }

    public static List<Log> getAllLogs() {
        List<Log> logs = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_LOGS, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(new Log(rs.getInt("id"), rs.getString("poruka"), rs.getString("level"),
                        rs.getDate("datum"), rs.getString("log")
                ));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return logs;
    }
}
