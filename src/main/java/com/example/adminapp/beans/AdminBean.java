package com.example.adminapp.beans;

import com.example.adminapp.dao.AdminDAO;
import com.example.adminapp.models.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

public class AdminBean implements Serializable {

    public AdminBean() {
    }

    public Admin getUser(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Admin admin = AdminDAO.getUserByUsername(username);

        return admin != null && bCryptPasswordEncoder.matches(password, admin.getLozinka()) ? admin : null;
    }


}
