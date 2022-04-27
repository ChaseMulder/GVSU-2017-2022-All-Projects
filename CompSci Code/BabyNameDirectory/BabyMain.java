
import java.io.FileInputStream;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;

public class BabyMain {

    public static void main(String[] args) {

        // TODO Auto-generated method stub

        BabyNamesDatabase db = new BabyNamesDatabase();

        // read small data file created just for testing

        db.readBabyNameData("BabyNames.csv");

        // check number of records

        if(db.countAllNames() != 6){

            System.out.println("Error: Number of names should be 6");

        }

        // check most popular boy

        BabyName popular = db.mostPopularBoy(1999);

        String name = popular.getName();

        if(name.equals("Scott") == false){

            System.out.println("Error: Popular boy in 1999 should be Scott");

        }

        // check number of records for one year

        ArrayList<BabyName> tempList = db.searchForYear(1999);

        if(tempList.size() != 4){

            System.out.println("Error: Should be 4 records in 1999");

        }

        System.out.println("Scanning complete.");

    }

}