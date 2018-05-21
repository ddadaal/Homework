%define PRECISION 40 
; precision in decimal, one precision for 100. 40 allows a maximum of 10^80
    global _start

    section .data
inputPrompt:    db "Input LowerBound and UpperBound with a space splitting each other, e.g. 1 3", 10, 0
lowerNeedLtUpperPrompt: db "LowerBound should be less than the UpperBound.", 10, 0
lowerShouldBeGreaterThan0: db "LowerBound needs to be greater than or equal to 0.", 10, 0
red: db `\033[31m`,0
colorReset: db `\033[0m`,0
number1: times PRECISION db 0
number2: times PRECISION db 0
numberTemp: times PRECISION db 0

    section .text
_start: 
        mov rdi, inputPrompt
        call printf
        call readInt
        mov r12, rax        
        call readInt
        mov r13, rax

        cmp r12, 0
        jge check1
        mov rdi, lowerShouldBeGreaterThan0
        call printf
        jmp _start
check1:
        cmp r12, r13
        jl fib
        mov rdi, lowerNeedLtUpperPrompt
        call printf
        jmp _start

fib: ; r12 lower bound, r13 upper bound
        xor rcx, rcx ; rcx as counter
        mov [number2], byte 1 ; initialize number2 as 1. use little-end
        mov r14, 0 ; r14 as the color flag, if r14 is 1, print red; 0, print reset color 
fib_judge_exit:
        cmp rcx, r13 ; rcx > upper bound, exit program
        jg return
        cmp rcx, r12 ; rcx < lower bound, do not print
        jl calculate
printNumber1:
        push rcx
        cmp r14, 0
        jg printNumber1_red
        mov rdi, colorReset
        call printf
        mov r14, 1
        jmp printNumber1_number
printNumber1_red:
        mov rdi, red
        call printf
        mov r14, 0
printNumber1_number:
        mov rdi, number1 ; print number1
        call printHpNumber
        mov rdi, 10
        call putchar
        pop rcx
calculate:
        ; move number1 to numberTemp
        mov rdi, numberTemp
        mov rsi, number1
        call move
    
        ; move number2 to number1
        mov rdi, number1
        mov rsi, number2
        call move

        ; add number2 and numberTemp to number2
        mov rdi, number2
        mov rsi, numberTemp
        call hpAdd

        inc rcx
        jmp fib_judge_exit
        
; move(destAddress: rdi: long, srcAddress: rsi: long) : void, use PRECISION
move:
        push rbp
        mov rbp, rsp
        push r12
        push r13 ; r13 as the temp to move number1 to number temp
        xor r12, r12 ; r12 as counter
move_loop:
        cmp r12, PRECISION
        je move_exit

        mov r13b, byte[rsi]
        mov [rdi], r13b ; [rdi] = [rsi]
        inc rsi
        inc rdi
        inc r12
        jmp move_loop
move_exit:
        pop r13
        pop r12
        leave
        ret

; hpAdd(dest: rdi: long, src: rsi: long) : void, high precision addition, use PRECISION.
hpAdd:
        push rbp
        mov rbp, rsp
        push r12
        push r13 ; r13 as the temp
        push r14 ; r14 as another temp
        push r15 ; r15 carries CF
        push r8 ; r8 for carry detection
        mov r8, 100 ; one byte for a max of 100
        xor r12, r12 ; r12 as counter
        xor r15, r15
hpAdd_loop:
        cmp r12, PRECISION
        je hpAdd_exit
        
        movzx r13, byte [rdi]
        movzx r14, byte [rsi]
        add r13, r14
        add r13, r15
        
        xor rdx, rdx
        mov rax, r13
        div r8 ; result / 100
        mov r15, rax  ; carry to r15
        mov [rdi], dl ; remainder to [rdi]

        inc rdi
        inc rsi
        inc r12
        jmp hpAdd_loop
hpAdd_exit:
        pop r8
        pop r15
        pop r14
        pop r13
        pop r12
        leave
        ret

; printHpNumber(start: rdi: long) : void, print a number, use PRECISION
printHpNumber:
        push rbp
        mov rbp, rsp
        push r12 ; r12 as pointer
        push r13 ; r13 saves start
        push r14 ; r14 as temp
        mov r13, rdi
        mov r12, rdi
        add r12, PRECISION-1 ; r12 initially points to the tail (highest) of the number
printHpNumber_findHighestNotZero:
        cmp r12, r13
        je printHpNumber_print_highest ; if r12 == r13 and the loop hasn't ended, the number is 0, go print it
        movzx r14, byte [r12]
        cmp r14, 0
        jne printHpNumber_print_highest ; [r12] not zero. found. print the highest with actual length
        dec r12
        jmp printHpNumber_findHighestNotZero
printHpNumber_print_highest:
        movzx rdi, byte [r12]
        mov rsi, -1
        call printNum
        dec r12
printHpNumber_loop: ; print all as complete 2-digit form
        cmp r12, r13
        jl printHpNumber_exit ; r12 is in the front of the start. meaning all bytes have been printed.
        movzx rdi, byte [r12]
        mov rsi, 2
        call printNum
        dec r12
        jmp printHpNumber_loop
printHpNumber_exit:
        pop r14
        pop r13
        pop r12
        leave
        ret



; return(returnCode: rdi: long) :void
return: 
        mov eax, 60 ; return
        syscall

; readInt(): long
readInt:    
        push rbp
        mov rbp, rsp
        xor r8, r8 ; r8 to store result
        sub rsp, 1  ; make room for buffer. 1 byte contains 1 char
readInt_read:
        mov rax, 0  ; read
        mov rdi, 0  ; stdin
        mov rsi, rsp ; buffer pointer
        mov rdx, 1 ; length
        syscall
        movzx rax, byte [rsp] ; result is in [rsp], move it to rax
readInt_judge:
        cmp rax, 48 ; <48
        jl readInt_exit 

        imul r8, 10
        sub rax, 48 ; -'0'
        add r8, rax 
        jmp readInt_read
readInt_exit: 
        mov rax, r8
        leave
        ret


; printNum(decimal: rdi: long, length: rsi: long): void
; if length >=0, output the top {length} numbers, extended with 0
; if length <0, output actual number
printNum:
        push rbp
        mov rbp, rsp
        xor rcx, rcx ; number count 12345=5
        mov rax, rdi ; move rdi to rax for division
        push r12
        push r13 
        mov r12, 10 ; r12 stores 10 only
printNum_convert:
        cmp rax, 10
        jl printNum_print_init
        xor rdx, rdx ; for remainder
        div r12
        add rdx, 48 ; + '0' to ascii
        push rdx
        inc rcx
        jmp printNum_convert
printNum_print_init:
        add rax, 48 ; push the highest number
        push rax
        inc rcx
        cmp rsi, 0 ; length <0, print actual numbers
        jl printNum_print
        cmp rsi, rcx
        jg printNum_print_fill_with_0 ; length > actual, fill with zero
        mov rcx, rsi ; replace rcx with rsi
printNum_print_fill_with_0:
        sub rsi, rcx ; rsi: 0s that need to be filled
printNum_print_fill_with_0_loop:
        cmp rsi, 0
        je printNum_print
        push 48
        dec rsi
        inc rcx
        jmp printNum_print_fill_with_0_loop
printNum_print:
        cmp rcx, 0
        je printNum_exit ; all number printed, exit
        pop rdi
        push rcx
        call putchar
        pop rcx
        dec rcx
        jmp printNum_print
printNum_exit:
        pop r13
        pop r12
        leave
        ret


; printf(pointer: rdi: char*): void
printf:
        push rbp
        mov rbp, rsp
        call strLength
        mov rdx, rax ; size
        mov rsi, rdi ; pointer
        mov rdi, 1 ; stdout
        mov rax, 1 ; write
        syscall
        leave
        ret

; strLength(pointer: rdi: char*) : long
strLength:
        push rbp
        mov rbp, rsp
        xor rax, rax ; result
        mov rcx, rdi ; address
strLength_loop:
        cmp [rcx], byte 0
        je strLength_exit
        inc rcx
        inc rax
        jmp strLength_loop
strLength_exit:
        leave
        ret


; putchar(rdi: char): void
putchar:    
        push rbp
        mov rbp, rsp
        push rax
        sub rsp, 1
        mov [rsp], dil
        mov rax, 1
        mov rdi, 1
        mov rsi, rsp
        mov rdx, 1
        syscall
        pop rax
        leave
        ret