package com.company;

import java.util.*;

public class Vehicle
{
    private String vehicleName;
    private int ownerID;
    private String plate;
    private Date ins_start;
    private Date ins_end;

    public Vehicle(String plate, Date ins_end) {
        this.plate = plate;
        this.ins_end = ins_end;
        System.out.println("Vehicle object initialized!");
    }

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public Vehicle(String vehicleName, int ownerID, String plate, Date ins_start, Date ins_end) {
        this.vehicleName = vehicleName;
        this.ownerID = ownerID;
        this.plate = plate;
        this.ins_start = ins_start;
        this.ins_end = ins_end;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getIns_start() {
        return ins_start;
    }

    public void setIns_start(Date ins_start) {
        this.ins_start = ins_start;
    }

    public Date getIns_end() {
        return ins_end;
    }

    public void setIns_end(Date ins_end) {
        this.ins_end = ins_end;
    }


    public void ExpirationCheck(String plate, Date dateToCheck)
    {
        System.out.println("In the expiration check for F2");
        System.out.println("Plate : " + plate + "  Expiration Date : " + ins_end + "  DateToBeChecked : " + dateToCheck);
        if(dateToCheck.compareTo(ins_end)>0)
        {
            System.out.println("Expired insurance");
        }
        else if(dateToCheck.compareTo(ins_end)<0)
        {
            System.out.println("Active insurance");
        }
        else {
            System.out.println("Last day of insurance, don't add to list");
        }
    }
}
