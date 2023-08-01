package com.example.adminapp.models;

import java.util.List;

public class Category {
    private Integer id;
    private String naziv;
    private List<Attribute> atributi;
    private List<Product> proizvodi;

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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Attribute> getAtributi() {
        return atributi;
    }

    public void setAtributi(List<Attribute> atributi) {
        this.atributi = atributi;
    }

    public List<Product> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Product> proizvodi) {
        this.proizvodi = proizvodi;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", atributi=" + atributi +
                ", proizvodi=" + proizvodi +
                '}';
    }
}
