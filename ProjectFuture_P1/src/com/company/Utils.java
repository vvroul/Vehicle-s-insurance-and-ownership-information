package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import java.util.Scanner;

public class Utils
{
    static  class Menu
    {
        static Scanner scanner = new Scanner(System.in);
        static int choice = 0;
        static List<Vehicle> testVehicleList = new LinkedList<>();

        /*public Menu()
        {

        }*/

        static void display()
        {
            do {
                System.out.println("---Select functionality to perform---");
                System.out.println("*1 com.company.Vehicle Insurance Status");
                System.out.println("*2 Forecoming Expiries");
                System.out.println("*3 Expiries by plate");
                System.out.println("*4 Quit");
                System.out.println("Give your choice ");
                choice = evaluateInput(1, 3);
                switch (choice) {
                    case 1:

                        break;
                    case 2:
                        Operations.F2Operation();
                        subMenu();
                        break;
                    case 3:

                        break;
                    default:
                        break;
                }

            } while (choice != 4);
        }

        static int evaluateInput(int min, int max)
        {
            int input = scanner.nextInt();
            while ((input < min) || (input > max))
            {
                if (input > max)
                {
                    return input;
                }
                System.out.println("Try again");
                input = scanner.nextInt();
            }
            return input;
        }

        static void subMenu()
        {

            System.out.println("---Enter export format--");
            System.out.println("*1 File");
            System.out.println("*2 Console");
            System.out.println("Give your choice ");

            switch (evaluateInput(1, 2)) {
                case 1:
                    ExportToFile(choice);
                    break;
                case 2:
                    ExportToConsole(choice);
                    break;
                default:
                    break;
            }
        }
    }

    //Given a number of days, return the corresponding Date
    static Date DaysToDate(int numberOfDays)
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
            String upTo3Characters = toBeChecked.substring(0, Math.min(toBeChecked.length(), 3)).toUpperCase();
            String rest = toBeChecked.substring(upTo3Characters.length(), Math.min(toBeChecked.length(), toBeChecked.length()));
            String upperCaseString = upTo3Characters + rest;
            System.out.println(upTo3Characters);
            System.out.println(upperCaseString);
            //System.out.println(matcher.group(0)); //prints /{item}/
            return true;
        }
        else
        {
            System.out.println("Plate match not found");
            return false;
        }
    }

    private static void ExportToFile(int opChoice)
    {
        if (opChoice == 2)
        {
            System.out.println("Please enter a name for the file : ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            System.out.println("Filename : " + fileName);
            try {
                PrintWriter writer = new PrintWriter(fileName);
                for (Vehicle aVehicle : Menu.testVehicleList)
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
    }

    private static void ExportToConsole(int opChoice)
    {
        if (opChoice == 2)
        {
            for (Vehicle aVehicle : Menu.testVehicleList)
            {
                System.out.println(aVehicle.getPlate());
            }
        }
    }
}

