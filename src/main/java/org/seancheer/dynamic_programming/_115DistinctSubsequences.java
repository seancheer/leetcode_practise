package org.seancheer.dynamic_programming;

/**
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * <p>
 * A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * It is guaranteed the answer fits on a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * rabbbit
 * rabbbit
 * rabbbit
 * Example 2:
 * <p>
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 1000
 * s and t consist of English letters.
 * <p>
 * 注意，是子序列，不是子串，两者是不同的
 *
 * @author: seancheer
 * @date: 2021/8/15
 **/
public class _115DistinctSubsequences {
    public static void main(String[] args) {
        var obj = new _115DistinctSubsequences();
        String s = "rabbbit";
        String t = "rabbit";
        var res = obj.numDistinct(s, t);
        System.out.println("res:" + res); //3

        s = "babgbag";
        t = "bag";
        res = obj.numDistinct(s, t);
        System.out.println("res:" + res); //5
    }


    /**
     * 解题思路：动态规划问题
     * dp[i][j]表示s的[0,i]的位置可以表示t的[0,j]位置的方法数，那么状态转移方程为：
     * if s[i] != t[j]，那么直接从dp[i - 1][j]继承表示方式，因为当前位置无法表示t[j]位置，所以从dp[i - 1][j]继承
     * if s[i] == t[j]，那么，除了第一种情况的表示方式，还增加了一种新的表示方式，即使用s[i]和s[j]，那么此时的状态还要加上
     * dp[i - 1][j - 1]，即dp[i][j] = dp[i - 1][j - 1];
     * 这道题目可以进行空间的压缩，这里就不写了
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (null == s || s.isEmpty() || null == t || t.isEmpty()) {
            return 0;
        }

        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }

        int[][] dp = new int[sLen][tLen];

        //初始值
        for (int i = 0; i < sLen; ++i) {
            //此举的目的是把状态传下去
            dp[i][0] = dp[Math.max(i - 1, 0)][0];
            if (s.charAt(i) == t.charAt(0)) {
                ++dp[i][0];
            }
        }
        for (int j = 1; j < tLen; ++j) {
            //因为s子序列要表示t，所以s肯定要至少从j的位置开始
            for (int i = j; i < sLen; ++i) {
                //先继承状态
                dp[i][j] = dp[i - 1][j];
                //相等，说明可以凑成一种新的表现方式，那么意味这我们可以从dp[i - 1][j - 1]继承状态，比如
                //babgba和bag，i = 5, j = 1，此时很明显dp[4][0]，有三种表示方式，那么加上a之后，也有三种表示ba的方式。
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen - 1][tLen - 1];
    }
}
