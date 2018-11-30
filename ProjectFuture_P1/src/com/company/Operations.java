package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class Operations
{

    static void F1Operation()
    {
        ConnectionSql.open();
        String input = Utils.validatePlate();
        ConnectionSql.queryPlate(input);
        ConnectionSql.close();
    }

    static void F2Operation()
    {
        //User will provide the number of days for the timeframe
        int numOfDays;
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|\tGive desirable timeframe for insurance expiration inspection\t|");
        System.out.println("|-------------------------------------------------------------------|");
        Scanner daysScanner = new Scanner(System.in);
        numOfDays = daysScanner.nextInt();
        Date dateToBeChecked = Utils.DaysToDate(numOfDays);

        //Here will be added a loop in a result set instead of us giving just values
        String date="Mon Dec 01 12:53:10 EET 2018";
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
            Date expDate = sdf.parse(date);
            String samplePlate = "ABC-1234";
            Vehicle testVehicle = new Vehicle(samplePlate, expDate);
            if (testVehicle.ExpirationCheck(dateToBeChecked))
            {
                Utils.Menu.testVehicleList.add(testVehicle);
            }
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }
    }

    static void F3Operation()
    {
        String date="Sun Nov 25 12:53:10 EET 2018";
        String[] samplePlate = { "JBC-1234", "SBA-1234", "FAC-1234", "-D234", "XBC-1234", "EBC-1234", "KBC-1234", "BBC-1234", "NBC-1234", "ABC-1234"};
        //cycle through the vehicle result set
        for (int i=0; i < 10; ++i)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                Date expDate = sdf.parse(date);
                Vehicle testVehicle = new Vehicle(samplePlate[i], expDate);
                //for every vehicle, make the expiration check with the date now
                if (testVehicle.ExpirationCheck(new Date()))
                {
                    //if true add it to the expiredList
                    Utils.Menu.testExpiredList.add(testVehicle);
                }
            }
            catch (java.text.ParseException e)
            {
                e.printStackTrace();
            }
        }

        Utils.Menu.testExpiredList.sort(Vehicle::compareTo);
        for (Vehicle v :  Utils.Menu.testExpiredList)
        {
            System.out.println(v.getPlate());
        }
    }


    public static void F4Operation()
    {

    }
}
