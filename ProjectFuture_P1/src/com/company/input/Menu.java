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
        public static List<Vehicle> testVehicleList = new ArrayList<>();
        public static List<Vehicle> testExpiredList = new ArrayList<>();

        public static void display()
        {
            do {
                System.out.println("---Select functionality to perform---");
                System.out.println("*1 Vehicle Insurance Status");
                System.out.println("*2 Forecoming Expiries");
                System.out.println("*3 Expiries by plate");
                System.out.println("*4 Quit");
                System.out.println("Give your choice ");
                choice = evaluateInput(1, 3);
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
                    default:
                        break;
                }

            } while (choice != 4);
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
            System.out.println("---Enter export format--");
            System.out.println("*1 File");
            System.out.println("*2 Console");
            System.out.println("Give your choice ");

            switch (evaluateInput(1, 2)) {
                case 1:
                    Utils.ExportToFile();
                    break;
                case 2:
                    Utils.ExportToConsole();
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
}
