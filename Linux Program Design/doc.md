# Linux程序设计第三次作业

161250010 陈俊达

# 0. 通用

本次作业使用C++编写，可在Linux环境下使用C++编译运行。也可以在本目录下运行`./compile.sh wc`或者`./compile.sh ls`编译程序。

已经在Ubuntu 18.04.1 LTS in Windows Subsystem for Linux环境下，使用gcc 7.3版本下编译通过并正常运行。注意使用gcc 5版本无法编译通过（需要支持`"a"[0]`的编译期求值）。

# 1. wc

## 1.1 编译

`./compile.sh wc`或者`g++ wc.cpp -o wc`

## 1.2 运行

`./wc [filename]`

压缩包中提供了`test.txt`文件用以测试。

```
> ./wc test.txt
3 92 601 test.txt
```


## 1.3 设计说明

从`argv[1]`中读到文件名，并使用ifstream将文件打开后，一个字节一个字节地读取文件，每读一个字节，**字节数**++，如果遇到了\n，**行数**++。再次判断这个字节，如果这个字节是非空字符时，**当前单词长度**++；当遇到空字符时（使用`isspace`），如果**当前单词长度**>0，那么**单词个数**++并归零**当前单词长度**；否则（即**当前单词长度**==0），那么什么都不做。当文件读取结束后，如果**当前单词长度**>0，那么**单词个数**++。最后依次输出行数，单词个数和字节数。

## 1.4 与示例代码对比

示例代码

https://www.gnu.org/software/cflow/manual/html_node/Source-of-wc-command.html

不同点：

- 由于示例代码是C代码，所以使用的库不同

- 计算单词数的方法不同

在示例代码中，当遇到一个非空字符的时候，即直接增加单词个数，然后跳到下一个空白符（`getword`函数）。而在我的代码中，是首先记录**当前单词长度**，在遇到空白符时，再增加单词个数。

- 判断字符是否为单词组成元素的方法不同

在示例代码中，是使用`isalpha`函数判断的（示例代码的`isword`函数）；而在我的代码中，是使用`!isspace(c)`判断的。

# 2. ls

## 2.1 编译

`./compile.sh ls`或者`g++ ls.cpp -o ls`

## 2.2 运行

`./ls [-l] [-d] [-a] [-R] [-i]`

压缩包中提供了`dir1`目录用以测试。以下输出是在dir1中运行此程序而得出的。

```
> cd ./dir1

> ../ls -aliR
.:
total 32
4503599628833558 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 .
5910974512386804 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 18:33 ..
1688849860310528 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 dir11
1125899906889221 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 dir12
4222124652122906 -rwxrwxrwx 1 viccrubs viccrubs 14684 Mar 27 18:57 file1

./dir11:
total 0
1688849860310528 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 .
4503599628833558 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 ..
5348024557548983 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 dir111
1688849860310535 -rwxrwxrwx 1 viccrubs viccrubs 0 Mar 27 16:28 file11

./dir11/dir111:
total 0
5348024557548983 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 .
1688849860310528 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 ..
1125899906889217 -rwxrwxrwx 1 viccrubs viccrubs 0 Mar 27 16:28 file111

./dir12:
total 0
1125899906889221 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 .
4503599628833558 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 ..
3096224743863814 -rwxrwxrwx 1 viccrubs viccrubs 0 Mar 27 16:28 file12

> ../ls -di -l

4503599628833558 drwxrwxrwx 1 viccrubs viccrubs 4096 Mar 27 16:28 .

```

## 2.3 设计说明

### 2.3.1 参数解析

程序中有五个全局变量`bool l, d, R, a, i;`用来记录传入的选项。在main函数中，从argv读入每个选项，遍历每个选项中出现的字符，并对出现的每个字母所对应的bool值设为true。

### 2.3.2 File类

在程序中为了控制复杂度，新增了一个File类，用来表示每个文件对象。File类的构造函数要求传入`vector<const char*>`类型的`basePaths`表示文件所在目录，和`const char*`类型的`name`表示文件名。在构造函数中，通过调用Linux提供的`int stat(char const* const _FileName, struct stat* const _Stat)`函数，获得这个文件对应的`stat`对象。这个对象中包括以下数据：

| 数据 | 成员名 | 用途 |
| -- | -- | -- |
| inode号 | st_ino | 打印inode号 |
| 文件权限 | st_mode | 打印文件权限(`printPermission`方法) | 
| 硬连接数 | st_nlink | 打印文件硬连接数 |
| 用户ID | st_uid | `getpwuid(fileStat.st_uid)->pw_name`打印用户名 |
| 组ID | st_gid  | `getgrgid(fileStat.st_gid)->gr_name;`打印组名 |
| 文件大小 | st_size | 打印文件大小 | 
| 占用block | st_blocks | 计算ls第一行`total`数据 |

File提供了`print()`方法，用来打印数据。当有i选项时（`i == true`，下同），首先打印inode号。若存在l选项，则依次打印以上内容。最后，根据文件是否为目录，打印不同颜色的文件名。

### 2.3.2 -d参数

如果传入了-d参数，那么直接使用`File f({ "." }, ".")`构造一个当前目录的.文件的文件对象，直接打印即可结束程序，不需要遍历目录的操作。

### 2.3.3 遍历目录

若不存在-d参数，则使用当前目录`.`，传入`iteratePath(vector<const char*> paths)`函数，遍历当前目录所有的文件。

首先使用`DIR * opendir(const char * path)`函数打开当前目录，获得对应的`DIR`结构。若成功打开，则再使用`struct dirent * readdir(DIR*)`函数，逐个读取当前当前目录的每个文件，拿到每个文件的`dirent`结构体。从这个结构体可以拿到文件名`d_name`，若文件名以`.`开头（表示文件为隐藏文件）而且没有a选项，就忽略这个文件。对每个未被忽略的文件，生成对应的File对象，并加入列表，并在这个过程中统计所有文件的`st_blocks`，以计算total值。当所有文件都加入后，首先打印total值，然后调用每个文件对象的`print()`方法，打印文件信息。

注意，这样计算出的total的值有可能和ls计算出的total值有区别，这是正常现象，是因为ls总以1024B为一个block。参考：

https://unix.stackexchange.com/questions/319359/how-does-linux-calculate-the-total-block-count-when-running-bin-ls-l

最后，若存在R选项，则需要使用深度优先搜索的方法，对刚才记录下的所有文件中的除了`.`和`..`的目录，递归调用`iteratePath`函数。

## 2.4 与示例代码对比

示例代码

https://github.com/wertarbyte/coreutils/blob/master/src/ls.c

示例代码非常长，且非常复杂，这里是我找到的部分不同点：

- 示例代码处理了符号链接
- 示例代码支持更多选项，例如路径，-s，排序（3374行）等
- 示例代码对interrupt等signal进行了特殊处理
- 由于符号连接的存在，示例代码在递归处理的时候，使用了[`obstack`](https://en.wikipedia.org/wiki/Obstack)来记录本次递归中经历的目录的inode，以防止出现死循环
- 示例代码使用`ST_NBLOCKS`宏计算每个文件所占用的块（2935行），从而相加计算total值。宏的具体定义不明，但是在另外一个库([stat-size.h](https://github.com/rofl0r/gnulib/blob/master/lib/stat-size.h))中有3种计算方式：
```c
#define ST_NBLOCKSIZE 1024

// 61行
#define ST_NBLOCKS(statbuf) \
  ((statbuf).st_size / ST_NBLOCKSIZE + ((statbuf).st_size % ST_NBLOCKSIZE != 0))

// 65行
#define ST_NBLOCKS(statbuf) \
  (S_ISREG ((statbuf).st_mode) || S_ISDIR ((statbuf).st_mode) ? \
   st_blocks ((statbuf).st_size) : 0)

// 100行
#define ST_NBLOCKS(statbuf) ((statbuf).st_blocks)

```
其中最后一种计算方式和我的代码相同。使用dir1目录进行测试，ls的结果为16，第一种计算方法得出的结果为23，第三种计算方法的结果为32。第二计算方法依赖`st_blocks`方法，将[fileblocks](https://github.com/digitalocean/gnulib/blob/master/lib/fileblocks.c)模块中的算法移植到程序中，将BSIZE定义为512、1024、2048和4096，计算结果均为46。所以ls具体并不是使用这三种方法中的任何一种。
- 示例代码在打印的时候（`print_horizontal`，4230行），通过计算当前的值的长度和最大值的长度，在打印文件信息（`print_file_name_and_frills`函数，4046行）之前打印了空格进行缩进。我的代码中未处理缩进行为。
- 示例代码使用`void** sorted_file`（312行）模拟vector来保存遍历到的文件对象指针（struct fileinfo *），我的代码中直接使用STL的vector保存文件对象。
