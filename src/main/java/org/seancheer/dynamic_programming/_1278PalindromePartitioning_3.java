package org.seancheer.dynamic_programming;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 * <p>
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 * <p>
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 * <p>
 * Input: s = "leetcode", k = 8
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 *
 * @author: seancheer
 * @date: 2021/8/3
 **/
public class _1278PalindromePartitioning_3 {

    public static void main(String[] args) {
        _1278PalindromePartitioning_3 obj = new _1278PalindromePartitioning_3();
        String s = "abc";
        int k = 2;
        var res = obj.palindromePartition(s, k);
        System.out.println("res:" + res); //1

        s = "aabbc";
        k = 3;
        res = obj.palindromePartition(s, k);
        System.out.println("res:" + res); //0

        s = "leetcode";
        k = 8;
        res = obj.palindromePartition(s, k);
        System.out.println("res:" + res); //0

        s = "acab";
        k = 3;
        res = obj.palindromePartition(s, k);
        System.out.println("res:" + res); //1

        s = "acbc";
        k = 3;
        res = obj.palindromePartition(s, k);
        System.out.println("res:" + res); //1
    }

    /**
     * 解题思路：典型的动态规划问题，不过有点困难，具体思路可以参考 https://leetcode.com/problems/palindrome-partitioning-iii/discuss/498677/Step-by-Step-solution-DP-(Java)
     * dp[m][i]表示到s.substring[0,i]分割为m份所需要改变的最小字母数，这样子当分割为m+1的时候，substring(0,i+1)就能用到dp[m][i]的结果了，
     * 就可以进行推算了
     * @param s
     * @param k
     * @return
     */
    public int palindromePartition(String s, int k) {
        if (null == s || s.length() == 0 || k < 0 || k == s.length()) {
            return 0;
        }

        int len = s.length();
        int[][] changes = new int[len][len];
        //计算所有子串需要改变的数量
        for (int right = 1; right < len; ++right) {
            for (int left = 0; left < right; ++left) {
                changes[left][right] = getChanges(s, left, right);
            }
        }

        int[][] dp = new int[k + 1][len];
        //给dp赋初值，即只分割为1份
        for (int i = 0; i < len; ++i) {
            dp[1][i] = changes[0][i];
        }

        //接下来开始状态推算，从k=1开始进行推算
        for (int m = 2; m <= k; ++m) {
            //注意right的开始位置，和分割的份数有关，比如分割成2份，那么意味着right至少从index1开始
            for (int right = m - 1; right < len; ++right) {
                int min = Integer.MAX_VALUE;
                //推算到right分割成m份的最小代价
                for (int left = m - 1; left <= right; ++left) {
                    min = Math.min(min, dp[m - 1][left - 1] + changes[left][right]);
                }
                dp[m][right] = min;
            }
        }
        return dp[k][len - 1];
    }

    /**
     * 计算将[left,right]的范围变为回文所需要的最小的操作
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int getChanges(String s, int left, int right) {
        int changes = 0;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                ++changes;
            }
            ++left;
            --right;
        }
        return changes;
    }
}
