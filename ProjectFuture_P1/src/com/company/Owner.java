package com.company;

import java.util.*;

public class Owner
{
    private String ownerName;
    private String ownerSurname;
    private int ownerID;
    //private List<Vehicle> uninsVehicleList;

    public Owner(String ownerName, String ownerSurname, int ownerID) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.ownerID = ownerID;
        //this.uninsVehicleList = uninsVehicleList;
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

    /*public List<Vehicle> getUninsVehicleList() {
        return uninsVehicleList;
    }

    public void setUninsVehicleList(List<Vehicle> uninsVehicleList) {
        this.uninsVehicleList = uninsVehicleList;
    }*/


}
