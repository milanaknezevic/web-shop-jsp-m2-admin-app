package com.example.adminapp.models;

public class Category {
    private Integer id;
    private String naziv;

    public Category() {
    }

    public Category(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
