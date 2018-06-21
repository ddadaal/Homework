# 4

## Environment

Theoretically available:

Linux x86

Tested:

Xubuntu 18.04 x86

## References

Code is based on Orange's 6 R

## 代码说明

在PROCESS结构体里增加两个成员：int wake_tick和SEMOPHORE* waiting_semophore。

当调用process_sleep，先根据毫秒数算出对应的tick数（ms*1000/HZ），加上当前的tick得到wake_tick。将这个进程的wake_tick设置为这个值，表明这个进程应在哪个tick数之后才能被选中。然后进入schedule方法。

当调用P时，若资源不够，则将这个进程的waiting_semophore设置为需要等待的信号量的指针，然后进入schedule。

V方法里，当信号量的value <=0，从这个信号量的等待列表中去除一个进程，将这个信号量的waiting_semophore设成0（说明这个进程不再等待信号量，可以恢复进行），并将其退出等待列表。

在schedule里面，切换进程时，检查需要被切换的进程的wake_tick和waiting_semophore。若当前tick数小于此进程的wake_tick，说明这个进程仍然睡眠，不能分给时间片，切换到下一个进程；若这个进程的waiting_semophore不是0，说明这个进程还在等待信号量，不能分给时间片，切换到下一个进程。

而对于传参，在系统调用之前，将参数pop到ebx中（先push ebx保护，之后pop ebx恢复）；int 0x90之后，将ebx的值push到栈中，这样就实现了传参。

## Run

under code directory

`make run`