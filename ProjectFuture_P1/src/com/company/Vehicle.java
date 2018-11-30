package com.company;

import java.util.*;

public class Vehicle implements Comparable<Vehicle>
{
    private int v_id;
    private String plate;
    private int ownerID;
    private Date ins_start;
    private Date ins_end;

    Vehicle(String plate, Date ins_end)
    {
        this.plate = plate;
        this.ins_end = ins_end;
    }

    public Vehicle(int v_id, int ownerID, String plate, Date ins_start, Date ins_end) {
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

    String getPlate() {
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


    boolean ExpirationCheck(Date dateToCheck)
    {
        if(dateToCheck.compareTo(ins_end)>0)
        {
            //Expired insurance
            return true;
        }
        else if(dateToCheck.compareTo(ins_end)<0)
        {
            //Active insurance
            return false;
        }
        else
        {
            //Not quiet possible (active at the last day)
            return false;
        }
    }

    @Override
    public int compareTo(Vehicle v)
    {
        return plate.compareTo(v.plate);
    }
}
