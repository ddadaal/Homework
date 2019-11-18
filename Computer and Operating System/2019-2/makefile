all:
	nasm -felf64 asm.asm
	g++ -std=c++11 -m64 -o main.out main.cpp asm.o