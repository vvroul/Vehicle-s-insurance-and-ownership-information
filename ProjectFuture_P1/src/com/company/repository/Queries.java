package com.company.repository;

import com.company.entities.Vehicle;
import com.company.input.Menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Queries
{
    public static class QueriesMethods
    {
        //Query that returns the insurance status of a given query
        public static void queryPlate(String input)
        {
            if(ConnectionSql.ConnectionMethods.connection==null)
            {
                System.out.println("There's no connection to a database");
                return;
            }
            try(Statement statement = ConnectionSql.ConnectionMethods.connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select plate, ins_date_end from vehicle where plate = "+"'"+input+"'"))
            {
                if(resultSet.next())
                {
                    Date now = new Date();
                    Date date = resultSet.getDate("ins_date_end");
                    if(date==null)
                    {
                        System.out.println("The vehicle has no insurance");
                        return;
                    }
                    System.out.println("The vehicle with plate number : "+resultSet.getString("plate") +
                            " " + (now.after(date)?"has an expired insurance, and the insurance expired at ":
                            "has a valid insurance, and the insurance expires at ")+date);
                }
                else
                {
                    System.out.println("No vehicle has such plate");
                }
            }
            catch (SQLException e)
            {
                System.out.println("something went wrong" +e.getMessage());
                e.printStackTrace();
            }
        }

        //More queries to be added here :
        public static void queryF2(Date dateToBeChecked)
        {
            if(ConnectionSql.ConnectionMethods.connection==null)
            {
                System.out.println("There's no connection to a database");
                return;
            }
            try(Statement statement = ConnectionSql.ConnectionMethods.connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select plate, ins_date_end from vehicle"))
            {
                while(resultSet.next())
                {
                    Date exp_date = resultSet.getDate("ins_date_end");
                    if(exp_date==null)
                    {
                        System.out.println("The vehicle has no insurance");
                    }
                    String thePlate = resultSet.getString("plate");
                    Vehicle testVehicle = new Vehicle(thePlate, exp_date);
                    if (testVehicle.ExpirationCheck(dateToBeChecked))
                    {
                        Menu.MenuMethods.testVehicleList.add(testVehicle);
                        System.out.println("The vehicle with plate : " + thePlate + " is about to have expired insurance.");
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Something went wrong");
                e.printStackTrace();
            }
        }

        public static void queryF3()
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
    }


}
