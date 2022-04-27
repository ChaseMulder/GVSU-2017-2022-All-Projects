.globl log_two



log_two:

#a0=num, $t0=i, $t1=1

addi $t0, $0, 0   #i=0             variables

addi $t1 $0, 1    #t1=1

slt $t4, $a0, $t1 #is a0<1

beq $t4, $t1, invalid  #ao<1 invalid   



addi $sp, $sp, -8 #make room	   store

sw $a0, 4($sp)    #store $a0

sw $ra, 0($sp)    #store $ra





sub $t3, $a0, $t1 #is a0=1

beq $t3, $0, equal

j else

equal:

addi $v0, $0, 0   #return 0        if block

lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

addi $sp, $sp, 8  #restore $sp

jr $ra		  #end



invalid:                           #invalid

addi $v0, $0, -1 #return in vo    

jr $ra		  #end



else:			           #else

sra $a0, $a0, 1 #div2  #a0/2       else block

jal log_two       #recursive call  jal



lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

addi $sp, $sp, 8  #restore $sp



addi $v0, $v0, 1  #return in vo    return

jr $ra		  #end

