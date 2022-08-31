package com.stonks.test;

import java.sql.*;

public class SQLTest {

    /*
       Test Class for the backend. This will read all of the settings from the database
       if everything is configured properly.
     */

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stonks", "root", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM settings WHERE id = 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("Database Settings:");
            System.out.println("Mode: " + resultSet.getString("layoutStyle"));
            System.out.println("Single Stock: " + resultSet.getString("singleTicker"));
            System.out.println("Two Stocks - 1: " + resultSet.getString("doubleTicker1"));
            System.out.println("Two Stocks - 2: " + resultSet.getString("doubleTicker2"));
            System.out.println("Many Stocks - Main: " + resultSet.getString("manyTicker1"));
            System.out.println("Many Stocks - Secondary 1: " + resultSet.getString("manyTicker2"));
            System.out.println("Many Stocks - Secondary 2: " + resultSet.getString("manyTicker3"));
            System.out.println("Many Stocks - Secondary 3: " + resultSet.getString("manyTicker4"));
            System.out.println("Many Stocks - Secondary 4: " + resultSet.getString("manyTicker5"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Navigate to PhpMyAdmin or use the SQL terminal to determine the current values in the database.");
        System.out.println("If the values match what was output, the test passes. If not the test fails.");
    }
}
