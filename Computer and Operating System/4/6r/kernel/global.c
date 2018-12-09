
/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            global.c
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                                    Forrest Yu, 2005
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

#define GLOBAL_VARIABLES_HERE

#include "type.h"
#include "const.h"
#include "protect.h"
#include "proto.h"
#include "proc.h"
#include "global.h"

PUBLIC PROCESS proc_table[NR_TASKS];

PUBLIC char task_stack[STACK_SIZE_TOTAL];

PUBLIC TASK task_table[NR_TASKS] = {
    {TestA, STACK_SIZE_TESTA, "TestA"},
    {barber, STACK_SIZE_BARBER, "barber"},
    {customer_1, STACK_SIZE_CUSTOMER, "customer_1"},
    {customer_2, STACK_SIZE_CUSTOMER, "customer_2"},
    {customer_3, STACK_SIZE_CUSTOMER, "customer_3"}
    };

PUBLIC irq_handler irq_table[NR_IRQ];

PUBLIC system_call sys_call_table[NR_SYS_CALL] = {
    sys_get_ticks,
    sys_disp_str,
    sys_process_sleep,
    sys_sem_p,
    sys_sem_v
    };
