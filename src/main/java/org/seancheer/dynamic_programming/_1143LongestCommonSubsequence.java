package org.seancheer.dynamic_programming;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 * @author: seancheer
 * @date: 2021/8/22
 **/
public class _1143LongestCommonSubsequence {
    public static void main(String[] args) {
        var obj = new _1143LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";
        var res = obj.longestCommonSubsequence(text1, text2);
        System.out.println("res:" + res); //3

        text1 = "abc";
        text2 = "abc";
        res = obj.longestCommonSubsequence(text1, text2);
        System.out.println("res:" + res); //3

        text1 = "abc";
        text2 = "def";
        res = obj.longestCommonSubsequence(text1, text2);
        System.out.println("res:" + res); //0

        text1 = "abcdee";
        text2 = "aceef";
        res = obj.longestCommonSubsequence(text1, text2);
        System.out.println("res:" + res); //4

        text1 = "abcdedsafdaf";
        text2 = "acefdsafdafdasffvewfeafaewfe";
        res = obj.longestCommonSubsequence(text1, text2);
        System.out.println("res:" + res); //10
    }

    /**
     * 解题思路：这是一道非常典型的动态规划问题
     * dp[i][j]表示在text1[0,i]和text2[0,j]中的最长公共子串，那么状态转移方程为：
     * 1 不相等比较简单if text1 != text[2]， dp[i][j] = Math.max(dp[i][j -1], dp[i - 1][j])
     * 2 如果相等，情况就复杂了，即if text1[i] == text2[j]，那么，
     * dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j - 1], dp[i - 1][j])，因为我们也可以不使用这一对。
     * <p>
     * 注意，不要多想，dp[i - 1][j]包含了dp[i - 1][j - 1]，其就是从dp[i - 1][j -1]推算出来的，所以不要重复考虑
     * 空间优化这里就不做了，很简单
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (null == text1 || text1.isEmpty() && (null == text2 || text2.isEmpty())) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                //如果相等的话，那么就多了一种选择，即从dp[i][j]+1而来
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j] + 1, dp[i][j]);
                }
            }
        }
        return dp[len1][len2];
    }
}
