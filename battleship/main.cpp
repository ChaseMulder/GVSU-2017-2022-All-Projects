#include <iostream>
#include <string>
#include <random>
#include <chrono>
#include <thread>
#include <time.h>
#include "randutils.hpp"
#include "sleepTimer.hpp"

//Random Number Generator Library
//https://gist.github.com/imneme/540829265469e673d045

/**********************************************************************
* C++ Battle Ship Game
*
**********************************************************************/

/* Board Constraints */
const int BOARD_WIDTH = 10;
const int BOARD_HEIGHT = 10;
const int SHIP_TYPES = 5;

/* Game Coordinate Types */
const char isWATER = 247; //ASCII Character Code
const char isHIT = 'X';
const char isSHIP = 'S';
const char isMISS = '0';

/* Coordinate Structure */
struct POINT {
    int X; //X(horizontal) Y(vertical) coordinates
    int Y;
};

/* Ship Structure */
struct SHIP {
    string name;//Ship name
    int length;//Length of ship
    POINT onGrid[5]; //0-4 ships
    bool hitFlag[5];
} ship[SHIP_TYPES];

/* Player Structure */
struct PLAYER {
    char grid[BOARD_WIDTH][BOARD_HEIGHT];
} player[3]; //player 1 user, player 2 computer, 0 unused

/* Enumeration Directions */
enum DIRECTION {HORIZONTAL,VERTICAL};

/* Place Ships Structure */
struct PLACESHIPS {
    DIRECTION direction;
    SHIP shipType;
};

/* Boolean Variables */
bool gameRunning = false;

/* Functions */
void LoadShips();
void ResetBoard();
void DrawBoard(int);
PLACESHIPS UserInputShipPlacement();
PLACESHIPS UserInputShipPlacementRand();
bool UserInputAttack(int&,int&,int);
bool UserInputAttackRand(int& x, int& y, int theplayer);
bool GameOverCheck(int);

/**************************** MAIN ***********************************/
int main() {
    LoadShips();
    ResetBoard();

    /**************************** PLACE SHIPS USER ***********************/
    int sPlayer=1; //1 = user, 2 = computer;
    //Loop through each ship type to place
    for (int thisShip=0; thisShip<SHIP_TYPES; ++thisShip) {
        //Display Game Board for user
        system("cls");
        DrawBoard(sPlayer);
        //Instruction Phase
        cout << "\n";
        cout << "INSTRUCTIONS (Player " << sPlayer << ")\n";
        cout << "Place Ships Phase.\nFormat should be:\n";
        cout << "Facing (0:Horizontal, 1:Vertical)\nX (horizontal) coords\nY (vertical) coords\n";
        cout << "Example: 0 7 2 would place a ship at X:7 Y:2 going horizontal\n\n";
        cout << "Ship to place: " << ship[thisShip].name << " which has a length of " << ship[thisShip].length  << "\n";
        cout << "Where do you want it placed? ";

        //Get good user input for ship placement
        PLACESHIPS bShip;
        bShip.shipType.onGrid[0].X = -1;
        while (bShip.shipType.onGrid[0].X == -1) {
            bShip = UserInputShipPlacement();
        }

        //Combine user data with THIS ship data
        bShip.shipType.length = ship[thisShip].length;
        bShip.shipType.name = ship[thisShip].name;

        //Add first grid point to player's board
        player[sPlayer].grid[bShip.shipType.onGrid[0].X][bShip.shipType.onGrid[0].Y] = isSHIP;

        //Determining all non-ship grid points
        for (int i=1; i<bShip.shipType.length; ++i) {
            if (bShip.direction == HORIZONTAL) {
                bShip.shipType.onGrid[i].X = bShip.shipType.onGrid[i-1].X+1;
                bShip.shipType.onGrid[i].Y = bShip.shipType.onGrid[i-1].Y;
            }
            if (bShip.direction == VERTICAL) {
                bShip.shipType.onGrid[i].Y = bShip.shipType.onGrid[i-1].Y+1;
                bShip.shipType.onGrid[i].X = bShip.shipType.onGrid[i-1].X;
            }

            //Adding ship grid points
            if(player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] == isSHIP) { //Not placing over other ships
                player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] = isHIT;   //If placing over another ship, mark as HIT
            } else {
                player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] = isSHIP;//If not placing over another ship, mark as SHIP
            }
        }
        //Loop through each ship type
    }

    /**************************** PLACE SHIPS COMPUTER *******************/
    sPlayer=2;//sPlayer = 2, is for computer
    for (int thisShip=0; thisShip<SHIP_TYPES; ++thisShip) {
        PLACESHIPS bShip;
        bShip.shipType.onGrid[0].X = -1;
        DrawBoard(sPlayer);
        sleep_for(nanoseconds(10));
        sleep_until(system_clock::now() + seconds(1));//Sleep for 1 second to show computer's moves
        //Get random good computer input
        while (bShip.shipType.onGrid[0].X == -1) {
            bShip = UserInputShipPlacementRand();//Random ship placement for computer
        }

        //Combine computer data with THIS ship data
        bShip.shipType.length = ship[thisShip].length;
        bShip.shipType.name = ship[thisShip].name;

        //Adding first computer grid point
        player[sPlayer].grid[bShip.shipType.onGrid[0].X][bShip.shipType.onGrid[0].Y] = isSHIP;

        //Adding all non-ship grid points
        for (int i=1; i<bShip.shipType.length; ++i) {
            if (bShip.direction == HORIZONTAL) {
                bShip.shipType.onGrid[i].X = bShip.shipType.onGrid[i-1].X+1;
                bShip.shipType.onGrid[i].Y = bShip.shipType.onGrid[i-1].Y;
            }
            if (bShip.direction == VERTICAL) {
                bShip.shipType.onGrid[i].Y = bShip.shipType.onGrid[i-1].Y+1;
                bShip.shipType.onGrid[i].X = bShip.shipType.onGrid[i-1].X;
            }

            //Adding ship grid points
            if(player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] == isSHIP) { //Not placing over other ships
                player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] = isHIT;//If placing over another ship, mark as HIT
            } else {
                player[sPlayer].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] = isSHIP;//If not placing over another ship, mark as SHIP
            }
        }
        //Loop through each computer's ship type
    }

    DrawBoard(sPlayer);
    sleep_for(nanoseconds(10));
    sleep_until(system_clock::now() + seconds(5));//Wait 5 seconds to show computer's game board

    /**************************** GAME PHASE *****************************/
    gameRunning = true;
    int displayPlayer = 1;
    do {
        //Display opponent's board when attacking
        int enemyPlayer;
        if (displayPlayer == 1) enemyPlayer = 2;
        if (displayPlayer == 2) enemyPlayer = 1;
        system("cls");
        DrawBoard(enemyPlayer);

        //Get attack coodrinates from current player
        bool goodInput = false;
        int x,y;
        //Get good user/computer input for attacking
        while (goodInput == false && displayPlayer ==1) {
            goodInput = UserInputAttack(x,y,displayPlayer);//User Attack input
        }
        while (goodInput == false && displayPlayer !=1) {
            goodInput = UserInputAttackRand(x,y,displayPlayer);//Computer random attack input
        }

        //Checking board, if HIT on coordinates where there's a SHIP, mark HIT; otherwise, mark miss
        if (player[enemyPlayer].grid[x][y] == isSHIP) player[enemyPlayer].grid[x][y] = isHIT;
        if (player[enemyPlayer].grid[x][y] == isWATER) player[enemyPlayer].grid[x][y] = isMISS;

        //Game over checking, If 0 returned, keep playing
        int aWin = GameOverCheck(enemyPlayer);
        if (aWin != 0) {
            gameRunning = false;
            break;
        }
        //Alternate between user and computer playing
        displayPlayer = (displayPlayer == 1) ? 2 : 1;
    } while (gameRunning);
    /**************************** GAME OVER ******************************/
    system("cls");//Out of while, so a player has won
    cout << "\n\nCONGRATULATIONS!!!  PLAYER " << displayPlayer << " HAS WON THE GAME!\n\n\n\n";//Winner
    system("pause");
    return 0;
}

/**
* LoadShips()
* @param none
* @return void
**/
void LoadShips() {
    //Setting default ship data
    ship[0].name = "Cruiser";
    ship[0].length = 2;
    ship[1].name = "Frigate";
    ship[1].length = 3;
    ship[2].name = "Submarine";
    ship[2].length = 3;
    ship[3].name = "Escort";
    ship[3].length = 4;
    ship[4].name = "bShip";
    ship[4].length = 5;
}

/**
* GameOverCheck()
* @param int enemyPLAYER
* @return bool
**/
bool GameOverCheck(int enemyPLAYER) {
    bool winner = true;
    //Loop through entire enemy board
    for (int w=0; w<BOARD_WIDTH; ++w) {
        for (int h=0; h<BOARD_HEIGHT; ++h) {
            //If ships are present, game isn't over
            if (player[enemyPLAYER].grid[w][h] = isSHIP) {
                winner = false;
                return winner;
            }
        }
    }
    //Out of loop, user has WON the game, return TRUE
    return winner;
}

/**
* UserInputAttack()
* @param int& x, int& y, int theplayer
* @return bool
**/
bool UserInputAttack(int& x, int& y, int theplayer) {
    cout << "\nPLAYER " << theplayer << ", ENTER COORDINATES TO ATTACK: ";
    bool goodInput = false;
    cin >> x >> y;
    if (x<0 || x>=BOARD_WIDTH) return goodInput;
    if (y<0 || y>=BOARD_HEIGHT) return goodInput;
    goodInput = true;
    return goodInput;
}

/**
* UserInputAttackRand()
* @param int& x, int& y, int theplayer
* @return bool
**/
bool UserInputAttackRand(int& x, int& y, int theplayer) {
    randutils::mt19937_rng rng;
    x = rng.uniform(0,9);
    y = rng.uniform(0,9);
    cout << "\nPLAYER " << theplayer << ", ENTER COORDINATES TO ATTACK: ";
    bool goodInput = false;
    cout << "Computer Attacking:" << x << " " << y;
    sleep_for(nanoseconds(10));
    sleep_until(system_clock::now() + seconds(3));
    if (x<0 || x>=BOARD_WIDTH) return goodInput;
    if (y<0 || y>=BOARD_HEIGHT) return goodInput;
    goodInput = true;
    return goodInput;
}

/**
* UserInputShipPlacementRand()
* @param none
* @return PLACESHIPS
**/
PLACESHIPS UserInputShipPlacementRand() {
    int d, x, y;
    randutils::mt19937_rng rng;
    d = rng.uniform(0,1);
    x = rng.uniform(0,8);
    y = rng.uniform(0,8);
    PLACESHIPS tmp;
    //Using -1 for bad return
    tmp.shipType.onGrid[0].X = -1;
    sleep_for(nanoseconds(10));
    sleep_until(system_clock::now() + seconds(1));//Sleep for 1 second to show computer's moves
    cout << "Computer Placing At:" << d << " "<< x << " " << y << "\n";
    if (d!=0 && d!=1) return tmp;
    if (x<0 || x>=BOARD_WIDTH) return tmp;
    if (y<0 || y>=BOARD_HEIGHT) return tmp;
    if (player[2].grid[x][y] == isSHIP) return tmp;//Don't allow ships to be placed over other ships
    //Good data, so place ship on board
    tmp.direction = (DIRECTION)d;
    tmp.shipType.onGrid[0].X = x;
    tmp.shipType.onGrid[0].Y = y;
    return tmp;
}

/**
* UserInputShipPlacement()
* @param none
* @return PLACESHIPS
**/
PLACESHIPS UserInputShipPlacement() {
//    PLACESHIPS bShip;
    int d, x, y;
    PLACESHIPS tmp;
    //Using -1 for bad return
    tmp.shipType.onGrid[0].X = -1;

    //Getting 3 integers from user
    cin >> d >> x >> y;
    if (d!=0 && d!=1) return tmp;
    if (x<0 || x>=BOARD_WIDTH) return tmp;
    if (y<0 || y>=BOARD_HEIGHT) return tmp;
    if (player[1].grid[x][y] == isSHIP) return tmp;//Don't allow ships to be placed over other ships
//    int X = x;
//    int Y = y;
//    for (int i=1; i<bShip.shipType.length; ++i) {
//            if(player[1].grid[bShip.shipType.onGrid[i].X][bShip.shipType.onGrid[i].Y] == isSHIP)
//            return tmp;
//    }
    //Good data, so place ship on board
    tmp.direction = (DIRECTION)d;
    tmp.shipType.onGrid[0].X = x;
    tmp.shipType.onGrid[0].Y = y;
    return tmp;
}

/**
* ResetBoard()
* @param none
* @return void
**/
void ResetBoard() {
    //Loop through each player
    for (int plyr=1; plyr<3; ++plyr) {
        //Setting each grid point to water
        for (int w=0; w<BOARD_WIDTH; ++w) {
            for (int h=0; h<BOARD_HEIGHT; ++h) {
                player[plyr].grid[w][h] = isWATER;
            }
        }
        //Loop to next player
    }
}


/**
* DrawBoard()
* @param int displayPlayer
* @return void
**/
void DrawBoard(int displayPlayer) {
    //Drawing board for displayPlayer
    cout << "PLAYER " << displayPlayer << "'s GAME BOARD\n";
    cout << "----------------------\n";
    //Loop through board_width to display top row
    cout << "   ";
    for (int w=0; w<BOARD_WIDTH; ++w) {
        if (w < 10)
            //Numbers only 1 character long, add two spaces after
            cout << w << "  ";
        else if (w >= 10)
            //Numbers 2 characters long, add only 1 space after
            cout << w << " ";
    }
    cout << "\n";
    //Loop through each grid point and display to console
    for (int h=0; h<BOARD_HEIGHT; ++h) {
        for (int w=0; w<BOARD_WIDTH; ++w) {
            //If this is the first grid point, number the grid first
            if (w==0) cout << h << " ";
            //If h was 1 character long, add an extra space to keep numbers lined up
            if (w<10 && w==0) cout << " ";
            //Display contents of grid with ships placed
            if (gameRunning == false) cout << player[displayPlayer].grid[w][h] << "  ";
            //Display contents of grid showing HITS, but not showing SHIPS
            if (gameRunning == true && player[displayPlayer].grid[w][h] != isSHIP) {
                cout << player[displayPlayer].grid[w][h] << "  ";
            } else if (gameRunning == true && player[displayPlayer].grid[w][h] == isSHIP) {
                cout << isWATER << "  ";
            }
            //If we have reached the border, line feed
            if (w == BOARD_WIDTH-1) cout << "\n";
        }
    }
}
