package com.company;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main
{

    public static void main(String[] args)
    {
        Utils.Menu.display();
        /*String entoli = "SELECT ID, first_name, last_name FROM owner";

        try (Connection conn = MYSQLConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(entoli))
        {
            while (rs.next())
            {
                System.out.println(rs.getString("ID") + "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name"));

            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }*/
    }
}

