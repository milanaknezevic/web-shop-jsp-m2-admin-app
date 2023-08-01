package com.example.adminapp.models;

public class Comment {
    private Integer id;

    public Comment() {
    }

    public Comment(Integer id) {
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
        return "Commentar{" +
                "id=" + id +
                '}';
    }
}
