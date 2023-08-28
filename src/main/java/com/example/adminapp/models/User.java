package com.example.adminapp.models;

import com.example.adminapp.models.enums.Status;

public class User {
    private Integer id;
    private String ime;
    private String prezime;
    private String korisnicko_ime;
    private String lozinka;
    private String grad;
    private String email;
    private Status status;


    public User() {
    }

    public User(Integer id, String ime, String prezime, String korisnicko_ime, String lozinka, String grad, String email, Status status) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.grad = grad;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnicko_ime='" + korisnicko_ime + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", grad='" + grad + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
