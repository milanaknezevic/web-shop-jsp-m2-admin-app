package com.example.adminapp.models;

import com.example.adminapp.models.enums.Role;
import com.example.adminapp.models.enums.Status;
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
    @Getter
    private String grad;
    @Getter
    private String avatar;
    @Getter
    private String email;
    @Getter
    private Role role;
    @Getter
    private Status status;
    private boolean isLoggedIn;

    public Admin() {
    }

    public Admin(Integer id, String ime, String prezime, String korisnicko_ime, String lozinka, String grad, String avatar, String email, Role role, Status status, boolean isLoggedIn) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.grad = grad;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
        this.status = status;
        this.isLoggedIn = isLoggedIn;
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

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", korisnicko_ime='" + korisnicko_ime + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", grad='" + grad + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
