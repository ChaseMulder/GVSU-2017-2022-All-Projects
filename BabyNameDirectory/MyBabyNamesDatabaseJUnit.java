import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
/*******************************************
 * Class to test the BabyNamesDatabase project
 *
 * @author - Ana Posada
 * @version - March 2020
 ******************************************/
public class MyBabyNamesDatabaseJUnit{
    /** object of the PokerDice class */
    private BabyNamesDatabase database;

    /******************************************************
     * Test constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        database= new BabyNamesDatabase(); 
        assertEquals("ArrayList should not contain any baby names at this time",
            0, database.countAllNames ());  
    } 

    /******************************************************
     * Test read file and counts
     *****************************************************/
    @Test
    public void testReadFileAndCounts()
    {
        database= new BabyNamesDatabase(); 
        database.readBabyNameData("BabyNames.csv");
        assertEquals("Should be 227,881 baby names",
            227881, database.countAllNames ());  

        assertEquals("Count of girls should be 153,467,149",
            153467149, database.countAllGirls());  

        assertEquals("Count of boys should be 161,943,150",
            161943150, database.countAllBoys()); 
    }

    /******************************************************
     * Test Most popular girl - Most popular boy
     *****************************************************/
    @Test
    public void testMostPopularNames()
    {
        BabyName popular;
        database= new BabyNamesDatabase(); 
        database.readBabyNameData("BabyNames.csv");
        
        popular = database.mostPopularGirl(2002);
        assertEquals("Emily was the most popular girl in 2002",
            "Emily", popular.getName());  

        popular = database.mostPopularBoy(2002);
        assertEquals("Jacob was the most popular boy in 2002",
            "Jacob", popular.getName()); 

        popular = database.mostPopularGirl(1950);
        assertEquals("Linda was the most popular girl in 1950",
            "Linda", popular.getName()); 

        popular = database.mostPopularBoy(1950);
        assertEquals("James was the most popular boy in 1950",
            "James", popular.getName()); 

        popular = database.mostPopularGirl(2019);
        assertEquals("No baby names found for 2019",
            null, popular); 

        popular = database.mostPopularBoy(2019);
        assertEquals("No baby names found for 2019",
            null, popular);  
    }

    /******************************************************
     * Test search for name
     *****************************************************/
    @Test
    public void testSearchForName()
    {
        ArrayList<BabyName> list;
        database= new BabyNamesDatabase(); 
        database.readBabyNameData("BabyNames.csv");
        list = database.searchForName("CaMiLa");
        assertEquals("There are 24 records with the name Camila",
            24, list.size());  

        list = database.searchForName("ROBERT");
        assertEquals("There are 213 records with the name Robert",
            213, list.size());  

        list = database.searchForName("Dog");
        assertEquals("There are no records with the name Dog",
            0, list.size());        
    }

    /******************************************************
     * Test search for year
     *****************************************************/
    @Test
    public void testSearchForYear()
    {
        ArrayList<BabyName> list;
        database= new BabyNamesDatabase(); 
        database.readBabyNameData("BabyNames.csv");
        list = database.searchForYear(1880);
        assertEquals("There were 381 baby names in 1880",
            281, list.size());  

        list = database.searchForYear(2000);
        assertEquals("There were 3,052  baby names in 2000",
            3052, list.size());  

        list = database.searchForYear(2020);
        assertEquals("There are no baby names for the year 2020",
            0, list.size());        
    }

    /******************************************************
     * Test top ten
     *****************************************************/
    @Test
    public void testTopTen() {
        ArrayList<BabyName> list;
        database= new BabyNamesDatabase(); 
        database.readBabyNameData("BabyNames.csv");
        
        list = database.topTenNames(1958);

        assertEquals("Michael was the most popular name in 1958",
            "Michael", list.get(0).getName());
        assertEquals("David was the second most popular name in 1958",
            "David", list.get(1).getName()); 
        assertEquals("Susan was the 10th most popular name in 1958",
            "Susan", list.get(9).getName()); 

        list = database.topTenNames(1995);
        
        assertEquals("Michael was the most popular name in 1995",
            "Michael", list.get(0).getName());
        assertEquals("Christopher was the 3rd most popular name in 1995",
            "Christopher", list.get(2).getName()); 
        assertEquals("Daniel was the 10th most popular name in 1995",
            "Daniel", list.get(9).getName()); 

        // year does not have records
        list = database.topTenNames(2020);
        assertEquals("There are no baby names for the year 2020",
            0, list.size()); 
    }
}
