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
    public static int toValue(Status value)
    {
        if(value ==REQUESTED)
        {
            return 0;
        }
        else if(value ==ACTIVE)
        {
            return 1;
        } else if (value ==BLOCKED) {
            return 2;
        } else {
            throw new IllegalArgumentException("Nepoznata vrednost za Status: " + value);
        }
    }
}
