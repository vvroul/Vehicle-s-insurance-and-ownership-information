package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MYSQLConnector {

        public static  Connection getConnection() throws SQLException{
            Connection conn = null;

            try (FileInputStream arxeio = new FileInputStream("StoixeiaVasis.properties"))
            {
                Properties stoixeia = new Properties();
                stoixeia.load(arxeio);

                String url = stoixeia.getProperty("url");
                String user = stoixeia.getProperty("user");
                String password = stoixeia.getProperty("password");

                conn = DriverManager.getConnection(url, user, password);
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            return conn;
        }
}