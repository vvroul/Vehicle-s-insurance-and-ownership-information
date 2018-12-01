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

    /*public static void F3Operation()
    {
        ConnectionSql.open();
        Utils.Menu.ExpiredList = ConnectionSql.queryF3();
        Utils.Menu.ExpiredList.sort(Vehicle::compareTo);
        for (Vehicle v : Utils.Menu.ExpiredList) {
            if (v.getIns_end()==null){
                System.out.println(v.getPlate() + " was never insured");
            }else{
                System.out.println(v.getPlate() + " date expired at " +v.getIns_end());
            }
        }
        ConnectionSql.close();
    }*/

    public static void F4Operation()
    {

    }
}
