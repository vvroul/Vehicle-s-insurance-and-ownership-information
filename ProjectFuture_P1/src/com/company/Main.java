package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{

    public static void main(String[] args)
    {
        //Utils.Menu.display();

        //Uncomment these lines to check the functionality of the methods
        //System.out.println(Utils.DaysToDate(10));
        //Utils.PatternCheck("PKA-5139");

        String entoli = "SELECT owner_id as o, count(owner_id) as c FROM vehicle GROUP BY owner_id";

        try (Connection conn = MYSQLConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(entoli))
        {
            while (rs.next())
            {
                System.out.println(rs.getString("o") + "\t" +
                                   rs.getString("c"));

            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
