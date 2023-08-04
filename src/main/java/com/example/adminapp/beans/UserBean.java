package com.example.adminapp.beans;

import com.example.adminapp.dao.UserDAO;
import com.example.adminapp.models.User;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getAll() {
        return UserDAO.getAllUsers();
    }

    public User getById(int id) {
        return UserDAO.getUserById(id);
    }

    public boolean update(User user) {
        return UserDAO.updateUser(user);
    }

    public void changeStatus(User user) {
        UserDAO.updateUserStatus(user.getId(), user.getStatus());
    }

    public boolean addUser(User user) {
        return UserDAO.insertUser(user);
    }


}