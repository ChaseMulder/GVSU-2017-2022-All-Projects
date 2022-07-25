#include <driverlib.h>

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <LCD_Library.h>
#include <SysTick_Library.h>
#include "LCD_Custom.h"

#define PRESSED 0

void initialize();

void TurnOn_Green_LED();
void TurnOff_Green_LED();
void TurnOn_Red_LED();
void TurnOff_Red_LED();

char SwitchStatus_Launchpad_Button1();
char SwitchStatus_Launchpad_Button2();
char SwitchStatus_Launchpad_Button3();
char SwitchStatus_Launchpad_Button4();

void LCD_Init(void);
void LCD_PulseEnablePin(void);
void LCD_PushNibble(uint8_t nibble);
void LCD_PushByte(uint8_t byte);
void LCD_CommandWrite(uint8_t command);
void LCD_DataWrite(uint8_t data);
void printString(char stringType[]);

int button_status = 0;

unsigned short lastedge, currentedge, period;
void TimerA2Init();
void TAO_N_IRQHandler();
int newFlag;

int keypress;
int arraykeys[3];
int credits_array[3];

int i = 0;

void finalArray();
void storeArray();
void SysTick_Init();
int Read_Keypad();
void SysTick_delay(uint16_t delay);
void print_Array();

void main_Menu();
void playGame();
void Spin();

void Sound_Play(unsigned, unsigned);
void systick_init(void);
void systick_delay(float );
void pause(unsigned short);


// Happy birthday notes
/*                        Hap py  Birth Day  to  you,  Hap py  birth day  to
                         C4   C4   D4   C4   F4   E4   C4   C4   D4   C4   G4 */
unsigned int notes[] = {262, 262, 294, 262, 349, 330, 262, 262, 294, 262, 392,

/*                       you, Hap py  Birth Day  dear  xxxx      Hap  py   birth
                         F4   C4   C4   C5   A4   F4   E4   D4   B4b  B4b  A4 */
                        349, 262, 262, 523, 440, 349, 330, 294, 466, 466, 440,

/*                       day  to  you
                         F4   G4   F4   */
                        349, 392, 349
                        };

unsigned short interval[] = {4, 4, 8, 8, 8, 10, 4, 4, 8, 8, 8, 10, 4, 4, 8, 8, 8,
                             8, 8, 4, 4, 8, 8, 8, 12};


void ADC14_pinInit( Void)
{
P5->SEL1 |= BIT5;
P5->SEL0 |= BIT5;
P5->DIR &= ~ BIT5;
}
void ADC14_preiphInit(void)
{
ADC14->CTL0 &= ~ ADC14_CTL0_ENC;
ADC14->CTL0 |= 0x04200210;
ADC14->CTL1 = 0x00000030;
ADC14->MCTL[0] = 0x00000000;
ADC14->CTL0 |= ADC14_CTL0_ENC;
}


int main(void)
{
    SysTick_Init();
    initialize();
    lcdInit();

   // 95
  //72
   // 63
  //  53
 //   83
   // 91
   // 87
    //86

    P9OUT = P9OUT | BIT5; //on
    P7OUT = P7OUT | BIT2; //on
    P6OUT = P6OUT | BIT3; //on
    P5OUT = P5OUT | BIT3; //on
    P8OUT = P8OUT | BIT3; //on
    P9OUT = P9OUT | BIT1; //on
    P8OUT = P8OUT | BIT7; //on
    P8OUT = P8OUT | BIT6; //on


    while(1);

        P6OUT = P6OUT & ~BIT6;

    uint16_t result;
    float nADC;
    WDT_A->CTL = WDT_A_CTL_PW | WDT_A_CTL_HOLD;
    ADC14_pinInit();
    ADC14_preiphInit();
    SysTick_Init();
    while (1)
    {
    ADC14->CTL0 |= ADC14_CTL0_SC;
    while (!ADC14->IFGR0 & BIT0 );
    result = ADC14->MEM[0];
    nADC = (result * 3.3) / 16384;

    printf("Value is:\n\t%d\n\t%2.2f degrees celsius \n", result, nADC);
    SysTick_delay_ms(100);
    }
    if(result < 1000)
    {


    }
    if(result < 2000)
    {

    }
    if(result < 3000)
    {

    }
    if(result < 4000)
    {

    }
    if(result < 5000)
    {

    }
    if(result < 6000)
    {

    }
    if(result < 7000)
    {

    }
    if(result < 8000)
    {

    }
    if(result < 9000)
    {

    }








    credits_array[0] = 0;
    credits_array[1] = 0;
    credits_array[2] = 0;
    unsigned char custom_char_address  = lcdCreateCustomChar(&stickfig_layout);

    lcdSetChar(custom_char_address,0,1);





    uint32_t i=0;
       systick_init();
       WDT_A->CTL = WDT_A_CTL_PW | WDT_A_CTL_HOLD;     // stop watchdog timer

       // Drive buzzer with P1.7
       P1->DIR |= BIT7;
       P1->OUT &= ~BIT7;
       P1->DIR &= ~BIT4;

       // Use P1.4 (on-board switch) to start song
       P1->REN |= BIT4;
       P1->OUT |= BIT4;
       while(1)
       {
               if ( (P1IN & BIT4) == 0 )
               {
                   for(i = 0; i<25; i++)
                   {
                     Sound_Play(8*notes[i], 100*interval[i]);
                     pause(6);
                   }
                pause(100);
               }
       }




    //Welcome Screen
    LCD_CommandWrite(0x80);
    char stringType[16] = "  Chase Mulder  ";
    printString(stringType);
    LCD_CommandWrite(0xc0);
    strcpy(stringType, "  Alex Kuzner   ");
    printString(stringType);
    LCD_CommandWrite(0xD0);
    strcpy(stringType, "  Slot Machine  ");
    printString(stringType);
    SysTick_delay(300); //delay 3 seconds

    //Main menu function
    main_Menu();

} //end of main

void main_Menu()
{
    //Main Menu
    char stringType[16] = "  Chase Mulder  ";
    LCD_CommandWrite(0x80);
    strcpy(stringType, "1- Sounds       ");
    printString(stringType);
    LCD_CommandWrite(0xc0);
    strcpy(stringType, "2- Enter Credits");
    printString(stringType);
    LCD_CommandWrite(0x90);
    strcpy(stringType, "3- Play         ");
    printString(stringType);
    LCD_CommandWrite(0xD0);
    strcpy(stringType, "                ");
    printString(stringType);

    while (1)
    {
        keypress = Read_Keypad();

        if (keypress == 1)
        {
            //Sounds
        }
        else if (keypress == 2)
        {
            //Enter credits

            LCD_CommandWrite(0x80);
            strcpy(stringType, "Enter Credits:  ");
            printString(stringType);
            LCD_CommandWrite(0xc0);
            strcpy(stringType, "* = cancel      ");
            printString(stringType);
            LCD_CommandWrite(0x90);
            strcpy(stringType, "# = accept      ");
            printString(stringType);

            storeArray(); //Credit storing

        }
        else if (keypress == 3)
        {
            playGame();
        }

    }        //end of while
}

void playGame()
{

    char stringGame[16] = "                ";
    LCD_CommandWrite(0x80);
    strcpy(stringGame, "                ");
    printString(stringGame);
    LCD_CommandWrite(0xc0);
    strcpy(stringGame, "                ");
    printString(stringGame);
    LCD_CommandWrite(0x90);
    strcpy(stringGame, "CR     Menu  BET");
    printString(stringGame);
    LCD_CommandWrite(0xD0);
    strcpy(stringGame, "        *       ");
    printString(stringGame);

    while (1)
    {

        print_Array(); //Credits in lower left
        lcdSetInt(credits_array[0], 13, 3);
        lcdSetInt(credits_array[1], 14, 3);
        lcdSetInt(credits_array[2], 15, 3); //Bet in lower right
        SwitchStatus_Launchpad_Button1();
        SwitchStatus_Launchpad_Button2();
        SwitchStatus_Launchpad_Button3();
        SwitchStatus_Launchpad_Button4();

        //////////////////////////////////////////////////////////////
        if (SwitchStatus_Launchpad_Button1() == PRESSED)
        { //Button debouncing
            SysTick_delay(3);
            if (SwitchStatus_Launchpad_Button1() == PRESSED)
            {
                button_status = 1;
            }
        }

        if (button_status == 1)
        {
            printf("work\n");
            if (credits_array[2] < arraykeys[0] * 100
                    & credits_array[2] < arraykeys[1] * 10
                    & credits_array[2] < 5)
            {
                credits_array[2] = credits_array[2] + 1;
                printf("working\n");
                button_status = 0;
                SysTick_delay(10);
                playGame();
            }

            button_status = 0;

        }
        //////////////////////////////////////////////////////////////
        if (SwitchStatus_Launchpad_Button2() != PRESSED)
        { //Button debouncing
            SysTick_delay(3);
            if (SwitchStatus_Launchpad_Button2() == PRESSED)
            {
                button_status = 1;
            }
        }

        if (button_status == 1)
        {
            printf("work\n");
            if (credits_array[2] > 0)
            {
                credits_array[2] = credits_array[2] - 1;
                printf("working\n");
                button_status = 0;
                SysTick_delay(10);
                playGame();
            }

            button_status = 0;
        }

        //////////////////////////////////////////////////////////////
        if (SwitchStatus_Launchpad_Button3() != PRESSED)
        {
            main_Menu();
        }
        //////////////////////////////////////////////////////////////
        if (SwitchStatus_Launchpad_Button4() != PRESSED)
        {
            Spin();
        }
        //////////////////////////////////////////////////////////////
    } //end while
} //end playGame




void Spin()
{



}

void Sound_Play(unsigned freq_in_hz, unsigned duration_ms)
 {
     uint32_t i = 0;
     float time_period_ms = (1.0/freq_in_hz)*1000.0;
     for(i=0;i<duration_ms;i++){
         P1->OUT |= BIT7;
         systick_delay(time_period_ms);
         P1->OUT &= ~BIT7;
         systick_delay(time_period_ms);
     }
 }
void systick_init()
{
    SysTick->CTRL = 0;  // disable systick
    SysTick->LOAD = 0x00ffffff;
    SysTick->VAL = 0; // clear this register
    SysTick->CTRL = 0x00000005;
}
void systick_delay(float ms)
{
    SysTick->LOAD = (3000 * ms) - 1 ;
    SysTick->VAL = 0;
    while(!(SysTick->CTRL & (1<<16)));
}
void pause(unsigned short i)
{
 unsigned short j;
 for (j = 0; j < i; j++)
     systick_delay(10);
}



void initialize()
{
    //Stop watchdog timer
    WDT_A_hold(WDT_A_BASE);

    //Initializing green LED
    uint8_t greenLED = BIT6;
    P3SEL1 &= ~greenLED;
    P3SEL0 &= ~greenLED; //Green
    P3DIR |= greenLED;
    //Initializing red LED
    uint8_t redLED = BIT9;
    P6SEL1 &= ~redLED;
    P6SEL0 &= ~redLED; //Red
    P6DIR |= redLED;
    //LEDS off
    P2OUT &= ~redLED;
    P2OUT &= ~greenLED;

    //7 segment display leds

           //
           P9SEL1 &= ~BIT5;
           P9SEL0 &= ~BIT5;
           P9DIR |= BIT5;
           P9OUT &= ~BIT5;
           //
           P7SEL1 &= ~BIT2;
           P7SEL0 &= ~BIT2;
           P7DIR |= BIT2;
           P7OUT &= ~BIT2;
           //
           P6SEL1 &= ~BIT3;
           P6SEL0 &= ~BIT3;
           P6DIR |= BIT3;
           P6OUT &= ~BIT3;
           //
           P5SEL1 &= ~BIT3;
           P5SEL0 &= ~BIT3;
           P5DIR |= BIT3;
           P5OUT &= ~BIT3;
           //
           P8SEL1 &= ~BIT3;
           P8SEL0 &= ~BIT3;
           P8DIR |= BIT3;
           P8OUT &= ~BIT3;
           //
           P9SEL1 &= ~BIT1;
           P9SEL0 &= ~BIT1;
           P9DIR |= BIT1;
           P9OUT &= ~BIT1;
           //
           P8SEL1 &= ~BIT7;
           P8SEL0 &= ~BIT7;
           P8DIR |= BIT7;
           P8OUT &= ~BIT7;
           //
           P8SEL1 &= ~BIT6;
           P8SEL0 &= ~BIT6;
           P8DIR |= BIT6;
           P8OUT &= ~BIT6;

    //Initializing Button 1
    P6DIR &= ~BIT0;
    P6REN |= BIT0;
    P6OUT |= BIT0;

    //Initializing Button 2
    P3DIR &= ~BIT2;
    P3REN |= BIT2;
    P3OUT |= BIT2;
    //Initializing Button 3

    //Initializing Button 4

    //LCD
    uint8_t LEDrs = BIT0;
    P4SEL1 &= ~LEDrs;
    P4SEL0 &= ~LEDrs; //RS
    P4DIR |= LEDrs;
    P4OUT &= ~LEDrs;
    uint8_t LEDe = BIT1;
    P4SEL1 &= ~LEDe;
    P4SEL0 &= ~LEDe; //E
    P4DIR |= LEDe;
    P4OUT &= ~LEDe;
    uint8_t LEDdb4 = BIT4;
    P4SEL1 &= ~LEDdb4;
    P4SEL0 &= ~LEDdb4; //DB4
    P4DIR |= LEDdb4;
    P4OUT &= ~LEDdb4;
    uint8_t LEDdb5 = BIT5;
    P4SEL1 &= ~LEDdb5;
    P4SEL0 &= ~LEDdb5; //DB5
    P4DIR |= LEDdb5;
    P4OUT &= ~LEDdb5;
    uint8_t LEDdb6 = BIT6;
    P4SEL1 &= ~LEDdb6;
    P4SEL0 &= ~LEDdb6; //DB6
    P4DIR |= LEDdb6;
    P4OUT &= ~LEDdb6;
    uint8_t LEDdb7 = BIT7;
    P4SEL1 &= ~LEDdb7;
    P4SEL0 &= ~LEDdb7; //DB7
    P4DIR |= LEDdb7;
    P4OUT &= ~LEDdb7;
    LCD_PushByte(0x08);
    SysTick_delay_us(100000);
    LCD_PushByte(0x30);
    SysTick_delay_us(100000);
    LCD_PushByte(0x30);
    SysTick_delay_us(100000);
    LCD_PushByte(0x30);
    SysTick_delay_us(100000);
    LCD_PushByte(0x02);
    SysTick_delay_us(100000);
    LCD_PushByte(0x06);
    SysTick_delay_us(100000);
    LCD_PushByte(0x01);
    SysTick_delay_us(100000);
    LCD_PushByte(0x0F);
    SysTick_delay_us(100000);

    //IR sensor
    P3->SEL0 |= BIT0;
    P3->SEL1 &= ~BIT0;
    P3->DIR &= ~BIT0;

    //Timer A for IR hz sensor
    TIMER_A2->CTL |= TIMER_A_CTL_TASSEL_2 | TIMER_A_CTL_MC__CONTINUOUS
            | TIMER_A_CTL_ID__8 |
            TIMER_A_CTL_CLR;
    TIMER_A2->CCTL[1] = TIMER_A_CCTLN_CM_1 |
            TIMER_A_CCTLN_CCIS_0 |
            TIMER_A_CCTLN_CCIE |
            TIMER_A_CCTLN_CAP |
            TIMER_A_CCTLN_SCS;
    NVIC->ISER[0] = 1 << ((TA2_N_IRQn) & 31);
    __enable_irq();
    TimerA2Init();

    //initialize rows
    P2SEL0 &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2SEL1 &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2DIR &= ~(BIT4 | BIT5 | BIT6 | BIT7 );
    P2REN |= (BIT4 | BIT5 | BIT6 | BIT7 );
    P2OUT |= (BIT4 | BIT5 | BIT6 | BIT7 );

}

void TurnOn_Green_LED()
{
    P3OUT = P3OUT | BIT6;
}
void TurnOff_Green_LED()
{
    P6OUT = P6OUT & ~BIT6;
}
void TurnOn_Red_LED()
{
    P6OUT = P6OUT | BIT7;
}
void TurnOff_Red_LED()
{
    P6OUT = P6OUT & ~BIT7;
}

char SwitchStatus_Launchpad_Button1()
{
    return (P6IN & BIT0 );
}
char SwitchStatus_Launchpad_Button2()
{
    return (P3IN & BIT2 );
}
char SwitchStatus_Launchpad_Button3()
{
    return (P3IN & BIT6 );
}
char SwitchStatus_Launchpad_Button4()
{
    return (P3IN & BIT7 );
}

void LCD_PulseEnable(void)
{
    P4OUT &= ~BIT1;
    SysTick_delay_us(10);
    P4OUT |= BIT1;
    SysTick_delay_us(10);
    P4OUT &= ~BIT1;
}
void LCD_PushNibble(uint8_t nibble)
{
    P4OUT &= ~(0xF0); // ASSUMPTION: D7-D4 are on P4.7=4.4
    P4OUT |= (nibble & 0x0F) << 4; // output nibble value on port pins P4.7-4.4
    LCD_PulseEnable();
}
void LCD_PushByte(uint8_t byte)
{
    uint8_t upper;
    uint8_t lower;
    upper = (byte & 0xF0) >> 4; // mask upper 4 and shift 4 to the right
    lower = (byte & 0x0F); // mask lower 4 and keep
    LCD_PushNibble(upper); // push upper nibble first
    LCD_PushNibble(lower); // «+. then lower nibble
    SysTick_delay_us(100);
}

void LCD_CommandWrite(uint8_t command)
{
    P4OUT &= ~BIT0;
    LCD_PushByte(command);
    //LCD_PulseEnable();
}
void LCD_DataWrite(uint8_t data)
{
    P4OUT |= BIT0;
    LCD_PushByte(data);
    //LCD_PulseEnable();
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

void TA2_N_IRQHandler(void)
{
    currentedge = TIMER_A2->CCR[1];
    period = currentedge - lastedge;
    lastedge = currentedge;
    TIMER_A2->CCTL[1] &= ~(TIMER_A_CCTLN_CCIFG);
    newFlag = period;
    if (25000 < newFlag && newFlag < 28300)
    {
        newFlag = 1;
    }
    if (35000 < newFlag && newFlag < 39000)
    {
        newFlag = 2;
    }
} //end handler

void TimerA2Init(void)
{
    P2->SEL0 |= BIT4;
    P2->SEL1 &= ~BIT4;
    P2->DIR |= BIT4;
    TIMER_A0->CTL = TIMER_A_CTL_SSEL__SMCLK |
            TIMER_A_CTL_MC__UP |
            TIMER_A_CTL_CLR |
            TIMER_A_CTL_ID__8;
    TIMER_A0->CCR[0] = 37500;
    TIMER_A0->CCR[1] = 37500 / 2;
    TIMER_A0->CCTL[1] = TIMER_A_CCTLN_OUTMOD_7;
} //end timer

//sysTick Timer
void SysTick_Init()
{ //Initialize Systick timer for delays we will use
    SysTick->CTRL = 0;
    SysTick->LOAD = 0xFFFFFF;
    SysTick->VAL = 0;
    SysTick->CTRL = BIT2 | BIT0;
}

void SysTick_delay(uint16_t delay)
{
    SysTick->LOAD = ((delay * 3000) - 1);
    SysTick->VAL = 0;
    SysTick->CTRL |= 0x05;
    while ((SysTick->CTRL & BIT(16)) == 0)
        ;
}

int Read_Keypad()
{

    uint8_t row, col;
    for (col = 0; col < 3; col++)
    {
        P5->DIR &= ~(BIT0 | BIT1 | BIT2 ); //Initialize columns port 5 bits 0,1,2
        P5->DIR |= (1 << (col));
        P5->OUT &= ~(1 << (col));
        SysTick_delay(10);
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

void storeArray()
{ //Bit shift left when more than 3 inputs have been inputted

    for (i = 0; i < 3;)
    {
        keypress = Read_Keypad();

        if (keypress == 1)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 2)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 3)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 4)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 5)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 6)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 7)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 8)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 9)
        {
            arraykeys[i] = keypress;
            i++;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 10)
        {

            arraykeys[0] = 0;
            arraykeys[1] = 0;
            arraykeys[2] = 0;
            i = 0;
            print_Array();
            keypress = 13;
        }
        else if (keypress == 11)
        {
            arraykeys[i] = 0;
            i++;
            print_Array();
        }
        else if (keypress == 12)
        {
            main_Menu();
            break;
        }

    }
    i = 0;
    storeArray();
}

void print_Array()
{

    lcdSetInt(arraykeys[0], 0, 3);
    lcdSetInt(arraykeys[1], 1, 3);
    lcdSetInt(arraykeys[2], 2, 3);

}

