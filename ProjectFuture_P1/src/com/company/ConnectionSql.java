package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

class ConnectionSql {

    private static Connection connection;

    //Open database connection
    static boolean open()
    {
        try (FileInputStream fileInputStream = new FileInputStream("StoixeiaVasis.properties"))
        {
            Properties props = new Properties();
            props.load(fileInputStream);
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (IOException IOex)
        {
            System.out.println(IOex.getMessage());
            return false;
        }
        catch (SQLException SQLex)
        {
            System.out.println("SQL exception occurred. Couldn't connect to database.");
            return false;
        }
        return true;
    }


    //Close database connection
    static void close()
    {
        try
        {
            if(connection!=null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Could not close connection"+e.getMessage());
        }
    }


    //Query that returns the insurance status of a given query
    static void queryPlate(String input)
    {
        if(connection==null)
        {
            System.out.println("There's no connection to a database");
            return;
        }
        try(Statement statement = connection.createStatement();
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
}
