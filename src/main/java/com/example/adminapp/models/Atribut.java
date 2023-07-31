package com.example.adminapp.models;

public class Atribut {
    private Integer id;
    private String naziv;
    private Integer kategorija_id;
    private String tip;

    public Atribut() {
    }

    public Atribut(Integer id, String naziv, Integer kategorija_id, String tip) {
        this.id = id;
        this.naziv = naziv;
        this.kategorija_id = kategorija_id;
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

    public Integer getKategorija_id() {
        return kategorija_id;
    }

    public void setKategorija_id(Integer kategorija_id) {
        this.kategorija_id = kategorija_id;
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
                ", kategorija_id=" + kategorija_id +
                ", tip='" + tip + '\'' +
                '}';
    }
}
