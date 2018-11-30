package com.company;

import java.io.*;
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
        static List<Vehicle> testExpiredList = new LinkedList<>();

        static void display()
        {
            do {
                System.out.println("|-----------------------------------|");
                System.out.println("|   \tSelect Functionality\t\t|");
                System.out.println("|-----------------------------------|");
                System.out.println("|\t1. Vehicle Insurance Status \t|");
                System.out.println("|\t2. Forecoming Expiries\t\t\t|");
                System.out.println("|\t3. Expiries by plate\t\t\t|");
                System.out.println("|\t4. Total Fine\t\t\t\t\t|");
                System.out.println("|\t5. Quit\t\t\t\t\t\t\t|");
                System.out.println("|-----------------------------------|");
                choice = evaluateInput(1, 4);
                switch (choice) {
                    case 1:
                        Operations.F1Operation();
                        break;
                    case 2:
                        Operations.F2Operation();
                        subMenu();
                        break;
                    case 3:
                        Operations.F3Operation();
                        break;
                    case 4:
                        //Operations.F4Operation();
                        break;
                    default:
                        break;
                }

            } while (choice != 5);
        }

        static int evaluateInput (int min,int max)
        {
            int input;
            do
            {
                input = validateInt();
                if (input > max)
                {
                    return input;
                }
            }while((input<min)||(input>max));
            return input;
        }

        static void subMenu()
        {
            System.out.println("|-----------------------------------|");
            System.out.println("|   \tSelect Export Type\t\t\t|");
            System.out.println("|-----------------------------------|");
            System.out.println("|\t1. File\t\t\t\t\t\t\t|");
            System.out.println("|\t2. Console\t\t\t\t\t\t|");
            System.out.println("|-----------------------------------|");

            switch (evaluateInput(1, 2)) {
                case 1:
                    ExportToFile();
                    break;
                case 2:
                    ExportToConsole();
                    break;
                default:
                    break;
            }
        }

        static int validateInt()
        {
            int input=0;
            try {
                while(!scanner.hasNextInt()){
                    scanner.next();
                    System.out.println("Please enter a number");
                }
                input = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Please enter a number");
            }
            return input;
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
            return true;
        }
        else
        {
            System.out.println("Plate match not found");
            return false;
        }
    }

    //Export info to file
    private static void ExportToFile()
    {
        System.out.println("Please enter a name for the file : ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try
        {
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

    //Export info to console
    private static void ExportToConsole()
    {
        for (Vehicle aVehicle : Menu.testVehicleList)
        {
            System.out.println(aVehicle.getPlate());
        }
    }

    public static String validatePlate(){
        String plate;
        Scanner scanner = new Scanner(System.in);
        System.out.println("|-------------------------------------------------------------------|");
        System.out.println("|\tGive desirable vehicle plate number for insurance inspection\t| ");
        System.out.println("|-------------------------------------------------------------------|");
        do {
            plate = scanner.nextLine().toUpperCase();
        }while(!PatternCheck(plate));
        return plate;
    }
}