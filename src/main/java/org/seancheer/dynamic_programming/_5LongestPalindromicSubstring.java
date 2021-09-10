package org.seancheer.dynamic_programming;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 * <p>
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 * <p>
 * Input: s = "ac"
 * Output: "a"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 *
 * @author: seancheer
 * @date: 2021/9/2
 **/
public class _5LongestPalindromicSubstring extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _5LongestPalindromicSubstring();
        String str = "babad";
        var res = obj.longestPalindrome(str);
        var res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //bab or aba

        str = "cbbd";
        res = obj.longestPalindrome(str);
        res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //bb

        str = "a";
        res = obj.longestPalindrome(str);
        res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //a

        str = "ac";
        res = obj.longestPalindrome(str);
        res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //a

        str = "abadd";
        res = obj.longestPalindrome(str);
        res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //aba

        str = "aacabdkacaa";
        res = obj.longestPalindrome(str);
        res2 = obj.longestPalindrome2(str);
        System.out.println("res:" + res + "   res2:" + res2); //aba
    }

    /**
     * 解题思路：采用中心扩散的方式，因为有可能有奇数位的子串，也有可能有偶数位的子串，所以两个都要判断
     * 以每一个位置的字符为中心，挨个判断即可
     * 可以参考解法2，动态规划的思路，也比较巧妙
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (null == s || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int len = s.length();
        int resLen = 1;
        int resLeft = 0;
        for (int i = 0; i < len; ++i) {
            //奇数位的判断
            int left = i, right = i;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }

            int tmpLen = right - left - 1;
            if (tmpLen > resLen) {
                resLen = tmpLen;
                resLeft = left + 1;
            }

            left = i;
            right = i + 1;
            ///偶数位
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }

            tmpLen = right - left - 1;
            if (tmpLen > resLen) {
                resLen = tmpLen;
                resLeft = left + 1;
            }
        }

        return s.substring(resLeft, resLeft + resLen);
    }

    /**
     * 解题思路：动态规划的思路
     * 使用dp[i][j]表示[i,j]的回文长度，首先初始值为：dp[i][j] = 1 if i == j，同时为了后面的逻辑统一，如果判断相邻的两个字符串
     * 相等，那么dp[i][j] = 2 if j == i + 1
     *
     * 状态转移方程为，gap为间隔，从2开始
     * dp[j - gap][j] = dp[j - gap + 1][j - 1] + 2 if s[j - gap] == s[j] && dp[j - gap + 1][j - 1] > 0
     * 注意，要保证s[j - gap + 1][j - 1]必须是回文的前提下才有意义
     * 很好理解的状态转移方程
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (null == s || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int len = s.length();
        int[][] dp = new int[len][len];
        int start = 0, resLen = 1;
        //一个肯定是回文
        for (int i = 0; i < len; ++i) {
            dp[i][i] = 1;
            //长度为2的情况下
            if (i - 1 >= 0 && s.charAt(i) == s.charAt(i - 1)) {
                dp[i - 1][i] = 2;
                if (dp[i - 1][i] > resLen) {
                    resLen = dp[i - 1][i];
                    start = i - 1;
                }
            }
        }

        for (int gap = 2; gap < len; ++gap) {
            for (int i = gap; i < len; ++i) {
                //注意，只有在dp[i - gap + 1][i - 1]存在回文的前提下，才有意义，否则就不应该+2
                if (s.charAt(i - gap) == s.charAt(i) && dp[i - gap + 1][i - 1] > 0) {
                    //回文长度加2
                    dp[i - gap][i] = dp[i - gap + 1][i - 1] + 2;
                    if (dp[i - gap][i] > resLen) {
                        resLen = dp[i - gap][i];
                        start = i - gap;
                    }
                }
            }
        }

        return s.substring(start, start + resLen);
    }
}
