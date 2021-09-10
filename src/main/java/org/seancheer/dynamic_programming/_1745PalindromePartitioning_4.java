package org.seancheer.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​
 * <p>
 * A string is said to be palindrome if it the same string when reversed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcbdd"
 * Output: true
 * Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
 * Example 2:
 * <p>
 * Input: s = "bcbddxy"
 * Output: false
 * Explanation: s cannot be split into 3 palindromes.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= s.length <= 2000
 * s​​​​​​ consists only of lowercase English letters.
 *
 * @author: seancheer
 * @date: 2021/8/3
 **/
public class _1745PalindromePartitioning_4 {

    public static void main(String[] args) {
        _1745PalindromePartitioning_4 obj = new _1745PalindromePartitioning_4();
        String s = "abcbdd";
        var res = obj.checkPartitioning(s);
        System.out.println("res:" + res); //true

        s = "bcbddxy";
        res = obj.checkPartitioning(s);
        System.out.println("res:" + res); //false

        s = "acab";
        res = obj.checkPartitioning(s);
        System.out.println("res:" + res); //false

        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcd";
        res = obj.checkPartitioning(s);
        System.out.println("res:" + res); //false
    }


    /**
     * 解题思路：动态规划
     * 使用dp[i][j]表示区间[i,j]是否是一个回文，这样子dp[i][j]=true if only if dp[i + 1][j - 1] && s.charAt[i] == s.charAt[j]
     * 具体看代码，思路很简单，注意初始化dp的做法，为了能够让dp用到上一个状态，所以采用了类似于插入排序的迭代方式，传统的
     * for(int i = 0;i < len;++i)
     *    for(int j = i;j < len;++j)
     *
     * 无法利用到dp的上一个状态，所以不采用这种方式来推算dp
     *
     * @param s
     * @return
     */
    public boolean checkPartitioning(String s) {
        if (null == s || s.length() < 3) {
            return false;
        }

        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; ++right) {
            dp[right][right] = true;
            for (int left = right - 1; left >= 0; --left) {
                if (s.charAt(right) == s.charAt(left)) {
                    //left + 1 > right - 1说明是第一次内层循环，直接为true，否则的话，需要判断前一次的dp是否为true
                    dp[left][right] = ((left + 1 > right - 1) || dp[left + 1][right - 1]);
                }
            }
        }

        //接下来开始寻找满足3个子串的情况
        for(int i = 0;i < len;++i){
            for(int j = i;j < len;++j){
                //表示有满足条件的三个子串，注意边界情况
                if (i > 0 && dp[0][i - 1] && j < len - 1 && dp[j + 1][len - 1] && dp[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
