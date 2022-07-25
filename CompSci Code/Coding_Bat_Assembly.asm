.globl indexOf, max, sum13, sum67

indexOf:
	add $t0, $zero, $zero
	add $t1, $a0, $zero
	la $t4, ($a1) 
top_of_loop:
	lb $t2, 0($t4)
	slt $t3, $zero, $t2
	beq $zero, $t3, returnNeg
	beq $t2, $t1, returnPos 
	addi $t0, $t0, 1
	j loopSkipper
loopSkipper:
	addi $t4, $t4, 4
	j top_of_loop
returnPos: 
	add $v0, $zero, $t0
	jr $ra
returnNeg:
	addi $v0, $zero, -1
	jr $ra


max:
add $t0, $zero, $zero
la $t1, ($a0)
add $t2, $zero, $a1
add $t3, $zero, $zero
add $t6, $zero, $zero
lw $t6, 0($t1)

top_loop:
slt $t4, $t3, $t2
beq $t4, $zero, end_loop
lw $t5, 0($t1)
slt $t7, $t5, $t6
beq $t7, $zero, newMax
addi $t1, $t1, 4
addi $t3, $t3, 1
j top_loop

newMax:
add $t6, $t5, $zero
addi $t1, $t1, 4
addi $t3, $t3, 1
j top_loop

end_loop:
add $v0, $zero, $t6
jr $ra

sum13:
add $t0, $zero, $zero
la $t1, ($a0)
add $t2, $zero, $a1
add $t3, $zero, $zero
addi $t6, $zero, 13
subi $t7, $t2, 1

top_loop_sum13:
slt $t4, $t3, $t2
beq $t4, $zero, end_loop_sum13
lw $t5, 0($t1)
beq $t6, $t5, skip13
addi $t1, $t1, 4
add $t0, $t0, $t5
addi $t3, $t3, 1
j top_loop_sum13

skip13:
beq $t3,$t7, end_loop_sum13
addi $t1, $t1, 8
addi $t3, $t3, 2
j top_loop_sum13

end_loop_sum13:
add $v0, $zero, $t0
jr $ra

sum67:
add $t0, $zero, $zero
la $t1, ($a0)
add $t2, $zero, $a1
add $t3, $zero, $zero
addi $t6, $zero, 6
subi $t7, $t2, 1

top_loop_sum67:
slt $t4, $t3, $t2
beq $t4, $zero, end_loopsum67
lw $t5, 0($t1)
beq $t6, $t5, skip6
addi $t1, $t1, 4
add $t0, $t0, $t5
addi $t3, $t3, 1
j top_loop_sum67

end_loopsum67:
add $v0, $zero, $t0
jr $ra

skip6:
addi $t1, $t1, 4
addi $t3, $t3, 1
slt $t4, $t3, $t2
beq $t4, $zero, end_loopsum67
lw $t5, 0($t1)
addi $t6, $t6, 1
j find7

find7:
beq $t6, $t5, remake6
addi $t3, $t3, 1
slt $t4, $t3, $t2
beq $t4, $zero, end_loopsum67
addi $t1, $t1, 4
lw $t5, 0($t1)
j find7

remake6:
subi $t6, $t6, 1
addi $t3, $t3, 1
slt $t4, $t3, $t2
beq $t4, $zero, end_loopsum67
addi $t1, $t1, 4
lw $t5, 0($t1)
j top_loop_sum67
