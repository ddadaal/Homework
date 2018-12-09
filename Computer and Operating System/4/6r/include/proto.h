
/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            proto.h
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                                    Forrest Yu, 2005
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

#include "proc.h"
/* klib.asm */
PUBLIC void out_byte(u16 port, u8 value);
PUBLIC u8 in_byte(u16 port);
PUBLIC void disp_str(char *info);
PUBLIC void disp_color_str(char *info, int color);

/* protect.c */
PUBLIC void init_prot();
PUBLIC u32 seg2phys(u16 seg);

/* klib.c */
PUBLIC void delay(int time);

/* kernel.asm */
void restart();

/* main.c */
void TestA();
void barber();
void customer_1();
void customer_2();
void customer_3();

/* i8259.c */
PUBLIC void put_irq_handler(int irq, irq_handler handler);
PUBLIC void spurious_irq(int irq);

/* clock.c */
PUBLIC void clock_handler(int irq);
PUBLIC void milli_delay(int milli_sec);

/* 以下是系统调用相关 */

/* proc.c */
PUBLIC int sys_get_ticks(); /* sys_call */
PUBLIC void do_sys_process_sleep(int);
PUBLIC void do_sys_sem_p(SEMOPHORE*);
PUBLIC void do_sys_sem_v(SEMOPHORE*);


/* syscall.asm */
PUBLIC void sys_call(); /* int_handler */
PUBLIC int get_ticks();
PUBLIC void disp_str_with_syscall(char *);
PUBLIC void sys_disp_str(char*);
PUBLIC void sys_process_sleep(int);
PUBLIC void process_sleep(int);
PUBLIC void sys_sem_p(SEMOPHORE*);
PUBLIC void sys_sem_v(SEMOPHORE*);
PUBLIC void sem_p(SEMOPHORE*);
PUBLIC void sem_v(SEMOPHORE*);