.globl makes10 intMax close10 dateFashion



makes10:

# a=$a0, b=$a1, ret=$v0, temp=$t0, 10=$a3

addi $a3, $0, 10 # a3=10

add $t0, $a0, $a1 # t0=a+b

beq $a0, $a3, L1  # if a=10, skip if block

beq $a1, $a3, L1  # if b=10

beq $t0, $a3, L1 # if a+b=1

addi $v0, $0, 0 #v0 = 0, inside if

jr $ra

L1:

addi $v0, $0, 1 #v0 = 0

jr $ra







intMax:

# a=$a0, b=$a1, c=$a2, ret=$v0, max=$t0, temp=$t1, temp2=$t2

slt $t1, $a1, $a0 # is b<a

beq $t1, $0, L2 # if b>=a, skip if block

addi $t0, $a0, 0 #max = a

J L3

L2:

addi $t0, $a1, 0 #max = b

L3:

slt $t2, $t0, $a2 #is max<c

beq $t2, $0, L4 # if max>=c, skip if

addi $t0, $a2, 0 # max = c

L4:

addi $v0, $t0, 0 #return max

jr $ra





close10:

# a=$a0, b=$a1, ret=$v0, aDiff=$t0, bDiff=$t1, temp=$t2, temp2=$t3

addi $t0, $a0, -10

abs $t0, $t0

addi $t1, $a1, -10

abs $t1, $t1

slt $t2, $t0, $t1 #is aDiff<bDiff

beq $t2, $0, L5 #if bDiff>=aDiff, skip if block

addi $v0, $a0, 0 #return a

jr $ra

L5:

slt $t3, $t1, $t0 #is bDiff<aDiff

beq $t3, $0, L6 #if aDiff>=bDiff, skip if block

addi $v0, $a1, 0 #return b

jr $ra

L6:

addi $v0, $0, 0 #return 0

jr $ra







dateFashion:

# you=$a0, date=$a1, ret=$v0, 8=$a3, 2=$a4, temp=$t0, temp2=$t1, temp3=$t3, temp4=$t4, temp5=$t5, temp6=$t6

addi $a3, $0, 8 # a3=8

addi $a2, $0, 2 # a4=2

slt $t0, $a0, $a3 # is you<8

not $t0, $t0

andi $t0, $t0, 1 # !(you<8)

slt $t1, $a2, $a1 #is 2<date

and $t2, $t0, $t1 # 1st and

slt $t3, $a1, $a3 # is date<8

not $t3, $t3

andi $t3, $t3, 1 # !(date<8)

slt $t4, $a2, $a0 #is 2<you

and $t5, $t3, $t4 # 2st and

or $t6, $t2, $t5 # or

beq $t6, $0, L7 #skip if

addi $v0, $0, 2 #return 2

jr $ra

L7:

slt $t4, $a2, $a0 #is 2<you

not $t4, $t4

andi $t4, $t4, 1 # !(you>2)

slt $t7, $a2, $a1 #is 2<date

not $t7, $t7

andi $t7, $t7, 1 # !(2<date)

or $t8, $t4, $t7 #or

beq $t8, $0, L8 #skip if

addi $v0, $0, 0 #return 0

jr $ra

L8:

addi $v0, $0, 1 #return 1

jr $ra
