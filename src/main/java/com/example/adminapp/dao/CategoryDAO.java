package com.example.adminapp.dao;

import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Category;
import com.example.adminapp.models.Product;
import com.example.adminapp.util.ConnectionPool;
import com.example.adminapp.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM webshop_ip.kategorija;";

    private static final String SELECT_BY_ID = "SELECT * FROM webshop_ip.kategorija k where k.id=?;";

    private static final String SELECT_BY_NAME = "SELECT * FROM webshop_ip.kategorija k where k.naziv=?;";
    private static final String INSERT = "INSERT INTO webshop_ip.kategorija (naziv) VALUES (?);";
    private static final String UPDATE = "UPDATE webshop_ip.kategorija k SET naziv=? WHERE k.id=?;";

    private static final String DELETE_CATEGORY = "DELETE FROM webshop_ip.kategorija WHERE id = ?;";
    //sve atribute i proizvode neke kategorije
    private static final String GET_CATEGORY = "SELECT slika.id as slika_id,komentar.id as komentar_id, p.id AS proizvod_id, p.naslov AS proizvod_naslov,a.id AS atribut_id,a.naziv AS atribut_naziv,a.tip AS atribut_tip,k.naziv AS kategorija_naziv,k.id AS kategorija_id FROM webshop_ip.kategorija k LEFT JOIN webshop_ip.proizvod p ON k.id = p.kategorija_id LEFT JOIN webshop_ip.atribut a ON k.id = a.kategorija_id LEFT JOIN webshop_ip.slika ON p.id = slika.proizvod_id LEFT JOIN webshop_ip.komentar ON p.id = komentar.proizvod_id;";
    private static final String GET_CATEGORY_BY_ID = "SELECT slika.id as slika_id,komentar.id as komentar_id, p.id AS proizvod_id, p.naslov AS proizvod_naslov,a.id AS atribut_id,a.naziv AS atribut_naziv,a.tip AS atribut_tip,k.naziv AS kategorija_naziv,k.id AS kategorija_id FROM webshop_ip.kategorija k LEFT JOIN webshop_ip.proizvod p ON k.id = p.kategorija_id LEFT JOIN webshop_ip.atribut a ON k.id = a.kategorija_id LEFT JOIN webshop_ip.slika ON p.id = slika.proizvod_id LEFT JOIN webshop_ip.komentar ON p.id = komentar.proizvod_id WHERE k.id =?;";

    public CategoryDAO() {
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_ALL_CATEGORIES, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("naziv"));
                category.setAtributi(AttributeDAO.getAttributesById(category.getId()));
                category.setProizvodi(ProductDAO.getProductsById(category.getId()));
                categories.add(category);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return categories;
    }

    public static Category getCategoryById(Integer id) {
        Category category = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_BY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("naziv"));
                category.setAtributi(AttributeDAO.getAttributesById(category.getId()));
                category.setProizvodi(ProductDAO.getProductsById(category.getId()));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return category;
    }

    public static Category getCategoryByName(String name) {
        Category category = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DAOUtil.prepareStatement(c, SELECT_BY_NAME, false);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("naziv"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return category;
    }

    public static boolean insert(Category category) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, INSERT, false);
            preparedStatement.setString(1, category.getNaziv());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }

    public static boolean updateCategory(Category category) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE, false);
            preparedStatement.setString(1, category.getNaziv());
            preparedStatement.setInt(2, category.getId());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }

    public static void deleteCategory(Category category) {
        Connection c = null;
        PreparedStatement ps = null;

        try {

            for (Attribute attribute : category.getAtributi()) {
                AttributeDAO.deleteAttribute(attribute.getId());
            }
            for (Product product : category.getProizvodi()) {
                ProductDAO.deleteProduct(product);
            }
            c = connectionPool.checkOut();
            ps = DAOUtil.prepareStatement(c, DELETE_CATEGORY, false);
            ps.setInt(1, category.getId());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }

}
