
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
#define SCREEN_SIZE (80 * 25)
#define SCREEN_WIDTH 80

#define TAB_WIDTH 8
#define DEFAULT_CHAR_COLOR 0x07

#define CLEAR_SCREEN_INTERVAL 20*1000



// search mode stuff
#define MAX_INPUT SCREEN_WIDTH
#define HIGHLIGHT_COLOR 0x04

/*
search mode
0 not in search mode
1 esc occurred. typing in keyword. 
2 enter occurred. highlighting keyword. only esc is allowed.
*/
PRIVATE int search_mode = 0;
PRIVATE char input_text[MAX_INPUT]; // accept maximum of a line
PRIVATE u32 input_text_pointer =0;
PRIVATE u32 prev_cursor=0; 

PRIVATE void reset_search_mode() {
    search_mode =0;
    input_text_pointer =0;
}


PRIVATE u32 cursor = 0;
PRIVATE u32 current_start_addr = 0;
PRIVATE v_mem_limit = 0;


/* console part */
PUBLIC void set_cursor(u32 pos)
{
    disable_int();
    out_byte(CRTC_ADDR_REG, CURSOR_H);
    out_byte(CRTC_DATA_REG, (pos >> 8) & 0xFF);
    out_byte(CRTC_ADDR_REG, CURSOR_L);
    out_byte(CRTC_DATA_REG, pos & 0xFF);
    enable_int();
}

PRIVATE void set_video_start_addr(u32 addr)
{
    disable_int();
    out_byte(CRTC_ADDR_REG, START_ADDR_H);
    out_byte(CRTC_DATA_REG, (addr >> 8) && 0xFF);
    out_byte(CRTC_ADDR_REG, START_ADDR_L);
    out_byte(CRTC_DATA_REG, addr & 0xFF);
    enable_int();
}

PRIVATE void refresh()
{
    set_cursor(cursor);
    set_video_start_addr(current_start_addr);
}

PUBLIC void scroll_screen(int direction)
{
    if (direction == SCREEN_SCROLL_UP)
    {
        if (current_start_addr > 0)
        {
            current_start_addr -= SCREEN_WIDTH;
        }
    }
    else if (direction == SCREEN_SCROLL_DOWN)
    {
        if (current_start_addr + SCREEN_SIZE < V_MEM_SIZE)
        {
            current_start_addr += SCREEN_WIDTH;
        }
    }
    refresh();
}

PUBLIC void out_char(char ch)
{
    u8 *p_vmem = (u8 *)(V_MEM_BASE + cursor * 2);
    switch (ch)
    {
    case '\t':
        p_vmem[0] = '\t';
        p_vmem[1] = 0x0;
        cursor = TAB_WIDTH * (cursor/TAB_WIDTH + 1);
        break;
    case '\n':
        p_vmem[0] = '\n';
        p_vmem[1] = 0x0; // black background and foreground
        cursor = SCREEN_WIDTH * (cursor / SCREEN_WIDTH + 1);
        break;
    case '\b': {
        int found = 0; // a \n or a \t is found
        if (cursor % SCREEN_WIDTH==0) {
            // cursor at the beginning of a row. attempting to find a \n or \t in the previous row

            u32 previous_start=SCREEN_WIDTH * (cursor/SCREEN_WIDTH-1); // the start of the previous row


            for (;cursor>=previous_start;cursor--) {
                u8 *p = (u8 *)(V_MEM_BASE + cursor * 2);
                if (p[0]== '\n' || p[0] == '\t') {
                    // a \n or \t is found. move the cursor to it and break;
                    found = 1;

                    p[0]= ' ';
                    p[1] = DEFAULT_CHAR_COLOR;
                    break;
                }
            }
        } else if (cursor % TAB_WIDTH==0) {
            u32 previous_start = TAB_WIDTH * (cursor/TAB_WIDTH - 1); // move the cursor to the previous column
            // cursor at the beginning of a column. attempting to find a \t in the previous TAB_SIZE pixels
            for (;cursor>=previous_start;cursor--) {
                u8 *p = (u8 *)(V_MEM_BASE + cursor * 2);
                if (p[0] == '\t') {
                    // a \t is found. move the cursor to it and break;
                    found = 1;
                    
                    p[0] = ' ';
                    p[1] = DEFAULT_CHAR_COLOR;
                    break;
                }
            }
        }
        if (!found) {
            cursor--;
            *(p_vmem - 2) = ' ';
            *(p_vmem - 1) = DEFAULT_CHAR_COLOR;
        }
        break;
    }
    default:
        p_vmem[0] = ch;
        p_vmem[1] = DEFAULT_CHAR_COLOR;
        cursor++;
    }
    while (cursor >= current_start_addr + SCREEN_SIZE)
    {
        scroll_screen(SCREEN_SCROLL_DOWN);
    }
    refresh();
}

PUBLIC void clear_screen()
{
    u8 *base = (u8 *)V_MEM_BASE;
    for (int i = 0; i < V_MEM_SIZE; i += 2)
    {
        base[i] = ' ';
        base[i + 1] = DEFAULT_CHAR_COLOR;
    }
    cursor = 0;
    refresh();
}

PUBLIC void init_screen()
{
    int v_mem_size = V_MEM_SIZE >> 1;
    v_mem_limit = v_mem_size;
    current_start_addr = 0;
    cursor = 0;
    disp_pos = 0;
    set_cursor(cursor);
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

PUBLIC void task_timely_clear_screen() {
    while (1) {
        clear_screen();
        milli_delay(CLEAR_SCREEN_INTERVAL);
    }
}

/*======================================================================*
				in_process
 *======================================================================*/
PUBLIC void in_process(u32 key)
{
    char output[2] = {'\0', '\0'};

    if (search_mode == 2) {
        if ((key & MASK_RAW) == ESC) {
            reset_search_mode();
        }
    }


    if (!(key & FLAG_EXT))
    {
        if (search_mode == 1){
            out_char(key,)
        }
        out_char(key);
    }
    else
    {
        int raw_code = key & MASK_RAW;
        switch (raw_code)
        {

        case ENTER:
            if (search_mode == 1) {
                // enter occurred. jump to mode 2
                search_mode =2;
            } else {
                out_char('\n');
            }

            break;
        case BACKSPACE:
            out_char('\b');
            break;
        case TAB:
            out_char('\t');
            break;
        case ESC:
            if (search_mode == 0) {
                search_mode = 1;
            } else if (search_mode ==2) {
                search_mode =0 ;
            }
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
