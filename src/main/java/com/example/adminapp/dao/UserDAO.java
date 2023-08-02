package com.example.adminapp.dao;

import com.example.adminapp.models.User;
import com.example.adminapp.models.enums.Role;
import com.example.adminapp.models.enums.Status;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String SELECT_ALL_USERS = "SELECT * FROM korisnik;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM korisnik WHERE id=?;";
    private static final String UPDATE_USER = "UPDATE korisnik  SET ime=?, prezime=?, korisnicko_ime=?, lozinka=?, grad=?,avatar=?, email=?, status=? WHERE id=?;";
    private static final String UPDATE_USER_STATUS = "UPDATE korisnik  SET status=? WHERE id=?;";
    private static final String INSERT_USER = "INSERT INTO user (ime, prezime, korisnicko_ime, lozinka, grad, avatar, email,role, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public UserDAO() {
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_USERS, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnicko_ime"), rs.getString("lozinka"),
                        rs.getString("grad"), rs.getString("avatar"), rs.getString("email"), Role.valueOf(rs.getString("role")), Status.valueOf(rs.getString("status"))));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return users;
    }

    public static User getUserById(Integer id) {
        User user = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_USER_BY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("korisnicko_ime"), rs.getString("lozinka"),
                        rs.getString("grad"), rs.getString("avatar"), rs.getString("email"), Role.valueOf(rs.getString("role")), Status.valueOf(rs.getString("status")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return user;
    }

    public static boolean updateUser(User user) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE_USER, false);
            preparedStatement.setString(1, user.getIme());
            preparedStatement.setString(2, user.getPrezime());
            preparedStatement.setString(3, user.getKorisnicko_ime());
            preparedStatement.setString(4, passwordEncoder.encode(user.getLozinka()));
            preparedStatement.setString(5, user.getGrad());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getStatus().toString());
            preparedStatement.setInt(9, user.getId());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }

    public static void updateUserStatus(Integer id, Status status) {
        Connection connection = null;

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE_USER_STATUS, false);
            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
    }

    public static boolean insertUser(User user) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, INSERT_USER, false);
            preparedStatement.setString(1, user.getIme());
            preparedStatement.setString(2, user.getPrezime());
            preparedStatement.setString(3, user.getKorisnicko_ime());
            preparedStatement.setString(4, passwordEncoder.encode(user.getLozinka()));
            preparedStatement.setString(5, user.getGrad());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getRole().toString());
            preparedStatement.setString(9, user.getStatus().toString());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }
}
