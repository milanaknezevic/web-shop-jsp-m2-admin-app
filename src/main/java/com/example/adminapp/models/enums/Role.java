package com.example.adminapp.models.enums;

public enum Role {

    ADMIN,KORISNICKA_PODRSKA,OBICNI_KORISNIK;

    public static Role fromValue(int value) {
        if (value == 0) {
            return ADMIN;
        } if (value == 1) {
            return KORISNICKA_PODRSKA;
        } if (value == 2) {
            return OBICNI_KORISNIK;
        }
        else {
            throw new IllegalArgumentException("Nepoznata vrednost za Role: " + value);
        }
    }
    public static int toValue(Role value) {
        if (value == ADMIN) {
            return 0;
        } if (value == KORISNICKA_PODRSKA) {
            return 1;
        } if (value == OBICNI_KORISNIK) {
            return 2;
        }
        else {
            throw new IllegalArgumentException("Nepoznata vrednost za Role: " + value);
        }
    }

}
