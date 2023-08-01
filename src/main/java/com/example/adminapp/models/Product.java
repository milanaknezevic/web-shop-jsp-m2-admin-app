package com.example.adminapp.models;

import java.util.List;

public class Product {
    private Integer id;
    private List<Comment> komentari;
    private List<Image> slike;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Comment> komentari) {
        this.komentari = komentari;
    }

    public List<Image> getSlike() {
        return slike;
    }

    public void setSlike(List<Image> slike) {
        this.slike = slike;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", komentari=" + komentari +
                ", slike=" + slike +
                '}';
    }
}
