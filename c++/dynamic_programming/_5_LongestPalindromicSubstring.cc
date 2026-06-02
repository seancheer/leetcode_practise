//
// Created by ljt on 2026/5/29.
//


#include <iostream>
#include <map>
#include <string>
using namespace std;

class Solution {
public:
    /**
     * 解题思路：最长回文子串，该问题的最优解法就是从每一个位置开始往左右两边进行遍历，试图查找回文，同时更新最长的回文结果，时间复杂度为O(nlogn)
     * 注意处理aba和aa的场景
     * @param s
     * @return
     */
    string longestPalindrome(string s) {
        if (s.size() <= 1) {
            return s;
        }

        // 接下来从每个位置开始查找回文
        string result{s[0]};
        for (int i = 1; i < s.size();) {
            int left = i - 1;
            int right = i + 1;
            // 先跳过所有相等的子串
            while (left >= 0 && s[left] == s[i]) {
                left--;
            }
            while (right < s.size() && s[right] == s[i]) {
                right++;
            }
            int nextIdx = right;
            // 查找对称的回文子串
            while (left >= 0 && right < s.size() && s[left] == s[right]) {
                --left;
                ++right;
            }
            if (right - left - 1 > result.length()) {
                result = s.substr(left + 1, right - left - 1);
            }
            i = nextIdx;
        }
        return result;
    }

    /**
     * 解题思路：最长回文子串，该解法的思路和1一样，不过代码写法更加优雅
     * 注意处理aba和aa的场景
     * @param s
     * @return
 */
    string longestPalindrome2(string s) {
        if (s.size() <= 1) {
            return s;
        }

        // 接下来从每个位置开始查找回文
        string result{s[0]};
        for (int i = 0; i < s.size(); ++i) {
            int left = i - 1;
            int right = i + 1;
            // 计算以s[i]为中心的最长子串，注意下面的代码可以提出来一个函数，这样子代码会更加简洁
            while (left >= 0 && right < s.size() && s[left] == s[right]) {
                if (result.size() < (right - left + 1)) {
                    result = s.substr(left, right - left + 1);
                }
                --left;
                ++right;
            }

            // 计算以s[i + 0.5]为中心的最大子串
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.size() && s[left] == s[right]) {
                if (result.size() < (right - left + 1)) {
                    result = s.substr(left, right - left + 1);
                }
                --left;
                ++right;
            }
        }
        return result;
    }

    /**
     * 解题思路：动态规划的解法，动态规划的状态转移方程为dp[i][j] = true if dp[i + 1][j - 1] == true and s[i] == s[j]
     * 注意，这里需要处理特殊情况，当i+1 > j - 1的时候，说明i和j是挨着的
     * 注意处理aba和aa的场景
     * @param s
     * @return
    */
    string longestPalindrome3(string s) {
        if (s.size() <= 1) {
            return s;
        }
        std::vector<std::vector<bool> > dp{s.size(), std::vector<bool>(s.size(), false)};
        // 初始化dp数组, 注意，单个字符也算作是回文
        string result{s[0]};
        for (int i = 0; i < s.size() - 1; ++i) {
            dp[i][i] = true;
        }

        for (int i = 1; i < s.size(); ++i) {
            for (int j = 0; j < s.size() - i; j++) {
                int end = j + i;
                if (s[j] == s[end]) {
                    if (end - j <= 1) {
                        // 两者相邻
                        dp[j][end] = true;
                    } else if ((end - j > 1) && (dp[j + 1][end - 1])) {
                        // 两者中间还有子串
                        dp[j][end] = true;
                    }
                }

                if (dp[j][end]) {
                    if (result.size() < (end - j + 1)) {
                        result = s.substr(j, end - j + 1);
                    }
                }
            }
        }
        return result;
    }
};

int main() {
    Solution s;

    auto result = s.longestPalindrome("babad");
    auto result2 = s.longestPalindrome2("babad");
    auto result3 = s.longestPalindrome3("babad");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // bab

    result = s.longestPalindrome("cbbd");
    result2 = s.longestPalindrome2("cbbd");
    result3 = s.longestPalindrome3("cbbd");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // bb

    result = s.longestPalindrome("eabcb");
    result2 = s.longestPalindrome2("eabcb");
    result3 = s.longestPalindrome3("eabcb");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // bcb

    result = s.longestPalindrome("bacabab");
    result2 = s.longestPalindrome2("bacabab");
    result3 = s.longestPalindrome3("bacabab");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // bacab

    result = s.longestPalindrome("ac");
    result2 = s.longestPalindrome2("ac");
    result3 = s.longestPalindrome3("ac");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // a

    result = s.longestPalindrome("caac");
    result2 = s.longestPalindrome2("caac");
    result3 = s.longestPalindrome3("caac");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // caac

    result = s.longestPalindrome("bb");
    result2 = s.longestPalindrome2("bb");
    result3 = s.longestPalindrome3("bb");
    printf("%s -- %s -- %s\n", result.c_str(), result2.c_str(), result3.c_str()); // b
}
