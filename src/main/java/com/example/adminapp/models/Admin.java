package com.example.adminapp.models;

import lombok.Getter;

public class Admin {
    @Getter
    private Integer id;
    @Getter
    private String ime;
    @Getter
    private String prezime;
    @Getter
    private String korisnicko_ime;
    @Getter
    private String lozinka;



    public Admin() {
    }

    public Admin(Integer id, String ime, String prezime, String korisnicko_ime, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnicko_ime='" + korisnicko_ime + '\'' +
                ", lozinka='" + lozinka + '\'' +
                '}';
    }
}
