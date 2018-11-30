package com.company.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionSql
{
    public static class ConnectionMethods
    {
        public static Connection connection;

        //Open database connection
        public static boolean open()
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
            finally
            {
                System.out.println("Connected");
            }
            return true;
        }

        //Close database connection
        public static void close()
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
    }
}
