import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.*;
/**************************************************
 * The test class GVdate.
 *
 * @author  Scott Grissom
 * @version (1.0.0)
 * @version (1.0.1) - Ana Posada
 * To include:
 * 1. contructor GVdate (String date) testing 
 * 2. invalid date testing for constructors and setDate
 * 3. invalid date testing for setMonth, setDay and setYear
 ****************************************************/
public class GVdateTestPhase2{
    private GVdate d;

    /*********************************************************
     *  Test Date Constructor- no input parameters
     ********************************************************/
    @Test
    public void testDefaultConstructor(){
        d = new GVdate();        
        Assert.assertEquals("Default constructor - Month should be 10",
            10, d.getMonth());
        Assert.assertEquals("Default constructor - Day should be 12",
            12, d.getDay());
        Assert.assertEquals("Default constructor - Year should be 2020",  
            2020, d.getYear());
    } 

    /*********************************************************
     *  Test Date Constructor2(int m, int d, int y)
     ********************************************************/
    @Test
    public void testConstructor2(){
        d = new GVdate(4,20,1963);        
        Assert.assertEquals("Constructor2 - month not set to input parameter", 
            4, d.getMonth());                
        Assert.assertEquals("Constructor2 - day not set to input parameter", 
            20, d.getDay());   
        Assert.assertEquals("Constructor2 - year not set to input parameter", 
            1963, d.getYear());      
    } 

    /*********************************************************
     *  Test Date Constructor3 GVdate (String date)
     ********************************************************/
    @Test
    public void testConstructor3(){
        d = new GVdate("4/2/1963");        
        Assert.assertEquals("Constructor3 - month not set correctly", 
            4, d.getMonth());                
        Assert.assertEquals("Constructor3 - day not set correctly", 
            2, d.getDay());   
        Assert.assertEquals("Constructor3 - year not set correctly", 
            1963, d.getYear()); 

        d = new GVdate("11/20/1963");        
        Assert.assertEquals("Constructor3 - month not set correctly", 
            11, d.getMonth());                
        Assert.assertEquals("Constructor3 - day not set correctly", 
            20, d.getDay());   
        Assert.assertEquals("Constructor3 - year not set correctly", 
            1963, d.getYear()); 

        d = new GVdate("11/2/1963");        
        Assert.assertEquals("Constructor3 - month not set correctly", 
            11, d.getMonth());                
        Assert.assertEquals("Constructor3 - day not set correctly", 
            2, d.getDay());   
        Assert.assertEquals("Constructor3 - year not set correctly", 
            1963, d.getYear()); 

        d = new GVdate("1/12/1963");        
        Assert.assertEquals("Constructor3 - month not set correctly", 
            1, d.getMonth());                
        Assert.assertEquals("Constructor3 - day not set correctly", 
            12, d.getDay());   
        Assert.assertEquals("Constructor3 - year not set correctly", 
            1963, d.getYear()); 
    } 

    /*********************************************************
     *  Test Invalid date constructor2(int m, int d, int y)
     ********************************************************/
    @Test
    public void testInvalidDateConstructor2(){
        testInvalidMonth1("constructor2");
        testInvalidNegativeMonth("constructor2");
        testInvalidNegativeDay("constructor2");
        testInvalidNegativeYear("constructor2");
        testInvalidDayUpperBound("constructor2");
    }

    /*********************************************************
     *  Test Invalid date constructor2(int m, int d, int y)
     ********************************************************/
    @Test
    public void testInvalidDateConstructor3(){
        testInvalidMonth1("constructor3");
        testInvalidNegativeMonth("constructor3");
        testInvalidNegativeDay("constructor3");
        testInvalidNegativeYear("constructor3");
        testInvalidDayUpperBound("constructor3");
    }

    /*********************************************************
     * Test setDate 
     ********************************************************/
    @Test
    public void testSetDate(){
        d = new GVdate(4,20,1963);    
        d.setDate(3,21,1965);
        Assert.assertEquals("setDate() month not set to input parameter", 
            3, d.getMonth());                
        Assert.assertEquals("setDate() day not set to input parameter", 
            21, d.getDay());   
        Assert.assertEquals("setDate() year not set to nput parameter", 
            1965, d.getYear());
    } 

    /*********************************************************
     * Test invalid date setDate 
     ********************************************************/
    @Test
    public void testInvalidDateSetDate(){
        d = new GVdate(4,20,1963);    
        d.setDate(3,21,1965);
        d.setDate(13,21,1965);
        Assert.assertEquals("setDate() invalid month - date should not change", 
            "3/21/1965", d.toString()); 

        testInvalidMonth1("setDate");
        testInvalidNegativeMonth("setDate");
        testInvalidNegativeDay("setDate");
        testInvalidNegativeYear("setDate");
        testInvalidDayUpperBound("setDate");
    } 

    /*********************************************************
     * Test setMonth 
     ********************************************************/
    @Test
    public void testSetMonth(){
        d = new GVdate(4,20,1963);    
        d.setMonth(12);
        Assert.assertEquals("setMonth() month not set to input parameter", 
            12, d.getMonth());   
    } 

    /*********************************************************
     * Test setYear
     ********************************************************/
    @Test
    public void testSetYear(){
        d = new GVdate(4,20,1963);    
        d.setYear(2013);
        Assert.assertEquals("setYear() year not set to input parameter", 
            2013, d.getYear());                
    } 

    /*********************************************************
     * Test setDay
     ********************************************************/
    @Test
    public void testSetDay(){
        d = new GVdate(4,20,1963);    
        d.setDay(28);
        Assert.assertEquals("setDay() day not set to input parameter", 
            28, d.getDay());   
        d.setDate(2,28,2004);
        d.setDay(29);
        Assert.assertEquals("setDay() day not set to input parameter", 
            29, d.getDay());   

    } 

    /*********************************************************
     * Test InvalidDaysetDay
     ********************************************************/
    @Test
    public void testInvalidDaySetDay(){
        d = new GVdate(4,20,1963);    
        d.setDay(31);
        Assert.assertEquals("setDay() day will create a wrong date" +
            "- date should not change", 20, d.getDay()); 

        testInvalidNegativeDay("setDay"); 
        testInvalidDayUpperBound("setDay"); 
    } 

    /*********************************************************
     * Test invalid month setMonth 
     ********************************************************/
    @Test
    public void testInvalidMonthSetMonth() {
        d = new GVdate(1,31,1960);    
        d.setMonth(6);
        Assert.assertEquals("setMonth() month will create a wrong date" +
        "- date should not change", 1, d.getMonth());  

        testInvalidMonth1("setMonth");
        testInvalidNegativeMonth("setMonth");
    } 

    /*********************************************************
     * Test setYear
     ********************************************************/
    @Test
    public void testInvalidYearSetYear(){
        testInvalidNegativeYear("setYear");   
    } 

    /*********************************************************
     * Test Leap Year
     ********************************************************/
    @Test
    public void testLeapYear(){
        d = new GVdate ();
        d.setDate(4,20,1963);
        Assert.assertTrue("1963 is NOT a leap year", 
            !d.isLeapYear(1963));  
        d.setDate(4,20,1600);
        Assert.assertTrue("1600 is a leap year", 
            d.isLeapYear(1600));  
        d.setDate(4,20,2000);
        Assert.assertTrue("2000 is a leap year", 
            d.isLeapYear(2000));   
        d.setDate(4,20,2004);
        Assert.assertTrue("2004 is a leap year", 
            d.isLeapYear(2004));            
        d.setDate(4,20,1900);
        Assert.assertTrue("1900 is NOT a leap year", 
            !d.isLeapYear(1900));    
    }   

    /*********************************************************
     * Test invalid month1 - testing value 13
     * @param - method that calls this method
     ********************************************************/
    private void testInvalidMonth1(String method){
        d = new GVdate();
        switch (method){
            case "constructor2": 
            d = new GVdate(13,20,1963);   
            break;

            case "constructor3": 
            d = new GVdate("13/20/1963");  
            break;

            case "setDate": 
            d.setDate(13,20,1963); 
            break;

            case "setMonth":
            d.setMonth(13);
        }
        Assert.assertEquals("invalid month - date should not change or " +
        "constructors should create the default date" ,"10/12/2020", d.toString());  
    }

    /*********************************************************
     * Test invalid month2 - testing value 0
     * @param - method that calls this method
     ********************************************************/
    private void testInvalidNegativeMonth(String method){
        d = new GVdate();
        switch (method){
            case "constructor2": 
            d = new GVdate(-1,20,1963);     
            break;

            case "constructor3": 
            d = new GVdate("-1/20/1963");    
            break;

            case "setDate": 
            d.setDate(-1,20,1963); 
            break;

            case "setMonth":
            d.setMonth(-1);
        }

        Assert.assertEquals("invalid month - date should not change or " +
        "constructors should create the default date", "10/12/2020", d.toString());  
    }

    /*********************************************************
     * Test invalid month3 - testing negative day
     * @param - method that calls this method
     ********************************************************/
    private void testInvalidNegativeDay(String method){
        d = new GVdate();
        switch (method){
            case "constructor2": 
            d = new GVdate(1,-20,1963);   
            break;

            case "constructor3": 
            d = new GVdate("1/-20/1963");  
            break;

            case "setDate":
            d.setDate(1,-20, 1963); 
            break;

            case "setDay": 
            d.setDay (-20);
        }

        Assert.assertEquals("invalid day - date should not change or " +
        "constructors should create the default date","10/12/2020", d.toString());
    }

    /*********************************************************
     * Test invalid month1 - testing value 13
     * @param - method that calls this method
     ********************************************************/
    private void testInvalidNegativeYear(String method){
        d = new GVdate();
        switch (method){
            case "constructor2": 
            d = new GVdate(4,20,-4);  
            break;

            case "constructor3": 
            d = new GVdate("4/20/-4");   
            break;

            case "setDate":
            d.setDate(4,20,-4); 
            break;

            case "setYear":
            d.setYear(-4); 
        }

        Assert.assertEquals("invalid Year - date should not change or " +
        "constructors should create the defaul date", "10/12/2020", d.toString()); 
    }

    /*********************************************************
     * Test invalid day - testing upper bound for each month
     * @param - method that calls this method
     ********************************************************/
    private void testInvalidDayUpperBound(String method){
        int[ ] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
        for (int i = 0 ; i <= 11 ; i++) {
            d = new GVdate ();

            if (i == 1) {
                // checking february for a NOT leap year
                d = new GVdate(2, 29, 2021);
                Assert.assertEquals("invalid day for month -" +
                "constructor should create the default date","10/12/2020", d.toString());

                d = new GVdate("2/29/2021");
                Assert.assertEquals(" invalid day for month - " +
                "constructor should create the default date","10/12/2020", d.toString());

                d = new GVdate();
                d.setDate(2, 29, 2021);
                Assert.assertEquals(" invalid day for month - date should not change",
                    "10/12/2020", d.toString());

                d = new GVdate(2, 27, 2021);
                d.setDay(29);
                Assert.assertEquals(" invalid day for month - date should not change",
                    "2/27/2021", d.toString());
            }
            else {              
                switch (method){
                    case "constructor2": 
                    d = new GVdate (i + 1, monthDays [i] + 1, 2020); 
                    Assert.assertEquals(" invalid day for month - " +
                    "constructor should create the default date","10/12/2020", d.toString());
                    break;

                    case "constructor3": 
                    d = new GVdate ((i + 1) + "/" + (monthDays [i] + 1) +
                        "/" + "2020"); 
                    Assert.assertEquals(" invalid day for month - " +
                    "constructor should create the default date","10/12/2020", d.toString());
                    break;

                    case "setDate":
                    d.setDate(i + 1, monthDays [i] + 1, 2020); 
                    Assert.assertEquals(" invalid day for month - date should not change",
                        "10/12/2020", d.toString());
                    break;

                    case "setDay" :
                    d.setDate(i + 1, 1, 2020);
                    d.setDay(monthDays [i] + 1);
                    Assert.assertEquals("invalid day for month - date should not change",
                        1, d.getDay());
                }
            }
        }
    }
}