package com.example.adminapp.beans;

import com.example.adminapp.dao.AttributeDAO;
import com.example.adminapp.dao.UserDAO;
import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.User;

import java.io.Serializable;
import java.util.List;

public class AttributeBean implements Serializable {
    public AttributeBean() {
    }
    public List<Attribute> getAttributesById(Integer id) {
        return AttributeDAO.getAttributesById(id);
    }
    public boolean getAttributesById(Attribute attribute) {
        return AttributeDAO.updateAttribute(attribute);
    }
    public boolean insertAttribute(Attribute attribute, Integer category_id) {
        return AttributeDAO.insertAttribute(attribute,category_id);
    }
    public void deleteAttribute( Integer id) {
       AttributeDAO.deleteAttribute(id);
    }

}
