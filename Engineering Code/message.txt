`timescale 1ns / 1ps

module ALU(A,B,R,C,Z,SEL,FLAG);

input [7:0] A,B;
input [3:0] SEL;
output reg [7:0] R = 7'b0; //result
output reg C = 0;  //carry
output reg Z = 0;
output reg [8:0] FLAG = 8'b0; // test for carry flag

parameter LOAD =   4'b0000;
parameter ADDA =   4'b0001;
parameter SUBA =   4'b0010;
parameter ANDA =   4'b0011;
parameter ORAA =   4'b0100;
parameter COMA =   4'b0101;
parameter INCA =   4'b0110;
parameter LSRA =   4'b0111;
parameter LSLA =   4'b1000;
parameter ASRA =   4'b1001;

always @ (A or B or SEL)
begin
 case(SEL)
 ////////////////////////////////////////////////////     LOAD
 LOAD:
 begin
  R = A;  // set result to A
  C = 1'bx;  // SET C to dontcare
  if (R ==0) Z = 1; // set z to 1 if result is 0
  else Z = 0;
 end
 ////////////////////////////////////////////////////     ADD
 ADDA: 
  begin
   R = A+B;  // compute A + B in 8 bit
   FLAG = A+B; // compute A + B for 9 bit
   C = FLAG[8]; // take 9th bit as carry flag
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 /////////////////////////////////////////////////////     SUBTRACT
 SUBA:
  begin
   R = A-B;  // compute A-B
   if (B>A) C = 1;
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 /////////////////////////////////////////////////////     AND
 ANDA:
  begin
   R = A&B;
   C = 1'bx;
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 ////////////////////////////////////////////////////    OR
 ORAA:
  begin
   R = A|B;
   C = 1'bx;
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 /////////////////////////////////////////////////////   COMPLEMENT
 COMA:
  begin
   R = ~A;
   C = 1;
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 ////////////////////////////////////////////////////   INCREMENT
 INCA:
  begin
   R = A+1;
   C = 1'bx;
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 ///////////////////////////////////////////////////   SHIFTRIGHT
 LSRA:
  begin
   R[7] = 0;
   R[6:0] = A[7:1];
   C = A[0];
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 ///////////////////////////////////////////////////  SHIFTLEFT
 LSLA:
  begin
   R[0] = 0;
   R[7:1] = A[6:0];
   C = A[7];
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
 ///////////////////////////////////////////////////  ARITHSHIFT RIGHT
 ASRA:
  begin
   R[7] = A[7];
   R[6:0] = A[7:1];
   C = A[0];
   if (R ==0) Z = 1; // set z to 1 if result is 0
   else Z = 0;
  end
  
 endcase
 end
endmodule
