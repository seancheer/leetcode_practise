package org.seancheer.dynamic_programming;

import org.seancheer.greedy._330PatchingArray;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input:
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Example 3:
 * <p>
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 *
 * @author: seancheer
 * @date: 2021/8/2
 **/
public class _97InterleavingString {
    public static void main(String[] args) {
        _97InterleavingString obj = new _97InterleavingString();
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        var res = obj.isInterleave(s1, s2, s3);
        var res2 = obj.isInterleave2(s1, s2, s3);
        System.out.println("res:" + res + "   res2:" + res2); //true

        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";
        res = obj.isInterleave(s1, s2, s3);
        res2 = obj.isInterleave2(s1, s2, s3);
        System.out.println("res:" + res + "   res2:" + res2); //false

        s1 = "";
        s2 = "";
        s3 = "";
        res = obj.isInterleave(s1, s2, s3);
        res2 = obj.isInterleave2(s1, s2, s3);
        System.out.println("res:" + res + "   res2:" + res2); //true

        s1 = "ab";
        s2 = "bc";
        s3 = "bbac";
        res = obj.isInterleave(s1, s2, s3);
        res2 = obj.isInterleave2(s1, s2, s3);
        System.out.println("res:" + res + "   res2:" + res2); //false

    }


    /**
     * 解题思路：典型的动态规划问题
     * 使用dp[i][j]表示从s1中选取[0,i - 1]时和s2中选取[0,j - 1]时能否构成s3的状态，那么dp[i][j]就可以从dp[i - 1][j]或者
     * dp[i][j - 1]中继承状态，注意初始值
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (null == s1 || null == s2 || null == s3) {
            return false;
        }

        int s1Len = s1.length(), s2Len = s2.length();
        if (s1Len + s2Len != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;
        //初始值
        for (int i = 0; i < s1Len; ++i) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i + 1][0] = true;
                continue;
            }
            break;
        }

        for (int i = 0; i < s2Len; ++i) {
            if (s2.charAt(i) == s3.charAt(i)) {
                dp[0][i + 1] = true;
                continue;
            }
            break;
        }

        for (int i = 1; i <= s1Len; ++i) {
            for (int j = 1; j <= s2Len; ++j) {
                char s1Char = s1.charAt(i - 1);
                char s2Char = s2.charAt(j - 1);
                char s3Char = s3.charAt(i + j - 1);
                if (s1Char == s3Char && dp[i - 1][j]) {
                    dp[i][j] = true;
                }

                if (s2Char == s3Char && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[s1Len][s2Len];
    }


    /**
     * 解题思路：典型的动态规划问题
     * 解法1的空间优化，思路很简单，画一个坐标系，观察每一次循环dp[i][j]依赖的东西，然后根据这个逻辑来简化空间，这里需要注意的是
     * 对于j = 0 i = [0,s1.Length)的处理，详情见代码！！！！标记处
     * 有问题，思考下问题出在哪里
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (null == s1 || null == s2 || null == s3) {
            return false;
        }

        int s1Len = s1.length(), s2Len = s2.length();
        if (s1Len + s2Len != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2Len + 1];
        dp[0] = true;
        for (int i = 0; i < s2Len; ++i) {
            if (s2.charAt(i) == s3.charAt(i)) {
                dp[i + 1] = true;
                continue;
            }
            break;
        }

        for (int i = 1; i <= s1Len; ++i) {
            //！！！！
            dp[0] = (dp[0] && (s1.charAt(i - 1) == s3.charAt(i - 1)));

            for (int j = 1; j <= s2Len; ++j) {
                char s1Char = s1.charAt(i - 1);
                char s2Char = s2.charAt(j - 1);
                char s3Char = s3.charAt(i + j - 1);
                //注意这里，不能像解法1那样用if来判断，因为现在状态压缩为一维了，那么就意味着我们必须即使更新状态了，因为按照
                //坐标系的关系，其依赖的是原始二维里面的状态，这里这么做的理由是dp[j]旧值是true，但是本次为false，就必须把
                //dp[j]更新为false，因为下一次依赖的是本次的false，而不是上次的true，二维就不存在该情况，因为各个状态都是一对一表示的
                dp[j] = ((s1Char == s3Char && dp[j]) || (s2Char == s3Char && dp[j - 1]));
            }
        }
        return dp[s2Len];
    }
}
