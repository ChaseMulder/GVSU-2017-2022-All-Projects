import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
import java.io.FileInputStream;

public class BabyNamesDatabase {
    ArrayList<BabyName> babyNames;
    public BabyNamesDatabase() {
        babyNames = new ArrayList<>();
    }
    public void readBabyNameData(String filename) {
        // Read the full set of data from a text file
        try{
            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");

            // keep reading as long as there is more data
            while(scnr.hasNext()) {
                // FIX ME: read the name, gender, count and year
                String name = scnr.next();
                String gender = scnr.next();
                int count = scnr.nextInt();
                int year = scnr.nextInt();
                // FIX ME: remove this print statement after method works
                //System.out.println(name);
                // FIX ME: assign true/false to boolean isFemale based on
                // the gender String
                boolean isFemale = false;
                if(gender.contains("f")||gender.contains("F"))isFemale = true;
                // FIX ME: instantiate a new Baby Name and add to ArrayList
                BabyName babyName = new BabyName(name, isFemale, count, year);
            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }
    }
    public int countAllNames() {
        return babyNames.size();
    }

    public int countAllGirls() {
        int count = 0;
        for(BabyName babyName:babyNames) {
            if(babyName.isFemale() && babyName.getYear()>=1880) {
                count += babyName.getCount();
            }
        }
        return count;
    }

    public int countAllBoys() {
        int count = 0;
        for(BabyName babyName:babyNames) {
            if(!babyName.isFemale() && babyName.getYear()>=1880) {
                count += babyName.getCount();
            }
        }
        return count;
    }

    public BabyName mostPopularGirl(int year) {
        int maxCount = 0;
        BabyName mostPopular = null; //=Database.get(0);
        for(BabyName babyName:babyNames) {
            if(babyName.isFemale() && babyName.getYear()==year && babyName.getCount()>maxCount) {
                maxCount = babyName.getCount();
                mostPopular = babyName;
            }
        }
        return mostPopular;
    }

    public BabyName mostPopularBoy(int year) {
        int maxCount = 0;
        BabyName mostPopular = null;
        for(BabyName babyName:babyNames) {
            if(!babyName.isFemale() && babyName.getYear()==year && babyName.getCount()>maxCount) {
                maxCount = babyName.getCount();
                mostPopular = babyName;
            }
        }
        return mostPopular;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<BabyName> searchForName(String name){
        ArrayList<BabyName> result = new ArrayList<>();
        for(BabyName babyName:babyNames) {
            if(babyName.getName().equalsIgnoreCase(name)) {
                result.add(babyName);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<BabyName> searchForYear(int year){
        ArrayList<BabyName> result = new ArrayList<>();
        for(BabyName babyName:babyNames) {
            if(babyName.getYear()==year) {
                result.add(babyName);
            }
        }

        return result;

    }

    public ArrayList<BabyName> topTenNames(int year){

        ArrayList<BabyName> temp = searchForYear(year);

        int n = Math.min(temp.size(), 10);

        ArrayList<BabyName> result = new ArrayList<>();

        for(int i=0;i<n;i++) {

            result.add(temp.get(i));

        }

        return result;

    }

}