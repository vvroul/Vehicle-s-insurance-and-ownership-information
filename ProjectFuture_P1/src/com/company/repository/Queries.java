package com.company.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                ResultSet resultSet = statement.executeQuery("select * from vehicle where plate = "+"'"+input+"'"))
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
    }
}
