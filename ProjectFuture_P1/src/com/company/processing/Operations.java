package com.company.processing;

import com.company.input.Menu;
import com.company.repository.ConnectionSql;
import com.company.entities.Vehicle;

import java.text.SimpleDateFormat;
import java.util.*;

public class Operations
{

    public static void F1Operation()
    {
        ConnectionSql.open();
        String input = Utils.validatePlate();
        ConnectionSql.queryPlate(input);
        ConnectionSql.close();
    }

    public static void F2Operation()
    {
        //User will provide the number of days for the timeframe
        int numOfDays;
        System.out.println("Please provide the number of days : ");
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
                Menu.MenuMethods.testVehicleList.add(testVehicle);
            }
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static void F3Operation()
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
                    Menu.MenuMethods.testExpiredList.add(testVehicle);
                }
            }
            catch (java.text.ParseException e)
            {
                e.printStackTrace();
            }
        }

        Menu.MenuMethods.testExpiredList.sort(Vehicle::compareTo);
        for (Vehicle v : Menu.MenuMethods.testExpiredList)
        {
            System.out.println(v.getPlate());
        }
    }


    public static void F4Operation()
    {

    }
}
