/*
 * led_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef LED_SOURCE_H_
#define LED_SOURCE_H_

void led_source();
void led_source(){
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
    }

void TurnOn_Green_LED()
{
    P3OUT |= P3OUT | BIT6;
}
void TurnOff_Green_LED()
{
    P6OUT = P6OUT & ~BIT6;
}
void TurnOn_Red_LED()
{
    P6OUT |= P6OUT | BIT7;
}
void TurnOff_Red_LED()
{
    P6OUT = P6OUT & ~BIT7;
}


#endif /* LED_SOURCE_H_ */
