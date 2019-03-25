#include <iostream>
#include <fstream>
#include <ctype.h>

#define EXIT(code, message) { \
    cout << message << endl; \
    return code; \
}

using namespace std;

int main(int argc, char* argv[]) {
    if (argc != 2) {
        EXIT(1, "Please input exactly one file path.")
    }

    ifstream fs(argv[1]);

    if (!fs.is_open()) {
        EXIT(1, "Failed to open file")
    }

    int lineCount = 0, byteCount = 0, wordCount = 0;
    
    int currentWordCount = 0;
    char c;

    while((c = fs.get()) != EOF) {
        byteCount++;
        if (c == '\n') {
            lineCount++;
        } 
        if (isspace(c)) {
            if (currentWordCount > 0) {
                wordCount++;
                currentWordCount = 0;
            }
        } else {
            currentWordCount++;
        }
    }

    if (currentWordCount > 0) {
        wordCount++;
    }

    cout << lineCount << " " << wordCount << " " <<byteCount << " " << argv[1] << endl;
    
}