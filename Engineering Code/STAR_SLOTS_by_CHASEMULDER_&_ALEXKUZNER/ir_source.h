/*
 * ir_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef IR_SOURCE_H_
#define IR_SOURCE_H_
void ir_source();
void ir_source(){
    //IR sensor
    P4->SEL0 &= ~BIT3;
    P4->SEL1 &= ~BIT3;
    P4->DIR &= ~BIT3;
    P4DIR &= ~BIT3;
    P4REN |= BIT3;
    P4OUT |= BIT3;
    }


#endif /* IR_SOURCE_H_ */
