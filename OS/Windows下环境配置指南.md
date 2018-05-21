# Windows下配置

bochs和nasm都是跨平台的，如果只是为了本系统课程完全不需要自己安装Linux。

注意：

若想更深入的学习Linux环境，安装Linux虚拟机/双系统是**最好的**选择。可是对于bochs来说，由于以下几点原因：

1. bochs、nasm全平台兼容
2. bochs需要GUI，而Linux GUI的坑一点也不比Windows下配置少

使用Linux容易使人心态爆炸，废掉整个周末。

并且，使用Windows，有以下的好处

1. 有熟悉的环境和工具（效率）
2. 节省系统资源（安装虚拟机有显著的性能降低）（效率）
3. 有WSL以应付一些Linux下才能进行的工作

而且，当涉及到内核等操作、非Linux不可时，还可以再去装个简单的Linux系统以完成作业（选择权）。

所以我认为，目前在Windows下操作是比较好的选择。

同时，当以后在Windows上操作过于复杂（比如第三方软件坑多等）时，除了根据本文档配置Windows环境以外，可能使用Linux系统是更好的选择。

## 0. 环境

Windows 10 x64

不需要Windows Subsystem for Linux

## 1. 安装配置bochs

1. 在[这个SourceForge地址](https://sourceforge.net/projects/bochs/)下载安装bochs for Windows。

2. 在PATH环境变量里加入bochs安装地址，默认为`C:\Program Files (x86)\Bochs-2.6.9`

## 2. 安装配置nasm

1. 下载安装nasm（[x64](https://www.nasm.us/pub/nasm/releasebuilds/2.13.03/win64/nasm-2.13.03-installer-x64.exe)，[x86](https://www.nasm.us/pub/nasm/releasebuilds/2.13.03/win32/nasm-2.13.03-installer-x86.exe)），选择所有选项。

2. 在PATH环境变量里加入nasm安装地址，默认为`C:\Program Files\NASM`(x64)或者`C:\Program Files (x86)\NASM`(x86)。

## 3. 安装dd for Windows

PPT里推荐使用dd将二进制文件写入软盘映像，为了保持PPT兼容性，我们使用dd for Windows。

1. 下载[dd for Windows 0.6 beta3](http://www.chrysocome.net/downloads/a6da006f1429d28466a1bb1ea616faf5/dd-0.6beta3.zip)

2. 解压其中的`dd.exe`，将其放入`C:\Program Files (x86)\dd`

3. 在PATH环境变量里加入dd地址，按2操作后的地址为`C:\Program Files (x86)\dd`

## 4. 设置Defender白名单

推荐使用一个单独的文件夹存放OS实验的相关内容。

在使用dd的过程中，可能会遇到操作失败，显示`无法完成操作，因为文件包含病毒或潜在的垃圾软件`的问题。

在Winidows Defender里将OS实验相关文件所在的文件夹设置白名单即可解决这个问题。

操作方法如下：

1. 打开`设置->更新和安全->Windows Defender->打开Windows Defender安全中心`。
2. 点击`病毒与威胁防护->“病毒与威胁防护”设置->(下拉)排除项->添加或删除排除项->添加排除项->文件夹`，选择存放OS实验所需文件的文件夹即可。

## 5. 其他注意事项

及时更新

1. 不需要sdl库。在`bochsrc`里删除`display_library: sdl`行。
2. to be discovered...

