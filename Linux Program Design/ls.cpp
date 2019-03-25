#include <iostream>

using namespace std;

#define EXIT(code, message) { \
    cout << message << endl; \
    return code; \
}

#define OPT_CASE(opt)  \
    case #opt[0]: \
        opt = true; \
        break; \


bool l, d, R, a, i;

int main(int argc, char* argv[]) {
    if (argc < 2) {
        EXIT(1, "usage: ls [–l] (-d|-R|-a|-i)‏")
    }

    l = d = R = a = i = false;

    for (int c=1;c<argc;c++) {
        switch(argv[c][1]) {
            OPT_CASE(l)
            OPT_CASE(d)
            OPT_CASE(R)
            OPT_CASE(a)
            OPT_CASE(i)
            default:
                cout << "unknown option: " << argv[c] << endl;
                return 1;
        }
    }

    cout << l << d << R << a << i << endl;
    return 0;

}