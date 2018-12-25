#include <string>
#include <vector>
using namespace std;

class Solution
{
public:
	bool isMatch(string s, string p)
	{
		vector<vector<int>> dp(s.size()+1, vector<int>(p.size()+1, 0)); // 0 unknown, -1 no, 1 yes
		return rec(s, p, dp, 0, 0);
	}

	bool rec(string &s, string &p, vector<vector<int>> &dp, int i, int j)
	{
		if (dp[i][j] != 0)
		{
			return dp[i][j] == 1;
		}


		bool ans;
		if (j == p.size())
		{
			ans = i == s.size();
		}
		else {
			char base = p[j];

			bool baseMatch = (i < s.size() && (base == '.' || base == s[i]));

			if (j < p.size() - 1 && p[j + 1] == '*')
			{
				ans = rec(s, p, dp, i, j + 2) || (baseMatch && rec(s, p, dp, i + 1, j));
			}
			else {
				ans = baseMatch && rec(s, p, dp, i + 1, j + 1);
			}
		}

		dp[i][j] = ans ? 1 : -1;
		return ans;
	}
};