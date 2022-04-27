import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;
/*******************************************
 * The test class for PokerDice - phases 1 - 3
 *
 * @author - Ana Posada
 * @version - February 2020
 ******************************************/
public class MyPokerDiceJUnit{
    /** object of the PokerDice class */
    private PokerDice game;

    /** Constants  */
    private static final int NUM_ROLLS = 3;
    private static final int NUM_ROUNDS = 7;
    private static final int NUM_DICE = 5;

    private static final int FIVE_OF_A_KIND = 50; 
    private static final int FOUR_OF_A_KIND = 40;  
    private static final int THREE_OF_A_KIND = 25;       
    private static final int FULL_HOUSE = 35;
    private static final int SMALL_STRAIGHT = 30;       
    private static final int LARGE_STRAIGHT = 45;       

    /** the game dice */
    private GVdie [] dice;

    /******************************************************
     * Instantiate - PokerGame
     * Called before every test case method.
     *****************************************************/
    @Before
    public void setUp()
    {
        game = new PokerDice();  
        dice = game.getDice();
    }

    /******************************************************
     * Test initial values of the constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        assertEquals("Constructor: rounds should be zero", 
            0, game.getNumRounds());   

        assertEquals("Constructor: rolls should be zero", 
            0, game.getNumRolls()); 

        assertEquals("Constructor: score should be zero", 
            0, game.getScore()); 

        assertEquals("Constructor: dice array should have 5 GVdie", 
            5, game.getDice().length); 

        for (GVdie d : dice) {
            assertFalse("resetGame(): the dice should not be held", d.isHeld());
        }
    } 

    /******************************************************
     * Test resetGame
     *****************************************************/
    @Test
    public void testResetGame()
    {
        game.resetGame();
        assertEquals("Instance variables should be reset to zero in resetGame()", 
            0, game.getScore());
        assertTrue ("resetGame(): did you reset number of rolls?", game.okToRoll());
        assertFalse ("resetGame(): did you reset number of rounds?", game.gameOver());

        for (GVdie d : dice) {
            assertFalse("resetGame(): the dice should not be held", d.isHeld());
        }
    }

    /******************************************************
     * Test OkToRoll or not
     *****************************************************/
    @Test
    public void testOKToRoll()
    {
        game.resetGame(); 
        for (int i = 0; i < NUM_ROLLS; i++)  {
            assertTrue("okToRoll(): should allow " + NUM_ROLLS + 
                " rolls per round",  game.okToRoll());
            game.rollDice();
        }
        assertFalse("okToRoll(): should not allow more than " + NUM_ROLLS + 
            " rolls per round",  game.okToRoll());
    }

    /******************************************************
     * Test game over or not over
     *****************************************************/
    @Test
    public void testGameOver()
    {
        game.resetGame();
        for (int i = 0; i < NUM_ROUNDS; i++) {
            assertFalse("gameOver(): should allow " + NUM_ROUNDS + 
                " rounds per game",  game.gameOver());
            game.checkChance();
        }
        assertTrue("gameOver(): the game should be over after " + NUM_ROUNDS + 
            " rounds",  game.gameOver());
    }

    /******************************************************
     * generate an array of NUM-DICE random numbers 
     * between 1 - 6 (inclusive)
     * @return int [] - array of NUM_DICE random numbers
     *****************************************************/
    private int[] generateRandomVals() {
        Random rand = new Random ();
        int [] values = new int [NUM_DICE];
        for (int i = 0; i < values.length; i++)
            values[i] = rand.nextInt(6) + 1;
        return values;
    }

    /******************************************************
     * generate an array of NUM-DICE random numbers 
     * between 1 - 6 (inclusive)
     * @param int - number of equal values to generate
     * @return int [] - array contains ofAKind values
     *****************************************************/
    private int []  genValuesOfAKind(int ofAKind) {
        int [] values = new int [NUM_DICE];
        Random rand = new Random ();
        int count = 0; 
        int num = 0;
        int index = 0;
        for (int i = 0 ; i < NUM_DICE; i++) 
            values [i] = 0;

        if (ofAKind >= 3 && ofAKind <= 5){               
            // populating array of equal numbers ofAKind at random indexes    
            num = rand.nextInt(6) + 1;
            while (count < ofAKind) {
                index = rand.nextInt (NUM_DICE);
                if (values [index] == 0) {    
                    values [index] = num;
                    count++;
                }
            }

            // populating the rest values at random
            for (int i = 0 ; i < NUM_DICE; i++) 
                if (values [i] == 0)
                    values [i] = rand.nextInt(6) + 1;

        }
        return values;

    }

    /******************************************************
     * generate an array of NUM-DICE random numbers 
     * between 1 - 6 (inclusive)
     * @return int[] -generates 3 of a kind and a exact pair
     *****************************************************/
    private int []  genValuesFullHouse() {
        int [] values = new int [NUM_DICE];
        Random rand = new Random ();
        int count = 0; 
        int num = 0;
        int numPair = 0;
        int index = 0;
        for (int i = 0 ; i < NUM_DICE; i++) 
            values [i] = 0;

        // populating 3 of a kind
        num = rand.nextInt(6) + 1;
        while (count < 3) {
            index = rand.nextInt (NUM_DICE);
            if (values [index] == 0) {    
                values [index] = num;
                count++;
            }
        }

        // populating the rest values to an exact number
        numPair = rand.nextInt(6) + 1;
        while (numPair == num)
            numPair = rand.nextInt(6) + 1;
        for (int i = 0 ; i < NUM_DICE; i++) 
            if (values [i] == 0)
                values [i] = numPair;

        return values;
    }

    /******************************************************
     * generate an array of NUM-DICE random numbers 
     * between 1 - 6 (inclusive)
     * @param int - sequence to generate
     * small - 4
     * large - 5
     * @return int [] - small or large straight generated at random
     *****************************************************/
    private int []  genValuesStraight(int sequence) {
        int [] values = new int [NUM_DICE];
        Random rand = new Random ();
        int count = 0; 
        int num = 0;
        int index = 0;
        for (int i = 0 ; i < NUM_DICE; i++) 
            values [i] = 0;

        if (sequence == 4)                   
            num = rand.nextInt(3) + 1;
        else if (sequence == 5)
            num = rand.nextInt(2) + 1;

        // populating the sequence at random indexes
        while (count < sequence) {
            index = rand.nextInt (NUM_DICE);
            if (values [index] == 0) {    
                values [index] = num;
                num++;
                count++;
            }
        }

        // populating the rest values at random
        for (int i = 0 ; i < NUM_DICE; i++) 
            if (values [i] == 0)
                values [i] = rand.nextInt(6) + 1;

        return values;
    }

    /******************************************************
     * tests THREE_OF_A_KIND
     *****************************************************/
    @Test
    public void testOkThreeOfAKind()
    {
        int [] values = genValuesOfAKind (3);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals (valuesToString (values) + " Three of a kind should increment score by " +
            THREE_OF_A_KIND ,  scoreBefore + THREE_OF_A_KIND , game.getScore());

        // four of a kind has a three of a kind
        values = genValuesOfAKind (4);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals (valuesToString (values) + " Four of a kind has a Three of a kind -  should increment score by " +
            THREE_OF_A_KIND ,  scoreBefore + THREE_OF_A_KIND , game.getScore());

        // five of a kind has a three of a kind
        values = genValuesOfAKind (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals (valuesToString (values) + " Five of a kind has a  Three of a kind -should increment score by " +
            THREE_OF_A_KIND ,  scoreBefore + THREE_OF_A_KIND , game.getScore());

        // a full house has 3 of a kind
        values = genValuesFullHouse ();
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals (valuesToString (values) + " Full house is also three of a kind " +
            THREE_OF_A_KIND , scoreBefore + THREE_OF_A_KIND, game.getScore());
    }

    /******************************************************
     * tests testNotOkThreeOfAKind()
     *****************************************************/
    @Test
    public void testNotOkThreeOfAKind()
    {
        int [] values = genValuesStraight (4);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals ( valuesToString (values) + "Not threeOfAKind should not increment score "
        , scoreBefore, game.getScore()  );

        values = genValuesStraight (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkThreeOfAKind ();
        assertEquals ( valuesToString (values) + "Not threeOfAKind should not increment score "
        , scoreBefore, game.getScore() );
    }

    /******************************************************
     * tests FOUR_OF_A_KIND
     *****************************************************/
    @Test
    public void testOkFourOfAKind()
    {
        int [] values = genValuesOfAKind (4);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFourOfAKind ();
        assertEquals (valuesToString (values) + " Four of a kind should increment score by " +
            FOUR_OF_A_KIND , scoreBefore + FOUR_OF_A_KIND, game.getScore() );

        // five of a kind has a four of a kind
        values = genValuesOfAKind (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFourOfAKind ();
        assertEquals (valuesToString (values) + " Five of a kind has a four of a kind - should increment score by " +
            FOUR_OF_A_KIND ,  scoreBefore + FOUR_OF_A_KIND , game.getScore());
    }

    /******************************************************
     * tests testNotOkFourOfAKind()
     *****************************************************/
    @Test
    public void testNotOkFourOfAKind()
    {
        int [] values = genValuesStraight (4);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFourOfAKind ();
        assertEquals ( valuesToString (values) + "Not FourOfAKind should not increment score "
        , scoreBefore, game.getScore() );

        values = genValuesStraight (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFourOfAKind ();
        assertEquals ( valuesToString (values) + "Not FourOfAKind should not increment score "
        , scoreBefore, game.getScore() );

        values = genValuesFullHouse ();
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFourOfAKind ();
        assertEquals ( valuesToString (values) + "Not FourOfAKind should not increment score "
        , scoreBefore, game.getScore() );
    }

    /******************************************************
     * tests FIVE_OF_A_KIND
     *****************************************************/
    @Test
    public void testOkFiveOfAKind()
    {
        int [] values = genValuesOfAKind (5);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFiveOfAKind ();
        assertEquals (valuesToString (values) +" Five of a kind should increment score by " +
            FIVE_OF_A_KIND , scoreBefore + FIVE_OF_A_KIND, game.getScore());
    }

    /******************************************************
     * tests not FIVE_OF_A_KIND
     *****************************************************/
    @Test
    public void testNotOkFiveOfAKind()
    {
        int [] values = genValuesStraight(4);
        int scoreBefore = game.getScore();
        game.setDice (values);       
        game.checkFiveOfAKind ();
        assertEquals (valuesToString (values) +" Not Five of a kind should increment score by "
        , scoreBefore, game.getScore()  );

        values = genValuesStraight (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFiveOfAKind ();
        assertEquals ( valuesToString (values) + "Not five OfAKind should not increment score "
        , scoreBefore, game.getScore() );
        
         values = genValuesFullHouse ();
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFiveOfAKind ();
        assertEquals ( valuesToString (values) + "Not Five OfAKind should not increment score "
        , scoreBefore, game.getScore() );
    }

    /******************************************************
     * tests FULL_HOUSE
     *****************************************************/
    @Test
    public void testOkFullHouse()
    {
        int [] values = genValuesFullHouse();
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFullHouse ();
        assertEquals   (valuesToString (values)+ " Full house (3 of a kind and a pair) increment score by " +
            FULL_HOUSE , scoreBefore + FULL_HOUSE, game.getScore());

        // five of a kind is also a full house
        values = genValuesOfAKind (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFullHouse ();
        assertEquals (valuesToString (values)+ " Five of a kind is also a full House increment score by " +
            FULL_HOUSE , scoreBefore + FULL_HOUSE, game.getScore());
    }

    /******************************************************
     * tests testNotOK full house()
     *****************************************************/
    @Test
    public void testNotOkFullHouse()
    {
        int [] values = genValuesStraight (4);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFullHouse ();
        assertEquals ( valuesToString (values) + "Not Full house - should not increment score "
        , scoreBefore, game.getScore());

        values = genValuesStraight (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkFullHouse ();
        assertEquals ( valuesToString (values) + "Not Full house -  should not increment score "
        , scoreBefore, game.getScore() );
    }

    /******************************************************
     * tests SMALL_STRAIGHT
     *****************************************************/
    @Test
    public void testOkSmallStraight()
    {
        int [] values = genValuesStraight (4);
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight ();
        assertEquals (valuesToString (values) +" Small straight should increment score by " +
            SMALL_STRAIGHT , scoreBefore + SMALL_STRAIGHT, game.getScore());

        // large straight is also a small straight
        values = genValuesStraight (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight ();
        assertEquals (valuesToString (values) +" Large straight is also a Small straight - should increment score by " +
            SMALL_STRAIGHT , scoreBefore + SMALL_STRAIGHT, game.getScore());
    }

    /******************************************************
     * tests Not Ok - small straight
     *****************************************************/
    @Test
    public void testNotOkSmallStraight()
    {
        int [] values = genValuesFullHouse();
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight ();
        assertEquals   (valuesToString (values)+ " Not a small straight - should not increment score by " 
        , scoreBefore, game.getScore());

        values = genValuesOfAKind (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight();
        assertEquals (valuesToString (values)+ " Not a small straight - should not increment score by "
        , scoreBefore, game.getScore());
        
        values = genValuesOfAKind (4);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight();
        assertEquals (valuesToString (values)+ " Not a small straight - should not increment score by "
        , scoreBefore, game.getScore());
        
        values = genValuesOfAKind (3);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkSmallStraight();
        assertEquals (valuesToString (values)+ " Not a small straight - should not increment score by "
        , scoreBefore, game.getScore());
    }

    /******************************************************
     * tests Not Ok - Large straight
     *****************************************************/
    @Test
    public void testNotOkLargeStraight()
    {
        int [] values = genValuesFullHouse();
        int scoreBefore = game.getScore();
        game.setDice (values);
        game.checkLargeStraight ();
        assertEquals   (valuesToString (values)+ " Not a large straight - should not increment score by " 
        , scoreBefore, game.getScore());

        values = genValuesOfAKind (5);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkLargeStraight();
        assertEquals (valuesToString (values)+ " Not a large straight - should not increment score by "
        , scoreBefore, game.getScore());
        
        values = genValuesOfAKind (4);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkLargeStraight();
        assertEquals (valuesToString (values)+ " Not a large straight - should not increment score by "
        , scoreBefore, game.getScore());
        
        values = genValuesOfAKind (3);
        scoreBefore = game.getScore();
        game.setDice (values);
        game.checkLargeStraight();
        assertEquals (valuesToString (values)+ " Not a large straight - should not increment score by "
        , scoreBefore, game.getScore());
    }

    /******************************************************
     * tests LARGE_STRAIGHT
     *****************************************************/
    @Test
    public void testOkLargeStraight()
    {
        int [] values = genValuesStraight (5);      
        game.setDice (values);
        int scoreBefore = game.getScore();
        game.checkLargeStraight ();
        assertEquals ( valuesToString (values) + "Large straight should increment score by " +
            LARGE_STRAIGHT , scoreBefore + LARGE_STRAIGHT, game.getScore());
    }

    /******************************************************
     * tests CHANCE
     *****************************************************/
    @Test
    public void testChance()
    {
        int [] values = generateRandomVals();
        int scoreBefore;
        int sum = 0;
        for (int v : values)
            sum += v;

        game.setDice (values);
        scoreBefore = game.getScore();
        game.checkChance ();
        assertEquals (valuesToString (values) + " Chance should increment score by " +
            sum , scoreBefore + sum, game.getScore());
    }

    /******************************************************
     * Converts an array to a String
     * @param int [] - array of values
     * @return String - 
     *****************************************************/
    private String valuesToString (int [] values){
        String s = "[";
        s += values [0];
        for (int i = 1 ; i <= values.length - 1 ; i++)
            s += "," + values [i];
        s += "]";
        return s;
    }
}
