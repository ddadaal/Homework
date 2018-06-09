
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
	while (1)
	{

		p_proc_ready++;
		if (p_proc_ready >= proc_table + NR_TASKS)
		{
			p_proc_ready = proc_table;
		}
		if (p_proc_ready->wake_tick==0 || p_proc_ready->wake_tick <= t) {
			p_proc_ready->wake_tick = 0;
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