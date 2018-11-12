int func() { return 1; }

int main() {
  int (*a)() = func;
}