#include <iostream>
#include <sys/stat.h>
#include <string>
#include <dirent.h>
#include <sys/types.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <vector>

using namespace std;

#define EXIT(code, message) { \
    cout << message << endl; \
    return code; \
}

#define OPT_CASE(opt)  \
    case #opt[0]: \
        opt = true; \
        break; \

#define GREEN "\033[0;32m"
#define GREEN_BG "\033[0;42m"
#define NOCOLOR "\033[0m"

bool l, d, R, a, i;

#define SPACE " "

string joinPaths(vector<const char*>& paths) {
    string path;
    for(int i =0;i<paths.size();i++) {
        path += paths[i];
        if (i != paths.size() - 1){
            path += "/";
        }
    }
    return path;
}

struct File {
    vector<const char*> basePaths;
    const char* name;
    
    struct stat fileStat;

    File(vector<const char*> basePaths, const char* name): basePaths(basePaths), name(name) {

        string realPath = joinPaths(basePaths);
        realPath += "/";
        realPath += name;

        if (stat(realPath.c_str(), &fileStat) != 0)  {
            throw string("Error reading ") + realPath;
        }
    }

    bool isDir() {
        return S_ISDIR(fileStat.st_mode);
    }

    void printPermissions() {
        unsigned short st_mode = fileStat.st_mode;
        cout << (isDir() ? "d" : "-");
        cout << ((st_mode & S_IRUSR) ? "r" : "-");
        cout << ((st_mode & S_IWUSR) ? "w" : "-");
        cout << ((st_mode & S_IXUSR) ? "x" : "-");
        cout << ((st_mode & S_IRGRP) ? "r" : "-");
        cout << ((st_mode & S_IWGRP) ? "w" : "-");
        cout << ((st_mode & S_IXGRP) ? "x" : "-");
        cout << ((st_mode & S_IROTH) ? "r" : "-");
        cout << ((st_mode & S_IWOTH) ? "w" : "-");
        cout << ((st_mode & S_IXOTH) ? "x" : "-");
    }

    void print() {
        if (i) {
            cout << fileStat.st_ino << " ";
        }

        if (l) {
            
            printPermissions();

            cout << SPACE;

            cout << fileStat.st_nlink;
            cout << SPACE;

            // user name
            cout << getpwuid(fileStat.st_uid)->pw_name;
            cout << SPACE;

            // group name
            cout << getgrgid(fileStat.st_gid)->gr_name;
            cout << SPACE;

            cout << fileStat.st_size;
            cout << SPACE;


            // format time
            char buffer [32];
            strftime(buffer, sizeof(buffer), "%b %d %H:%M", localtime(&fileStat.st_mtime));
            cout << buffer;

            cout << SPACE;

        }

        if (isDir()) {
            cout << GREEN_BG;
        } else {
            cout << GREEN;
        }

        cout << name << NOCOLOR;

    }

    bool isSpecial() {
        string str(name);
        return str == "." || str == "..";
    }


    
};

int iterateDir(vector<const char*>& paths)
{
    DIR *dp;
    struct dirent *dirp;

    string realPath = joinPaths(paths);

    if((dp  = opendir(realPath.c_str())) == NULL) {
        EXIT(errno, string("Error opening ")+realPath)
    }

    if (R) {
        cout << realPath << ": " << endl;
    }

    vector<File> files;
    int total = 0;

    while ((dirp = readdir(dp)) != NULL) {
        const char * name = dirp->d_name;

        if (!a && name[0] == '.') {
            continue;
        }

        files.emplace_back(paths, name);

        
        total += files.back().fileStat.st_blocks;

    }

    if (l) {
        cout << "total " << total << endl;
    }

    for (auto& file: files) {
        file.print();
        if (l) {
            cout << endl;
        } else {
            cout << SPACE;
        }
    }

    if (!l) {
        cout << endl;
    }


    if (R) {
        cout << endl;
        for (auto& file: files) {
            if (file.isDir() && !file.isSpecial()){
                paths.push_back(file.name);
                iterateDir(paths);
                paths.pop_back();
            }
        }

    }

    closedir(dp);
    return 0;
}


int main(int argc, char* argv[]) {

    l = d = R = a = i = false;

    for (int c=1;c<argc;c++) {
        for (int optIndex = 1; argv[c][optIndex] != 0; optIndex++) {
            switch(argv[c][optIndex]) {
                OPT_CASE(l)
                OPT_CASE(d)
                OPT_CASE(R)
                OPT_CASE(a)
                OPT_CASE(i)
                default:
                    EXIT(1, string("Unknown options: ")+argv[c])
            }
        }

    }


    string path = ".";

    vector<const char *> paths { "." };

    if (d) {
        File f(paths, ".");
        f.print();
        cout << endl;
        return 0;
    }


    iterateDir(paths);


    return 0;

}