.globl nCk



nCk:

#a0=n, a1=k, $t0=0, $t1=1, $t2=0

addi $t0, $0, 0   #t0=0            variables

addi $t1, $0, 1   #t1=1

addi $t2, $0, 0   #t2=0

slt $t4, $a0, $t0 #is a0<0

beq $t4, $t1, invalid  #ao<0

slt $t4, $a1, $t0 #is a1<0

beq $t4, $t1, invalid

slt $t4, $a0, $a1  #is n<k

beq $t4, $t1, invalid #            invalid

addi $sp, $sp, -16 #make room	   store

sw $s0, 12($sp)

sw $a1, 8($sp)

sw $a0, 4($sp)    #store $a0

sw $ra, 0($sp)    #store $ra

bne $t0, $a1, L1  #if a1!=0, skip

addi $v0, $0, 1   #return 1        return 1

lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

lw $a1, 8($sp)

lw $s0, 12($sp)

addi $sp, $sp, 16 #restore $sp

jr $ra		  #end                 end

L1:

bne $a0, $a1, L2  #if a0!=a1, skip

addi $v0, $0, 1   #return 1        return 1

lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

lw $a1, 8($sp)

lw $s0, 12($sp)

addi $sp, $sp, 16 #restore $sp

jr $ra		  #end                 end

invalid:          #                invalid

addi $v0, $0, -1  #return in vo    return -1

jr $ra		  #end                 end

L2:

addi $a0, $a0, -1 #a0-1             -1  -1

addi $a1, $a1, -1 #a1-1

jal nCk          #recursive call   jal

lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

lw $a1, 8($sp)

lw $s0, 12($sp)

addi $s0, $v0, 0   #s0=v0          t2=v0

addi $a0, $a0, -1   #a0-1            -1

jal nCk          #recursive call   jal

add $v0, $s0, $v0  #subset+subset=v0   +

lw $ra, 0($sp)    #restore $ra     restore

lw $a0, 4($sp)    #restore $a0

lw $a1, 8($sp)

lw $s0, 12($sp)

addi $sp, $sp, 16  #restore $sp



jr $ra		  #end







