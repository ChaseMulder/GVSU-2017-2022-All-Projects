import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
/*****************************************************
 * The test class GVdate - Phase 1
 *
 * @author  Ana Posada
 * @version 1.0.0
 ****************************************************/
public class GVdateTestPhase1{
    private GVdate d;

    /*********************************************************
     *  Test Date Constructor- no input parameters
     ********************************************************/
    @Test
    public void testDefaultConstructor(){
        d = new GVdate();        
        Assert.assertEquals("Month should be 10",
            10, d.getMonth());
        Assert.assertEquals("Day should be 12",
            12, d.getDay());
        Assert.assertEquals("Year should be 2020",  
            2020, d.getYear());
    } 

    /*********************************************************
     *  Test Date Constructor(int m, int d, int y)
     ********************************************************/
    @Test
    public void testSecondConstructor(){
        d = new GVdate(6,22,1992);        
        Assert.assertEquals("GVdate() did not set month to value of input parameter", 
            6, d.getMonth());                
        Assert.assertEquals("GVdate() did not set day to value of input parameter", 
            22, d.getDay());   
        Assert.assertEquals("GVdate() did not set year to value of input parameter", 
            1992, d.getYear());      
    } 

    /*********************************************************
     * Test setDate 
     ********************************************************/
    @Test
    public void testSetDate(){
        d = new GVdate(10,13,2020);    
        d.setDate(4,30,1960);
        Assert.assertEquals("setDate() month not set to input parameter", 
            4, d.getMonth());                
        Assert.assertEquals("setDate() day not set to input parameter", 
            30, d.getDay());   
        Assert.assertEquals("setDate() year not set to input parameter", 
            1960, d.getYear());
    } 

    /*********************************************************
     * Test setMonth 
     ********************************************************/
    @Test
    public void testSetMonth(){
        d = new GVdate(4,30,1960);    
        d.setMonth(12);
        Assert.assertEquals("setMonth() month not set to input parameter", 
            12, d.getMonth());                
    } 

    /*********************************************************
     * Test setYear
     ********************************************************/
    @Test
    public void testSetYear(){
        d = new GVdate(4,30,1960);    
        d.setYear(2020);
        Assert.assertEquals("setYear() year not set to input parameter", 
            2020, d.getYear());                
    } 

    /*********************************************************
     * Test setDay
     ********************************************************/
    @Test
    public void testSetDay(){
        d = new GVdate(4,30,1960);    
        d.setDay(28);
        Assert.assertEquals("setDay() day not set to input parameter", 
            28, d.getDay());   
    } 

    /*********************************************************
     * Test toString
     *********************************************************/
    @Test
    public void testToString(){
        d = new GVdate(12,31,1999); 
        Assert.assertEquals("Problems with formatting the date as required: "
            + "mm/dd/yyyy" , "12/31/1999", d.toString());                            
    }  

}