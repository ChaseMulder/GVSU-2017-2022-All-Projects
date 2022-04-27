.globl addArray
.data
#$t3=i, $a0=array, $a1=size, $t2=sum
example_arr: .word 2 4 6 8
.text

addArray:
addi $t3, $0, 0 #i=0
addi $t2, $0, 0 #sum=0
loop:
slt $t0, $t3, $a1 #i<size
beq $t0, $0, done #loop till i>size
sll $t0, $t3, 2   #(byte_offset)
add $t0, $t0, $a0 #address array[i]
lw $t1, 0($t0)    #t1=array[i]
add $t2, $t2, $t1 #sum=sum+array[i]
addi $t3, $t3, 1  #i+=1
j loop
done:
addi $v0, $t2, 0 #vo=sum

jr $ra






#lw $t0, 0($a0)  #2
