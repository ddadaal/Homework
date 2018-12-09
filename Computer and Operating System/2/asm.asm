global print

; print(pointer: rdi: char*): void
print:
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