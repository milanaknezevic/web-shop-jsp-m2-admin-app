package com.example.adminapp.models;

public class Image {
    private Integer id;

    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                '}';
    }
}
