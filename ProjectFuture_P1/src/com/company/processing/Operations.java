package com.company.processing;

import com.company.input.Menu;
import com.company.input.Validations;
import com.company.repository.ConnectionSql;
import com.company.entities.Vehicle;
import com.company.repository.Queries;

import java.text.SimpleDateFormat;
import java.util.*;

public class Operations
{

    public static void F1Operation()
    {
        ConnectionSql.ConnectionMethods.open();
        String input = Validations.ValidationMethods.validatePlate();
        Queries.QueriesMethods.queryPlate(input);
        ConnectionSql.ConnectionMethods.close();
    }

    public static void F2Operation()
    {
        //User will provide the number of days for the timeframe
        int numOfDays;
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|\tGive desirable timeframe for insurance expiration inspection\t|");
        System.out.println("|-------------------------------------------------------------------|");
        Scanner daysScanner = new Scanner(System.in);
        numOfDays = daysScanner.nextInt();
        //Turn the given number of days to corresponding date
        Date dateToBeChecked = Utils.UtilMethods.DaysToDate(numOfDays);
        ConnectionSql.ConnectionMethods.open();
        Queries.QueriesMethods.queryF2(dateToBeChecked);
        ConnectionSql.ConnectionMethods.close();
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
