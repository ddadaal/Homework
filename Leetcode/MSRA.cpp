#include "cppincludes.h"

int main() {
  int m, n;
  while (cin >> m, cin >> n) {
    int** matrix = new int*[m];
    for (int i=0;i<m;i++) {
      matrix[i] = new int[n];
      for (int j=0;j<n;j++) {
        cin >> matrix[i][j];
      }
    }

    cout << countCluster(matrix, m, n) << endl;
  }
}

int countCluster(int **matrix, int m, int n) {

  int ans =0;
  for (int i=0;i<m;i++) {
    for (int j=0;j<n;j++) {
      if (matrix[i][j] == 1) {
        ans++;
        flood(matrix, i, j, m, n);
      }
    }
  }

  return ans;

}

void flood(int **matrix, int i, int j, int m, int n) {
  if (i < 0 || j < 0 || i >= m || j >= n) {
    return;
  }
  if (matrix[i][j] == 0) {
    return;
  }

  matrix[i][j] = 0;
  flood(matrix, i - 1, j - 1, m, n);
  flood(matrix, i, j - 1, m, n);
  flood(matrix, i + 1, j - 1, m, n);
  flood(matrix, i - 1, j, m, n);
  flood(matrix, i + 1, j, m, n);
  flood(matrix, i - 1, j + 1, m, n);
  flood(matrix, i, j + 1, m, n);
  flood(matrix, i + 1, j + 1, m, n);
}