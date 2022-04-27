/*
 * lcd_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef LCD_SOURCE_H_
#define LCD_SOURCE_H_
void lcd_source();
void lcd_source(){
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


#endif /* LCD_SOURCE_H_ */
