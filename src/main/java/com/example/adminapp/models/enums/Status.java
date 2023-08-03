package com.example.adminapp.models.enums;

public enum Status {
    REQUESTED,ACTIVE,BLOCKED;

    public static Status fromValue(int value)
    {
        if(value ==0)
        {
            return REQUESTED;
        }
        else if(value ==1)
        {
            return ACTIVE;
        } else if (value ==2) {
            return BLOCKED;
        } else {
            throw new IllegalArgumentException("Nepoznata vrednost za Status: " + value);
        }
    }
}
