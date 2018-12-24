/*
 * @lc app=leetcode id=79 lang=cpp
 *
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (29.60%)
 * Total Accepted:    230.1K
 * Total Submissions: 777.1K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 
 */
#include <vector>
#include <string>
#include <unordered_map>
using namespace std;

class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m=board.size(), n=board[0].size();

        int** map = new int*[m];

        // traversed map and init charmap

        for (int i=0;i<m;i++) {
            map[i] = new int[n];
            for (int j=0;j<n;j++) {
                map[i][j] = 0;
            }
        }

        for (int i=0;i<m;i++) {
            for (int j =0;j<n;j++) {
                if (board[i][j] == word[0]) {
                    map[i][j]=1;
                    bool result = find(board, word, 1, map, i, j);
                    if (result) {
                        return result;
                    } else {
                        map[i][j]=0;
                    }
                }
            }
        }
        return false;
    }

    void tryAdd(vector<vector<char>>& board, char c, int x, int y, vector<pair<int, int>>& possible){
        if (0<=x && x<board.size() && 0<=y && y<board[0].size() && board[x][y] == c){
            possible.push_back(make_pair(x, y));
        }
    }

    bool find(vector<vector<char>>& board, string& word, int wordi, int** map, int x, int y) {
        if (wordi == word.size()) {
            return true;
        } 
        char c = word[wordi];
        vector<pair<int, int>> possible;
        tryAdd(board, c, x+1, y, possible);
        tryAdd(board, c,x-1, y, possible);
        tryAdd(board, c,x, y+1, possible);
        tryAdd(board, c,x, y-1, possible);
        for (auto coord: possible) {
            if (map[coord.first][coord.second] == 1) {
                continue;
            }
            map[coord.first][coord.second] = 1;
            auto result = find(board, word, wordi+1, map, coord.first, coord.second);
            if (result) {
                return true;
            } else {
                map[coord.first][coord.second] = 0;
            }
        }
        return false;
    }
};