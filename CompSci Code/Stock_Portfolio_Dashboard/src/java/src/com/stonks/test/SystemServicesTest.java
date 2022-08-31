package com.stonks.test;

import com.stonks.code.DatabaseQuery;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SystemServicesTest {
    public static void main(String[] args) {
        System.out.println("Outputting Java Version: Should be OpenJDK for licensing issues. If not, test fails.");
        System.out.println(System.getProperty("java.vm.name"));

        try {
            URL url = new URL("http://localhost");

            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Successful connection to " + url.toString() + ". This phase of test passes.");
        } catch (MalformedURLException e) {
            System.out.println("Issue with test url");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error connecting to localhost. Web server is not running or not functioning properly. This phase of test fails.");
            e.printStackTrace();
        }

        boolean db = DatabaseQuery.tryDB("jdbc:mysql://localhost:3306/stonks", "root", "");
        if (db) {
            System.out.println("Successful connection to database. This phase of test passes.");
        } else {
            System.out.println("Error connecting to database. Make sure DB is running and has correct schema.");
            System.out.println("Reference the repo docs for more info on DB setup. This phase of test fails.");
        }
    }
}
