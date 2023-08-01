package com.example.adminapp.models;

public class Attribute {
    private Integer id;
    private String naziv;

    private String tip;

    public Attribute() {
    }

    public Attribute(Integer id, String naziv, String tip) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
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


    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Atribut{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
