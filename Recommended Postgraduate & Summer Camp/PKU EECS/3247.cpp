#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>

const int CAPACITY = 100000;

using namespace std;

int reverse(int i)
{
    int result = 0, numOfBits = 0;

    // test num of bits
    int i1 = i;
    while (i1 > 0)
    {
        numOfBits++;
        i1 /= 10;
    }

    // the result
    for (int j = 0; j < numOfBits; j++)
    {
        result += (i % 10) * pow(10, (numOfBits - j - 1));
        i /= 10;
    }

    return result;
}

int main()
{
    vector<bool> cache(CAPACITY, true);

    for (int i = 2; i * i < CAPACITY; ++i)
    {
        if (cache[i])
        {
            // 这里只需要从 i * i 开始筛选即可，证明见下文
            for (int j = i * i; j < CAPACITY; j += i)
                cache[j] = false;
        }
    }

    int n;
    cin >> n;

    vector<int> result;

    for (int i = pow(10, n-1)+1; i < pow(10, n); i ++)
    {
        if (i == reverse(i) && cache[i])
        {
            result.push_back(i);
        }
    }

    cout << result.size() << endl;

    for (int i = 0; i < result.size(); i++)
    {
        cout << result[i];
        if (i < result.size() - 1)
        {
            cout << " ";
        }
    }
}