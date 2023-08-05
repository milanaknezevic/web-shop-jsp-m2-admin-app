package com.example.adminapp.beans;

import com.example.adminapp.dao.CategoryDAO;
import com.example.adminapp.models.Category;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getAll() {
        return CategoryDAO.getAllCategories();
    }

    public static Category getById(Integer id) {
        return CategoryDAO.getCategoryById(id);
    }

    public static Category getByName(String name) {
        return CategoryDAO.getCategoryByName(name);
    }

    public int addCategory(Category category) {
        return CategoryDAO.insert(category);
    }

    public boolean update(Category category) {
        return CategoryDAO.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        CategoryDAO.deleteCategory(category);
    }

}
