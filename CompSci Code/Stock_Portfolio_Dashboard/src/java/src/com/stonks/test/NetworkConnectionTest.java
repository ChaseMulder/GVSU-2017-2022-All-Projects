package com.stonks.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkConnectionTest {
    public static void main(String[] args) {
        int fails = 0;

        try {
            URL url1 = new URL("https://google.com");

            URLConnection connection1 = url1.openConnection();
            connection1.connect();

            System.out.println("Successful connection to " + url1.toString());
        } catch (MalformedURLException e) {
            System.out.println("Issue with test url 1. Test part 1 fails");
            e.printStackTrace();
            fails++;
        } catch (IOException e) {
            System.out.println("Error connecting to url 1. Test part 1 fails");
            e.printStackTrace();
            fails++;
        }

        try {
            URL url2 = new URL("https://1.1.1.1");

            URLConnection connection2 = url2.openConnection();
            connection2.connect();

            System.out.println("Successful connection to " + url2.toString());
        } catch (MalformedURLException e) {
            System.out.println("Issue with test url 2. Test part 2 fails");
            e.printStackTrace();
            fails++;
        } catch (IOException e) {
            System.out.println("Error connecting to url 2. Test part 2 fails");
            e.printStackTrace();
            fails++;
        }

        System.out.println("Internet Connection Test Results: " + (2 - fails) + " Successes, " + fails + " Failures");
    }
}
