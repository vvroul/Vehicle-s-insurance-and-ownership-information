package com.company;

import java.sql.*;
import java.util.Date;

public class ConnectionSql {

    public static final String USERNAME = "root";
    public static final String PASSWORD = "Iadorec#741852963";
    public static final String CONN_STRING = "jdbc:mysql://127.0.0.1:3306/";
    public static final String DB_NAME = "team8db";

    public static Connection connection;

    public static boolean open(){
        try{
            connection = DriverManager.getConnection(CONN_STRING+DB_NAME+"?useSSL=false",USERNAME,PASSWORD);
            System.out.println("Connected");
            return true;

        }catch (SQLException e){
            System.out.println("Could not connected" +e.getMessage());
            return false;
        }
    }

    public static void close() {
        try {
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection"+e.getMessage());
        }

    }

    public static void queryPlate(String input){

        if(connection==null){
            System.out.println("There's no connection to a database");
            return;
        }
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle where plate = "+"'"+input+"'")){
            if(resultSet.next()) {
                Date now = new Date();
                Date date = resultSet.getDate("ins_date_end");
                if(date==null){
                    System.out.println("The vehicle has no insurance");
                    return;
                }
                System.out.println("The vehicle with plate number : "+resultSet.getString("plate") +
                        " " + (now.after(date)?"has an expired insurance, and the insurance expired at ":
                        "has a valid insurance, and the insurance expires at ")+date);
            }else{
                System.out.println("No vehicle has such plate");
            }
        }catch (SQLException e) {
            System.out.println("something went wrong" +e.getMessage());
            e.printStackTrace();
        }
    }
}
