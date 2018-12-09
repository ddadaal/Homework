
/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                               proc.c
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

/*======================================================================*
                              schedule
 *======================================================================*/
PUBLIC void schedule()
{
	int t = get_ticks();
	// if (p_proc_ready->ticks > 0) {
	// 	p_proc_ready->ticks--;
	// 	return;
	// } else {
	// 	p_proc_ready->ticks = TIME_SLICE;
	// }
	while (1)
	{

		p_proc_ready++;
		if (p_proc_ready >= proc_table + NR_TASKS)
		{
			p_proc_ready = proc_table;
		}
		if (p_proc_ready->waiting_semophore == 0 && p_proc_ready->wake_tick <= t) {
			break;
		}

	}
}

/*======================================================================*
                           sys_get_ticks
 *======================================================================*/
PUBLIC int sys_get_ticks()
{
	return ticks;
}

// PUBLIC void sys_disp_str(char* str) {
// 	disp_str(str);
// }

PUBLIC void do_sys_process_sleep(int ms)
{
	p_proc_ready->wake_tick = get_ticks() + ms / (1000 /HZ);
	schedule();
}


PUBLIC void do_sys_sem_p(SEMOPHORE* s) {
	s->value--;
	if (s->value < 0) {
		p_proc_ready->waiting_semophore = s;

		s->list[s->queue_end] = p_proc_ready;
		s->queue_end = (s->queue_end+1) % SEMOPHORE_LIST_SIZE;
		schedule();
	}
}

PUBLIC void do_sys_sem_v(SEMOPHORE* s) {
	s->value++;
	if (s->value <=0) {
		PROCESS* p = s->list[s->queue_start];
		p->waiting_semophore = 0;
		s->queue_start = (s->queue_start+1) % SEMOPHORE_LIST_SIZE;
	}
}