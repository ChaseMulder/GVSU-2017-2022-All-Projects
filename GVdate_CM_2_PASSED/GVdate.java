import java.io.*;
import java.util.Scanner;

public class GVdate {
    private int month;
    private int day;
    private int year;

    private static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int birthDay = 3;
    private final int birthMonth = 5;

    public GVdate(){
        month = 10;
        day = 12;
        year = 2020;
    }

    public GVdate(int month, int day, int year){ //Constructor 2
        if (isDateValid(month, day, year)){
            this.month = month;
            this.day = day;
            this.year = year;
        }
        else{
            this.month = 10;
            this.day = 12;
            this.year = 2020;
        }
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getYear(){
        return year;
    }

    public String toString(){
        return month + "/" + day + "/" + year;
    }

    public boolean isMyBirthday(){
        if(day == birthDay & month == birthMonth){
            return true;
        }
        return false;
    }

    public void setMonth(int month){ //not sure how to check if invalid 
        if (isDateValid(month, this.day, this.year)){
            this.month = month; 
        }
    }

    public void setDay(int day){ //not sure how to check if invalid 
        if (isDateValid(this.month, day, this.year)){
            this.day = day;
        }
    }

    public void setYear(int year){ 
        if (isDateValid(this.month, this.day, year)){
            this.year = year;
        }
    }   

    public void setDate (int month, int day, int year){
        if (isDateValid(month, day, year)){
            this.month = month;
            this.day = day;
            this.year = year;
        }
    }

    private boolean isDateValid (int month, int day, int year){
        if (month > 12 || month < 1){
            return false;
        }
        if (day > getLastDayOfMonth(month, year) || day < 0){
            return false;
        }
        if (year < 0){
            return false;
        }
        return true;
    }    

    public GVdate (String date){ //Constructor 3
        String[] parts = date.split("/");
        int m = Integer.parseInt(parts[0]);
        int d = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        if (isDateValid(m, d, y)){
            this.month = m;
            this.day = d;
            this.year = y;
        }
        else{
            this.month = 10;
            this.day = 12;
            this.year = 2020;
        }
    }

    public boolean isLeapYear(int year){
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private int getLastDayOfMonth(int month, int year){ 
        //  private static final int[] DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return DAYS_IN_MONTH[month];
    }

    /*
    //phase 3
    public String toString(int format){
    }
    public void nextDay( ){ 
    if (isValid(month, day + 1, year))    return new Date(month, day + 1, year);
    else if (isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
    else                                  return new Date(1, 1, year + 1);
    }
    public boolean equals(GVdate otherDate){
    }
    public void skipAhead(int numDays ){
    }
     */

    public static void main(String args[]) {
        GVdate d1 = new GVdate(2, 29, 2021);
        String test = d1.toString();       
        System.out.println (test);

        d1.setYear(-1);
        String test2 = d1.toString();       
        System.out.println (test2);
    }
}
