package com.example.adminapp.beans;

import com.example.adminapp.dao.AdminDAO;
import com.example.adminapp.models.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

public class AdminBean implements Serializable {
    private boolean isLoggedIn = false;

    public AdminBean() {
    }

    public boolean login(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Admin admin = AdminDAO.getUserByUsername(username);

        if (admin != null && bCryptPasswordEncoder.matches(password, admin.getLozinka())) {
            isLoggedIn = true;
            return true;
        } else {
            return false;
        }
        // return admin != null && bCryptPasswordEncoder.matches(password, admin.getLozinka());

    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }


}
