/*
 * button_source.h
 *
 *  Created on: Aug 2, 2019
 *      Author: Owner
 */

#ifndef BUTTON_SOURCE_H_
#define BUTTON_SOURCE_H_

void button_source();
void button_source(){
    //Initializing Button 1
    P6DIR &= ~BIT0;
    P6REN |= BIT0;
    P6OUT |= BIT0;

    //Initializing Button 2
    P6DIR &= ~BIT1;
    P6REN |= BIT1;
    P6OUT |= BIT1;
    //Initializing Button 3
    P3DIR &= ~BIT2;
    P3REN |= BIT2;
    P3OUT |= BIT2;
    //Initializing Button 4
    P3DIR &= ~BIT3;
    P3REN |= BIT3;
    P3OUT |= BIT3;

}



char SwitchStatus_Launchpad_Button1()
{
    return (P6IN & BIT0 );
}
char SwitchStatus_Launchpad_Button2()
{
    return (P6IN & BIT1 );
}
char SwitchStatus_Launchpad_Button3()
{
    return (P3IN & BIT2 );
}
char SwitchStatus_Launchpad_Button4()
{
    return (P3IN & BIT3 );
}


#endif /* BUTTON_SOURCE_H_ */
