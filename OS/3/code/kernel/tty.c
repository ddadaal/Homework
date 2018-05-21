
/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                               tty.c
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
#include "keyboard.h"


#define SCREEN_SCROLL_DOWN -1
#define SCREEN_SCROLL_UP 1
#define SCREEN_SIZE (80*25)
#define SCREEN_WIDTH 80

#define DEFAULT_CHAR_COLOR 0x07

struct s_console {
    u32 current_start_addr;
    u32 original_addr;
    u32 v_mem_limit;
    u32 cursor;   
} console;

/* console part */
PUBLIC void set_cursor(u32 pos) {
	disable_int();
	out_byte(CRTC_ADDR_REG, CURSOR_H);
	out_byte(CRTC_DATA_REG, (pos>>8)&0xFF);
	out_byte(CRTC_ADDR_REG, CURSOR_L);
	out_byte(CRTC_DATA_REG, pos&0xFF);
	enable_int();
}

PRIVATE void set_video_start_addr(u32 addr) {
    disable_int();
    out_byte(CRTC_ADDR_REG, START_ADDR_H);
    out_byte(CRTC_DATA_REG, (addr >> 8) && 0xFF);
    out_byte(CRTC_ADDR_REG, START_ADDR_L);
    out_byte(CRTC_DATA_REG, addr & 0xFF);
    enable_int();
}

PRIVATE void refresh() {
    set_cursor(console.cursor);
    set_video_start_addr(console.current_start_addr);
}

PUBLIC void scroll_screen(int direction) {
    if (direction == SCREEN_SCROLL_UP){
        if (console.current_start_addr > console.original_addr) {
            console.current_start_addr -= SCREEN_WIDTH;
        }
    } else if (direction == SCREEN_SCROLL_DOWN) {
        if (console.current_start_addr + SCREEN_SIZE < console.original_addr + console.v_mem_limit) {
            console.current_start_addr += SCREEN_WIDTH;
        }
    }
    refresh();
}

PUBLIC void out_char(char ch) {
    struct s_console* con = &console;
    u8* p_vmem = (u8*)(V_MEM_BASE + con->cursor * 2);
    switch (ch) {
        case '\n':
            if (con->cursor < con->original_addr + con->v_mem_limit - SCREEN_WIDTH) {
                con->cursor = con->original_addr + SCREEN_WIDTH * ((con->cursor - con->original_addr) / SCREEN_WIDTH + 1);
            }
            break;
        case '\b':
            if (con->cursor > con->original_addr) {
                con->cursor--;
                *(p_vmem-2) = ' ';
                *(p_vmem-1) = DEFAULT_CHAR_COLOR;
            }
            break;
        default:
            if (con->cursor < con->original_addr + con->v_mem_limit -1) {
                p_vmem[0] = ch;
                p_vmem[1] =DEFAULT_CHAR_COLOR;
                p_vmem +=2;
                con->cursor++;
            }
    }
    while (con->cursor >= con->current_start_addr + SCREEN_SIZE) {
        scroll_screen(SCREEN_SCROLL_DOWN);
    }
    refresh();
}

PUBLIC void scroll_to_cursor() {
    
}

PUBLIC void clear_screen() {
    u8* base = (u8*)V_MEM_BASE;
    for (int i=0;i<V_MEM_SIZE;i+=2) {
        base[i]=' ';
        base[i+1]=DEFAULT_CHAR_COLOR;
    }
    console.cursor =0;
    refresh();
}


PUBLIC void init_screen() {
    int v_mem_size = V_MEM_SIZE >> 1;
    
    console.original_addr = 0;
    console.v_mem_limit = v_mem_size;
    console.current_start_addr = 0;
    console.cursor = 0;
    disp_pos = 0;
    clear_screen();
    set_cursor(console.cursor);
    
    
}


/*======================================================================*
                           task_tty
 *======================================================================*/
PUBLIC void task_tty()
{
    init_screen();
    
    while (1)
    {
        keyboard_read();
    }
}

/*======================================================================*
				in_process
 *======================================================================*/
PUBLIC void in_process(u32 key)
{
    char output[2] = {'\0', '\0'};

    if (!(key & FLAG_EXT))
    {
        out_char(key);
    }
    else
    {
        int raw_code = key & MASK_RAW;
        switch (raw_code) {
        
        case ENTER:
            out_char('\n');
            break;
        case BACKSPACE:
            out_char('\b');
            break;
        case PAGEUP:
            scroll_screen(SCREEN_SCROLL_UP);
            break;
        case PAGEDOWN:
            scroll_screen(SCREEN_SCROLL_DOWN);
            break;
        default:
            break;
        }
    }
}

