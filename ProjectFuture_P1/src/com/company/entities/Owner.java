package com.company.entities;

public class Owner
{
    private String ownerName;
    private String ownerSurname;
    private int ownerID;

    public Owner(String ownerName, String ownerSurname, int ownerID)
    {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.ownerID = ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
