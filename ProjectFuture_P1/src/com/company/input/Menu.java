package com.company.input;

import com.company.processing.Operations;
import com.company.processing.Utils;
import com.company.entities.Vehicle;

import java.util.*;

public class Menu
{
    public static  class MenuMethods
    {
        static Scanner scanner = new Scanner(System.in);
        static int choice = 0;
        public static List<Vehicle> goingToExpireList = new ArrayList<>();
        public static List<Vehicle> expiredList = new ArrayList<>();
        public static int totalFine;

        //Asks the user for the operation to be resolved
        public static void display()
        {
            do
            {
                System.out.println("|-----------------------------------|");
                System.out.println("|   \tSelect Functionality\t\t|");
                System.out.println("|-----------------------------------|");
                System.out.println("|\t1. Vehicle Insurance Status \t|");
                System.out.println("|\t2. Forecoming Expires\t\t\t|");
                System.out.println("|\t3. Expires by plate\t\t\t\t|");
                System.out.println("|\t4. Total Fine\t\t\t\t\t|");
                System.out.println("|\t5. Quit\t\t\t\t\t\t\t|");
                System.out.println("|-----------------------------------|");
                choice = Validations.ValidationMethods.evaluateInput(1, 4);
                switch (choice)
                {
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
                        Operations.F4Operation();
                        break;
                    default:
                        break;
                }

            }while (choice != 5);
        }

        //Asks the user for the export format
        static void subMenu()
        {
            System.out.println("|-----------------------------------|");
            System.out.println("|   \tSelect Export Type\t\t\t|");
            System.out.println("|-----------------------------------|");
            System.out.println("|\t1. File\t\t\t\t\t\t\t|");
            System.out.println("|\t2. Console\t\t\t\t\t\t|");
            System.out.println("|-----------------------------------|");

            switch (Validations.ValidationMethods.evaluateInput(1, 2))
            {
                case 1:
                    Utils.UtilMethods.ExportToFile();
                    break;
                case 2:
                    Utils.UtilMethods.ExportToConsole();
                    break;
                default:
                    break;
            }
        }
    }
}
