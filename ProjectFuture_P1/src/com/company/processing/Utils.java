package com.company.processing;

import com.company.entities.Vehicle;
import com.company.input.Menu;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Scanner;

public class Utils
{
    public static class UtilMethods
    {
        //Given a number of days, return the corresponding Date
        public static Date DaysToDate(int numberOfDays)
        {
            Date myDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.DATE, numberOfDays);
            myDate = cal.getTime();
            return myDate;
        }

        //Check if the String toBeChecked had the pattern "LLL-NNNN", where L is letter and N is number
        public static boolean PatternCheck(String toBeChecked)
        {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{3}-[0-9]{4}$");
            Matcher matcher = pattern.matcher(toBeChecked);
            if (matcher.find())
            {
                return true;
            }
            else
            {
                System.out.println("Plate match not found");
                return false;
            }
        }

        //Export info to file
        public static void ExportToFile()
        {
            System.out.println("Please enter a name for the file : ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            try
            {
                PrintWriter writer = new PrintWriter(fileName);
                for (Vehicle aVehicle : Menu.MenuMethods.goingToExpireList)
                {
                    writer.println(aVehicle.getPlate());
                }
                writer.close();
            }
            catch (java.io.FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        //Export info to console
        public static void ExportToConsole()
        {
            for (Vehicle aVehicle : Menu.MenuMethods.goingToExpireList)
            {
                System.out.println(aVehicle.getPlate());
            }
        }
    }
}