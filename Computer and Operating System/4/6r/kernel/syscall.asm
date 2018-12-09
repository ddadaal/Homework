
; ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
;                               syscall.asm
; ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
;                                                     Forrest Yu, 2005
; ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

%include "sconst.inc"

extern disp_str
extern do_sys_process_sleep
extern do_sys_sem_p
extern do_sys_sem_v

_NR_get_ticks       equ 0 ; 要跟 global.c 中 sys_call_table 的定义相对应！
_NR_disp_str_with_syscall       equ 1 ; 要跟 global.c 中 sys_call_table 的定义相对应！
_NR_process_sleep equ 2;
_NR_sem_p equ 3
_NR_sem_v equ 4



INT_VECTOR_SYS_CALL equ 0x90

; 导出符号
global	get_ticks
global disp_str_with_syscall
global sys_disp_str
global process_sleep
global sys_process_sleep

global sys_sem_p
global sys_sem_v
global sem_p
global sem_v

bits 32
[section .text]

; ====================================================================
;                              get_ticks
; ====================================================================
get_ticks:
	mov	eax, _NR_get_ticks
	int	INT_VECTOR_SYS_CALL
	ret

disp_str_with_syscall:
	mov eax, _NR_disp_str_with_syscall
	push ebx
	mov ebx, [esp+8]
	int INT_VECTOR_SYS_CALL
	pop ebx
	ret

sys_disp_str:
	; disp_str corrupts some registries. Protect them all before calling
	pusha
	push ebx
	call disp_str
	pop ebx
	popa
	ret

process_sleep:
	mov eax, _NR_process_sleep
	push ebx
	mov ebx, [esp+8]
	int INT_VECTOR_SYS_CALL
	pop ebx
	ret

sys_process_sleep:
	push ebx
	call do_sys_process_sleep
	pop ebx
	ret

sem_p:
	mov eax, _NR_sem_p
	push ebx
	mov ebx, [esp+8]
	int INT_VECTOR_SYS_CALL
	pop ebx
	ret

sem_v:
	mov eax, _NR_sem_v
	push ebx
	mov ebx, [esp+8]
	int INT_VECTOR_SYS_CALL
	pop ebx
	ret

sys_sem_p:
	push ebx
	call do_sys_sem_p
	pop ebx
	ret

sys_sem_v:
	push ebx
	call do_sys_sem_v
	pop ebx
	ret
