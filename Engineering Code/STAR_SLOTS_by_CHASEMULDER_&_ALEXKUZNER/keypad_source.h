/*
 * keypad_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef KEYPAD_SOURCE_H_
#define KEYPAD_SOURCE_H_

void keypad_source();
void keypad_source(){
    //initialize rows
    P2SEL0 &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2SEL1 &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2DIR &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2REN |= (BIT4 | BIT5 | BIT6 | BIT7 );
    P2OUT |= (BIT4 | BIT5 | BIT6 | BIT7 );
    }



void printString(char stringType[])
{
    int i;
    for (i = 0; i < 16; i++)
    {
        char letter = stringType[i];
        LCD_DataWrite(letter);
    }
}

int Read_Keypad()
{

    uint8_t row, col;
    for (col = 0; col < 3; col++)
    {
        P5->DIR &= ~(BIT0 | BIT1 | BIT2 ); //Initialize columns port 5 bits 0,1,2
        P5->DIR |= (1 << (col));
        P5->OUT &= ~(1 << (col));
        SysTick_delay_ms(10);
        row = P2->IN & 0xF0;
        while (!(P2->IN & BIT4 ) | !(P2->IN & BIT5 ) | !(P2->IN & BIT6 )
                | !(P2->IN & BIT7 ))
            ; //Initialize rows port 6 bits 0, 1, 4, 5
        if (row != 0xF0)
            break;
    }
    P5->DIR &= ~(BIT0 | BIT1 | BIT2 );
    if (col == 3)
        return 0;
    if (row == 0b11100000)
        return col + 1;
    if (row == 0b11010000)
        return 3 + col + 1;
    if (row == 0b10110000)
        return 6 + col + 1;
    if (row == 0b01110000)
        return 9 + col + 1;

    return -1;

}



#endif /* KEYPAD_SOURCE_H_ */
