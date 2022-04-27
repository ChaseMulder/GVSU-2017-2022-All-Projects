/*
 * 7seg_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef SEG_SOURCE_H_
#define SEG_SOURCE_H_

void seg_source();
void seg_source(){

    //7 segment display leds

    //
    P9SEL1 &= ~BIT5;
    P9SEL0 &= ~BIT5;
    P9DIR |= BIT5;
    P9OUT &= ~BIT5;
    //
    P7SEL1 &= ~BIT0;
    P7SEL0 &= ~BIT0;
    P7DIR |= BIT0;
    P7OUT &= ~BIT0;
    //
    P7SEL1 &= ~BIT3;
    P7SEL0 &= ~BIT3;
    P7DIR |= BIT3;
    P7OUT &= ~BIT3;
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
}
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



#endif /* SEG_SOURCE_H_ */
