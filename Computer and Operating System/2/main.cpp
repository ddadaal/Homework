#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdlib.h>
using namespace std;

const char *COLOR_RED = "\033[31m";
// const char *COLOR_RED = "";

const char *COLOR_RESET = "\033[0m";
// const char *COLOR_RESET = "";

extern "C" {
	// void printNum(int decimal);
	void print(const char* pointer);
}

void printNum(int decimal) {
	print(to_string(decimal).c_str());
}

// void print(const char* pointer) {
// 	cout << pointer;
// }

void println(const char* pointer = nullptr) {
	if (pointer != nullptr) {
		print(pointer);
	}
	print("\n");
}

unsigned readToInt(const char *str, int offset = 0, int length = 1)
{

	unsigned result = 0;

	for (int i = 0; i < length; i++, offset++)
	{
		result += (unsigned char)(str[offset]) << i * 8;
	}

	return result;
}

class FileItem;

class Item {
public:
	virtual vector<FileItem*> getSubItems() = 0;
	virtual bool isDirectory() = 0;
};


class FileItem : public Item
{
public:
	string name;
	string ext;
	bool isSpecial;
	bool isDir;
	int writeTime;
	int writeDate;
	const char *contentPointer;
	int size;
	const char* dataSectors;
	const char* fatSector;
	int startCluster;

	string getFullName() {
		if (isDir) {
			return name;
		}
		else {
			return name + '.' + ext;
		}

	}

	bool isDirectory() override {
		return isDir;
	}

	vector<FileItem*> getSubItems() override {
		vector<FileItem*> subItems;
		if (isDir) {
			for (int i = 0;; i += 0x20)
			{
				if (contentPointer[i] != 0)
				{
					//int clusterNo = *(contentPointer + i + 0x1A);
					subItems.push_back(new FileItem(contentPointer + i, dataSectors, fatSector));
				}
				else {
					break;
				}
			}
		}
		return subItems;
	};

private:
	unsigned nextClusterNo(unsigned current) {
		bool isOdd = current % 2 == 1;
		unsigned bytes = readToInt(fatSector, current / 2 * 3, 3); // 3 -> 2, 2/2*3 = 3

		if (isOdd) {
			return bytes >> (3*4); // get high 1.5 bytes
		}
		else {
			return bytes & 0x000FFF; // mask the high 1.5 bytes
		}
	}
public:

	string readContent()
	{
		string content;
		unsigned clusterNo = startCluster;	
		while (clusterNo < 0xFF8) {
			if (clusterNo >= 0xFF0) {
				throw "encountered bad cluster";
			}
			const char* contentPointer = dataSectors + (clusterNo - 2) * 512;
			content += string(contentPointer, 512);
			
			clusterNo = nextClusterNo(clusterNo);
		}
		
		return content.substr(0, size);
	}

	void printItem()
	{
		if (isDir) {
			print(COLOR_RED);
			print(name.c_str());
			print(COLOR_RESET);
		}
		else {
			print(getFullName().c_str());
		}
	}

	FileItem(const char *start, const char *dataSectors, const char* fatSector) : dataSectors(dataSectors), fatSector(fatSector)
	{
		isDir = readToInt(start, 0xB, 1) == 0x10;
		writeTime = readToInt(start, 0x16, 2);
		writeDate = readToInt(start, 0x18, 2);
		size = readToInt(start, 0x1C, 4);
		startCluster = readToInt(start, 0x1A, 2);
		contentPointer = dataSectors + (startCluster - 2) * 512;
		isSpecial = start[0] == '.';

		string name(start, 8);
		for (char i : name)
		{
			if (i != ' ')
			{
				this->name += i;
			}
		}

		string ext(start + 8, 3);
		for (char i : ext) {
			if (i != ' ') 
			{
				this->ext += i;
			}
		}

		

	}
};


class Image : public Item
{
public:
	char *content;

	bool isDirectory() override {
		return true;
	}

	vector<FileItem*> getSubItems() override {
		vector<FileItem*> rootItems;
		char* pointer = RoorDirSectors();
		for (int i = 0;; i += 0x20) {
			if (pointer[i] != 0) {
				rootItems.push_back(new FileItem(pointer + i, DataSectors(), fatSector()));
			}
			else {
				break;
			}
		}
		return rootItems;
	}


	Image(const char *file) {
		const int size = 1440 * 1024;
		content = new char[size];
		ifstream in(file, ios::binary);
		in.read(content, size);
		in.close();
	}

	char *RoorDirSectors()
	{
		return content + (19 * 512); // 1 + 9 + 9 9 sectors for fat
	}

	char *DataSectors()
	{
		const int BPB_RootEntCnt = readToInt(content, 17 ,2);
		const int BPB_BytePerSec = readToInt(content, 11,2);
		const int RootDirSectors = ((BPB_RootEntCnt * 32) + (BPB_BytePerSec - 1)) / BPB_BytePerSec; // 14
		return content + ((19 + RootDirSectors) * 512); // 14 + 19 sector
	}

	~Image()
	{
		delete content;
	}

	char *fatSector()
	{
		return content + (1* 512); // sector 1
	}
};

vector<string> split(string &str, char separator)
{
	vector<string> result;
	string buf;
	for (char c : str)
	{
		if (c == separator)
		{
			if (buf.length() > 0)
			{
				result.push_back(buf);
			}
			buf.clear();
		}
		else
		{
			buf += c;
		}
	}
	if (buf.length() > 0)
	{
		result.push_back(buf);
	}
	return result;
}

Item* findItem(Item* root, vector<string>& paths, int offset) {
	if (root == nullptr) {
		throw "Specified path is not found.";
	}
	if (offset == paths.size()) {
		return root;
	}
	else {
		for (FileItem* item : root->getSubItems()) {
			if (item->getFullName() == paths[offset]) {
				return findItem(item, paths, offset + 1);
			}
		}
		throw "Specified path is not found.";
	}
}

void lsRec(Item* dir, string parent) {
	print(parent.c_str());
	println(":");
	auto subItems = dir->getSubItems();
	for (auto item : subItems) {
		item->printItem();
		print("  ");
	}
	println();
	for (auto item : subItems) {
		if (item->isDir && !item->isSpecial) {
			lsRec(item, parent + item->name + '/');
		}
	}
}

Image image("./a.img");


void normalizePath(string& path) {
	if (path[0] != '/') {
		path = '/' + path;
	}
	if (path[path.size() - 1] != '/') {
		path += '/';
	}
}

void ls(string path)
{
	normalizePath(path);

	vector<string> parents = split(path, '/');
	Item *root = findItem(&image, parents, 0);
	if (root->isDirectory()) {
		lsRec(root, path);
	}
	else {
		throw "Specified path is a file";
	}

}

void cat(string path) {
	vector<string> parents = split(path, '/');
	Item *root = findItem(&image, parents, 0);
	if (root->isDirectory()) {
		throw "Specified path is a directory, not a file.";
	}
	print(((FileItem*)root)->readContent().c_str());
}

void countRec(Item* dir, string parent, int indent) {
	print(parent.c_str());
	print(": ");
	auto subItems = dir->getSubItems();
	int fileCount = 0, dirCount = 0;
	for (auto item : subItems) {
		if (item->isDir && !item->isSpecial) {
			dirCount++;
		}
		else if (!item->isDir) {
			fileCount++;
		}
	}
	printNum(fileCount);
	print(" file(s), ");
	printNum(dirCount);
	print(" dir(s)");
	println();

	for (auto item : subItems) {
		if (item->isDir && !item->isSpecial) {
			for (int i = 0; i < indent * 2; i++) {
				print(" ");
			}
			countRec(item, parent + item->name + '/', indent+1);
		}
	}
}

void count(string path) {

	vector<string> parents = split(path, '/');
	Item *root = findItem(&image, parents, 0);
	


	if (!root->isDirectory()) {
		print(path.c_str());
		print(": ");
		print("1 file, 0 dir");
		return;
	}
	normalizePath(path);

	countRec(root, path, 1);

}

vector<string> splitCommand(string& str) {
	vector<string> result;
	string buf;
	for (auto i =0;i<str.length();i++)
	{
		if (str[i] == ' ')
		{
			if (buf.length() > 0)
			{
				result.push_back(buf);
			}
			buf = "";
			for (auto j = i + 1; j < str.length(); j++) {
				if (str[i] != ' ') break;
			}
		}
		else
		{
			buf += str[i];
		}
	}
	if (buf.length() > 0)
	{
		result.push_back(buf);
	}
	return result;
}


void readAndInterpretCommand() {
	string command;
	getline(cin, command);
	vector<string> separated = splitCommand(command);
	if (separated.size() == 0) {
		return;
	}
	if (separated[0] == "ls") {
		if (separated.size() == 1) {
			ls("/");
		}
		else if (separated.size() > 2) {
			throw "Too many arguments. 0-1 argument is expected.";
		}
		else {
			ls(separated[1]);
		}
	}
	else if (separated[0] == "cat") {
		if (separated.size() == 1) {
			throw "Input a file path";
		}
		else if (separated.size() > 2) {
			throw "Too many arguments. 1 argument is expected.";
		}
		else {
			cat(separated[1]);
		}
	}
	else if (separated[0] == "count") {
		if (separated.size() == 1) {
			count("/");
		}
		else if (separated.size() > 2) {
			throw "Too many arguments. 1 argument is expected.";
		}
		else {
			count(separated[1]);
		}
	}
	else if (separated[0] == "exit") {
		throw 0;
	}
	else {
		throw "Unknown command.";
	}
	
}

int main()
{

	while (true) {
		print("> ");
		try {
			readAndInterpretCommand();
		}
		catch (const char* str) {
			print(str);
			
		} catch (int returnCode) {
			return returnCode;
		}
		println();
		println();

	}
}