package com.company.repository;

import com.company.entities.Vehicle;
import com.company.input.Menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class Queries
{
    public static class QueriesMethods
    {
        //Query that returns the insurance status of a given query
        public static void queryF1(String input)
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
                    String thePlate = resultSet.getString("plate");
                    Vehicle testVehicle = new Vehicle(thePlate, exp_date);
                    if (testVehicle.ExpirationCheck(dateToBeChecked))
                    {
                        Menu.MenuMethods.goingToExpireList.add(testVehicle);
                    }
                }
            }
            catch (SQLException e)
            {
                System.err.println("Something went wrong");
                e.printStackTrace();
            }
        }

        public static List<Vehicle> queryF3()
        {
            if (ConnectionSql.ConnectionMethods.connection == null)
            {
                System.out.println("There's no connection to a database");
                return null;
            }
            ArrayList<Vehicle> list = new ArrayList<>();
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String nowSql = formatter.format(now);
            try (Statement statement = ConnectionSql.ConnectionMethods.connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("select plate, ins_date_end from vehicle where " +
                         "'" + nowSql + "' > ins_date_end or ins_date_end is null "))
            {
                while (resultSet.next())
                {
                    String plate = resultSet.getString("plate");
                    Date end = resultSet.getDate("ins_date_end");
                    Vehicle vehicle = new Vehicle(plate, end);
                    list.add(vehicle);
                }
            }
            catch (SQLException e)
            {
                System.out.println("Something went wrong!" + e.getMessage());
                e.printStackTrace();
            }
            return list;
        }

        public static int queryF4(int owner_id)
        {
            if (ConnectionSql.ConnectionMethods.connection == null)
            {
                System.err.println("There's no connection to a database");
            }
            try (Statement statement = ConnectionSql.ConnectionMethods.connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT o.first_name, o.last_name, v.plate, v.ins_date_end " +
                         "from owner o " +
                         "inner join vehicle v on o.id = v.owner_id " +
                         "where o.id = " + "'" + owner_id + "'")) {
                int uninsuredVehicles = 0;
                String[] fullName = new String[2];
                while (resultSet.next())
                {
                    Date exp_date = resultSet.getDate("ins_date_end");
                    String thePlate = resultSet.getString("plate");
                    fullName[0] = resultSet.getString("first_name");
                    fullName[1] = resultSet.getString("last_name");
                    if (exp_date == null)
                    {
                        ++uninsuredVehicles;
                        continue;
                    }

                    if (exp_date.compareTo(new Date()) < 0)
                    {
                        ++uninsuredVehicles;
                    }
                }
                if (uninsuredVehicles != 0)
                {
                    System.out.println("|-------------------------------------------------------------------|");
                    System.out.println("|\tGive desirable fine for total fine calculation\t\t\t\t\t| ");
                    System.out.println("|-------------------------------------------------------------------|");
                    Scanner fineScanner = new Scanner(System.in);
                    int finePrice = fineScanner.nextInt();
                    int total_fine = finePrice * uninsuredVehicles;
                    return total_fine;
                }
                else
                {
                    System.out.println(fullName[1] + " " + fullName[0] + " has all vehicles insured.");
                    return 0;
                }
            }
            catch (SQLException e)
            {
                System.out.println("Something went wrong!" + e.getMessage());
                e.printStackTrace();
            }
            return -1;
        }
    }
}
