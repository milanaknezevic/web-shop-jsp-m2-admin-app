package com.example.adminapp.beans;

import com.example.adminapp.dao.LogDAO;
import com.example.adminapp.models.Log;

import java.io.Serializable;
import java.util.List;

public class LogBean implements Serializable {

    private Log log;

    public List<Log> getAll() {
        return LogDAO.getAllLogs();
    }

}
