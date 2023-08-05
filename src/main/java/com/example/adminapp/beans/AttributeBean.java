package com.example.adminapp.beans;

import com.example.adminapp.dao.AttributeDAO;
import com.example.adminapp.dao.CategoryDAO;
import com.example.adminapp.dao.UserDAO;
import com.example.adminapp.models.Attribute;
import com.example.adminapp.models.Category;
import com.example.adminapp.models.User;

import java.io.Serializable;
import java.util.List;

public class AttributeBean implements Serializable {
    public AttributeBean() {
    }
    public List<Attribute> getAttributesById(Integer id) {
        return AttributeDAO.getAttributesById(id);
    }

    public boolean insertAttribute(Attribute attribute, Integer category_id) {
        return AttributeDAO.insertAttribute(attribute,category_id);
    }
    public boolean update(Attribute attribute) {
        return AttributeDAO.updateAttribute(attribute);
    }

    public void deleteAttribute( Integer id) {
       AttributeDAO.deleteAttribute(id);
    }
    public Attribute getAttributeById(Integer id){ return  AttributeDAO.getAttributeById(id);}

}
