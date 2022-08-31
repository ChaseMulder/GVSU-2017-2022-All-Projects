package com.stonks.test;

import com.stonks.code.Text5x7;

import java.util.ArrayList;

public class FontTest {

    // if more chars are added later, add them to the chars string and they will be tested also
    public static void main(String[] args) {
        String chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-+.|";
        for (int i = 0; i < chars.length(); i += 6) {
            String substr = chars.substring(i, Math.min(i + 6, chars.length()));
            ArrayList<String[]> text = Text5x7.getLetters(substr);
            System.out.println("Testing chars " + substr);
            System.out.println();
            for (int j = 0; j < 7; j++) {
                String line =  "";
                for (String[] strings : text) {
                    line += strings[j];
                    line += "   ";
                }
                System.out.println(line);
            }
            System.out.println();
        }

        System.out.println("The output should contain all the chars. Check the output to confirm all the characters are outputting properly.");
        System.out.println("All the chars should be output uppercase. If this is not the case the test fails.");
        System.out.println("If all the characters are output properly, the test passes.");
    }
}
