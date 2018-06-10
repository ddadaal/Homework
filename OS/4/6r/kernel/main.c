
/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            main.c
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                                    Forrest Yu, 2005
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

#include "type.h"
#include "const.h"
#include "protect.h"
#include "proto.h"
#include "string.h"
#include "proc.h"
#include "global.h"

SEMOPHORE customers, barbers, mutex;
int waiting;
#define CHAIRS 3

int customer_color[3] = {
	0x0C,
	0x09,
	0x0E};

void init_semophore(SEMOPHORE *s, int value)
{
	s->value = value;
	s->queue_start = 0;
	s->queue_end = 0;
}

/*======================================================================*
                            kernel_main
 *======================================================================*/
PUBLIC int kernel_main()
{
	disp_str("-----\"kernel_main\" begins-----\n");

	TASK *p_task = task_table;
	PROCESS *p_proc = proc_table;
	char *p_task_stack = task_stack + STACK_SIZE_TOTAL;
	u16 selector_ldt = SELECTOR_LDT_FIRST;
	int i;
	for (i = 0; i < NR_TASKS; i++)
	{
		strcpy(p_proc->p_name, p_task->name); // name of the process
		p_proc->pid = i;					  // pid

		p_proc->ldt_sel = selector_ldt;

		memcpy(&p_proc->ldts[0], &gdt[SELECTOR_KERNEL_CS >> 3],
			   sizeof(DESCRIPTOR));
		p_proc->ldts[0].attr1 = DA_C | PRIVILEGE_TASK << 5;
		memcpy(&p_proc->ldts[1], &gdt[SELECTOR_KERNEL_DS >> 3],
			   sizeof(DESCRIPTOR));
		p_proc->ldts[1].attr1 = DA_DRW | PRIVILEGE_TASK << 5;
		p_proc->regs.cs = ((8 * 0) & SA_RPL_MASK & SA_TI_MASK) | SA_TIL | RPL_TASK;
		p_proc->regs.ds = ((8 * 1) & SA_RPL_MASK & SA_TI_MASK) | SA_TIL | RPL_TASK;
		p_proc->regs.es = ((8 * 1) & SA_RPL_MASK & SA_TI_MASK) | SA_TIL | RPL_TASK;
		p_proc->regs.fs = ((8 * 1) & SA_RPL_MASK & SA_TI_MASK) | SA_TIL | RPL_TASK;
		p_proc->regs.ss = ((8 * 1) & SA_RPL_MASK & SA_TI_MASK) | SA_TIL | RPL_TASK;
		p_proc->regs.gs = (SELECTOR_KERNEL_GS & SA_RPL_MASK) | RPL_TASK;

		p_proc->regs.eip = (u32)p_task->initial_eip;
		p_proc->regs.esp = (u32)p_task_stack;
		p_proc->regs.eflags = 0x1202; /* IF=1, IOPL=1 */

		p_proc->wake_tick = 0;
		p_proc->waiting_semophore = 0;
		p_proc->ticks = TIME_SLICE;

		p_task_stack -= p_task->stacksize;
		p_proc++;
		p_task++;
		selector_ldt += 1 << 3;
	}

	k_reenter = 0;
	ticks = 0;

	init_semophore(&customers, 0);
	init_semophore(&barbers, 0);
	init_semophore(&mutex, 1);
	waiting = 0;

	// clear screen and reset cursor

	u8 *base = (u8 *)V_MEM_BASE;
	for (int i = 0; i < V_MEM_SIZE; i += 2)
	{
		base[i] = ' ';
		base[i + 1] = DEFAULT_CHAR_COLOR;
	}
	disp_pos = 0;

	p_proc_ready = proc_table;

	/* 初始化 8253 PIT */
	out_byte(TIMER_MODE, RATE_GENERATOR);
	out_byte(TIMER0, (u8)(TIMER_FREQ / HZ));
	out_byte(TIMER0, (u8)((TIMER_FREQ / HZ) >> 8));

	put_irq_handler(CLOCK_IRQ, clock_handler); /* 设定时钟中断处理程序 */
	enable_irq(CLOCK_IRQ);					   /* 让8259A可以接收时钟中断 */

	restart();

	while (1)
	{
	}
}

PUBLIC void put_char(u8 ch, u8 color) {
    char str[2] = {'\0','\0'};
	str[0]=ch;
	disp_color_str(str, color);
}


/*======================================================================*
                               TestA
 *======================================================================*/
void TestA()
{
	int i = 0;

	while (1)
	{

		// process_sleep(1000);
	}
}

/*======================================================================*
                               TestB
 *======================================================================*/
void barber()
{
	while (1)
	{
		disp_str_with_syscall("Barber: Waiting for customer\n");
		sem_p(&customers);
		sem_p(&mutex);
		// disp_str_with_syscall("Barber: alive\n");
		waiting--;

		sem_v(&barbers);
		sem_v(&mutex);

		// cut hair
		disp_str_with_syscall("Barber: Cutting hair\n");
		process_sleep(2 * 1000 / HZ);

		// disp_int()
	}
}

void print_customer_log(int i, char *content)
{
	int color = customer_color[i - 1];
	disp_color_str("Customer", color);
	put_char(i+'0', color);
	disp_color_str(": ", color);
	disp_color_str(content, color);
	disp_str("\n");
}

/*======================================================================*
                               TestB
 *======================================================================*/
void customer(int i)
{
	// while (1)
	{
		sem_p(&mutex);
		if (waiting < CHAIRS)
		{
			waiting++;
			print_customer_log(i, "Come and waiting");
			sem_v(&customers);
			sem_v(&mutex);
			sem_p(&barbers);

			// be hair cut
			print_customer_log(i, "Being cut");
			// milli_delay(2 * 1000 / HZ);

			print_customer_log(i, "Done hair cut and leave");
		}
		else
		{
			print_customer_log(i, "Leave directly");
			sem_v(&mutex);
		}
		// milli_delay(3000);
	}
	while (1)
	{
	}
}

void customer_1()
{
	customer(1);
}

void customer_2()
{
	customer(2);
}

void customer_3()
{
	customer(3);
}