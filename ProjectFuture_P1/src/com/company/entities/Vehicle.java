package com.company.entities;

import java.util.*;

public class Vehicle implements Comparable<Vehicle>
{
    private int v_id;
    private int ownerID;
    private String plate;
    private Date ins_start;
    private Date ins_end;

    public Vehicle(String plate, Date ins_end)
    {
        this.plate = plate;
        this.ins_end = ins_end;
    }

    public Vehicle(int v_id, int ownerID, String plate, Date ins_start, Date ins_end)
    {
        this.v_id = v_id;
        this.ownerID = ownerID;
        this.plate = plate;
        this.ins_start = ins_start;
        this.ins_end = ins_end;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
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

    //Checks the insurance status of the given date
    public boolean ExpirationCheck(Date dateToCheck)
    {
        System.out.println("Going for the check");
        Date now = new Date();

        if (ins_end == null)
        {
            return false;
        }

        if ((now.compareTo(ins_end) <0) && (ins_end.compareTo(dateToCheck)<0))
        {
            //Expired insurance
            return true;
        }
        else
        {
            //Not quiet possible (active at the last day)
            return false;
        }
    }

    @Override
    //Compare method for Vehicle class
    public int compareTo(Vehicle v)
    {
        return plate.compareTo(v.plate);
    }
}
