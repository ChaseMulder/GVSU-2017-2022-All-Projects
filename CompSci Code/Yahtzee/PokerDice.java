import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
 
/**
 * Chase Mulder CompSci 162 Project 3 Poker Dice
 */


 
public class PokerDice {
    int NumRounds ;
    int NumRolls;
    int score;
    Random r = new Random ();
    final int THREE_OF_A_KIND = 25;      
    final int FOUR_OF_A_KIND = 40;
    final int FIVE_OF_A_KIND = 50;
    final int FULL_HOUSE = 35;
    final int SMALL_STRAIGHT = 30;      
    final int LARGE_STRAIGHT = 45;
    int oldScore;
    
 
    GVdie []  dice;
    int [] tally;
 
    public PokerDice(){
 
        dice = new GVdie[5];
        for (int i =0; i < dice.length; i++){
            dice[i] = new GVdie(r.nextInt(6) + 1);  
        }
        resetGame();
    }
 
    public int getScore( ){
        return score;
    }
 
    public int getNumRolls( ) {
        return NumRolls;
    }
 
    public int getNumRounds( ){
        return NumRounds;
    }
 
    public boolean okToRoll( ) {
        if (getNumRolls() < 4 ){
            return true;
        }
        return false;
    }
 
    public boolean gameOver( ){
        boolean gameOver = false;
        if (getNumRounds() < 8){
            gameOver= true;
        }
        return  gameOver;
    }
 
    public GVdie [] getDice( ){
        return dice;    
    }
 
    public void resetGame ( ){
        score = 0;
        NumRounds = 0;
        NumRolls = 0;
    }
    public ArrayList<Integer> List;
 
    public String diceToString ( ){
        String s = "[";
        s += dice[0];
        for (int i = 2; i <= dice.length; i++)
            s += "," + dice[i].getValue();
        return s += "]";
    }
 
    public void tallyDice(){
        for (int i = 0; i < dice.length; i++){
            for (i = 0; i < tally.length; i++){
                if (dice[i].getValue()==1)
                    tally[1]++;
                if (dice[i].getValue() == 2)
                    tally[2]++;
                if (dice[i].getValue()== 3)
                    tally[3]++;
                if (dice[i].getValue()== 4)
                    tally[4]++;
                if (dice[i].getValue() == 5)
                    tally[5]++;
                if (dice[i].getValue()== 6)
                    tally[6]++;
            }
            return ;  
        }
    }
 
    public void setDice (int [] values){
        for (int i=0; i < dice.length; i++){
            for ( i =0; i < dice.length; i++){
                dice[i] = new GVdie(r.nextInt(6) + 1);    
                if (dice[i].getValue() != values[i])
                    dice[i].roll();
            }
        }
    }
 
    public boolean hasStraight(int length){
        tallyDice();
        int count = 1;
        int max = 1;
 
        for (int i = 1; i < tally.length; i++) {
            if ((tally[i] + 1) == (tally[i + 1]))
                count++;
            else
                count = 1;
            if(count > max)
                return true;
 
        }
        return false;
    }
 
    public boolean hasMultiples(int count){
        int check = 0;
 
        tallyDice();
        for(int i = 1; i < tally.length; i++){
            for(int j = i+1; j < tally.length; j++){
                if(tally[i] >= tally[j])
                    check = 1;
            }
 
        }
        if(check == 1)
            return true;
        else
            return false;
    }
 
    public boolean hasStrictPair(){
        if(hasMultiples(2))
            return true;
        else
            return false;
    }
 
    public void nextRound (){
        NumRounds++;
        for(int i = 0; i <= dice.length; i++){
            dice[i].setBlank();
            dice[i].setHeld(false);
        }
        NumRolls = 0;
    }
 
    public void rollDice(){
        for(int i = 0; i <= dice.length; i++){
            if(!dice[i].isHeld())
                dice[i].roll();
         }
        NumRolls++;
    }
 
    public void checkThreeOfAKind(){
        if(hasMultiples(3) || hasMultiples(4) || hasMultiples(5)){
            score += THREE_OF_A_KIND ;
         }
        nextRound();
    }  
 
    public void checkFullHouse(){
        if(hasMultiples(5) || hasMultiples(3) && hasStrictPair()){
            score += FULL_HOUSE;
         }
        nextRound();
    }    
 
    public void checkSmallStraight(){
         if(hasStraight(4)){
            score += SMALL_STRAIGHT     ;
        }else
            score = score;
         nextRound();    
    }
 
    public void checkLargeStraight(){
        if(hasStraight(5)){
            score += LARGE_STRAIGHT ;  
        }
        nextRound();
    }
 
    public void checkFourOfAKind(){
        if(hasMultiples(4) || hasMultiples(5))
            score += FOUR_OF_A_KIND;
         nextRound();
    }
 
    public void checkFiveOfAKind(){
        if(hasMultiples(5))
            score += FIVE_OF_A_KIND;
         nextRound();
    }
 
    public void checkSingles(int val){
        int count = 0;
        tallyDice();
        for(int i = 1; i < tally.length; i++){
            if(tally[i] == val)
                count++;
        }
        score = val * count;
        checkChance();
        nextRound();
    }
 
    public void checkChance(){
        int count = 0;
        tallyDice();
        for(int i = 1; i < tally.length; i++){
            count += tally[i];
            score += count;
        }
        nextRound();
    }
}
 