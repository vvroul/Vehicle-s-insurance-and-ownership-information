package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class Operations
{

    public static void F1Operation()
    {

    }

    static void F2Operation()
    {
        //User will provide the number of days for the timeframe
        int numOfDays;
        System.out.println("Please provide the number of days : ");
        Scanner daysScanner = new Scanner(System.in);
        numOfDays = daysScanner.nextInt();
        //System.out.println("The number of days is : " + numOfDays);
        Date dateToBeChecked = Utils.DaysToDate(numOfDays);

        //Here will be added a loop in a result set instead of us giving just values
        String date="Mon Dec 01 12:53:10 EET 2018";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
            Date expDate = sdf.parse(date);
            String samplePlate = "ABC-1234";
            Vehicle testVehicle = new Vehicle(samplePlate, expDate);
            if (testVehicle.ExpirationCheck(samplePlate, dateToBeChecked))
            {
                //Owner testOwner = new Owner("Bill", "Vrouliotis", 2567);
                Utils.Menu.testVehicleList.add(testVehicle);
            }
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static void F3Operation()
    {

    }

    public static void F4Operation()
    {

    }
}
