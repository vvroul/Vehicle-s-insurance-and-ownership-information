package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import java.util.Scanner;
import java.text.*;

public class Utils
{
    public static  class Menu
    {
        static Scanner scanner = new Scanner(System.in);
        static int choice = 0;
        static List<Vehicle> testVehicleList = new LinkedList<>();

        /*public Menu()
        {

        }*/

        //We might need those
        public static Scanner getScanner()
        {
            return scanner;
        }

        public static void setScanner(Scanner scanner)
        {
            Menu.scanner = scanner;
        }

        public static int getChoice() {
            return choice;
        }

        public static void setChoice(int choice)
        {
            Menu.choice = choice;
        }

        public static void display()
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
                        //User will provide the number of days for the timeframe
                        int numOfDays;
                        System.out.println("Please provide the number of days : ");
                        Scanner daysScanner = new Scanner(System.in);
                        numOfDays = daysScanner.nextInt();
                        System.out.println("The number of days is : " + numOfDays);
                        Date dateToBeChecked = DaysToDate(numOfDays);
                        System.out.println(dateToBeChecked);

                        //here will come a loop in a result set instead of us giving just values
                        String date="Mon Dec 01 12:53:10 EET 2018";
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                            Date expDate = sdf.parse(date);
                            System.out.println("Expdate : " + expDate);
                            String samplePlate = "ABC-1234";
                            Vehicle testVehicle = new Vehicle(samplePlate, expDate);
                            if (testVehicle.ExpirationCheck(samplePlate, dateToBeChecked) == true)
                            {
                                Owner testOwner = new Owner("Bill", "Vrouliotis", 2567);
                                testVehicleList.add(testVehicle);
                            }
                        }
                        catch (java.text.ParseException e)
                        {
                            e.printStackTrace();
                        }
                        subMenu();
                        break;
                    case 3:

                        break;
                    default:
                        break;
                }

            } while (choice != 4);
        }

        public static int evaluateInput(int min, int max)
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

        public static void subMenu()
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
            System.out.println(matcher.group(0)); //prints /{item}/
            return true;
        }
        else
        {
            System.out.println("Plate match not found");
            return false;
        }
    }

    public static void ExportToFile(int opChoice)
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

    public static void ExportToConsole(int opChoice)
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

