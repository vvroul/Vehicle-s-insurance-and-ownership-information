package com.company.input;

import com.company.processing.Utils;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations
{
    public static class ValidationMethods
    {
        //Asks the user for a plate and calls the PatternCheck method to check for its pattern
        public static String validatePlate()
        {
            String plate;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Give Plate to be checked (ex. ABC-1234): ");
            do
            {
                plate = scanner.nextLine().toUpperCase();
            }while(!Utils.UtilMethods.PatternCheck(plate));
            return plate;
        }

        //Ensures that the user will press a number instead of something else
        static int validateInt()
        {
            int input=0;
            try
            {
                while(!Menu.MenuMethods.scanner.hasNextInt())
                {
                    Menu.MenuMethods.scanner.next();
                    System.out.println("Please enter a number");
                }
                input = Menu.MenuMethods.scanner.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a number");
            }
            return input;
        }

        //Ensures the input will be valid for the number of operations' options
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
    }
}
