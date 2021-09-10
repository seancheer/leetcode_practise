package org.seancheer.dynamic_programming;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _72EditDistance {
    public static void main(String[] args) {
        _72EditDistance obj = new _72EditDistance();
        String word1 = "horse";
        String word2 = "ros";
        int res = obj.minDistance(word1, word2);
        System.out.println("res:" + res);  //3

        word1 = "intention";
        word2 = "execution";
        res = obj.minDistance(word1, word2);
        System.out.println("res:" + res);  //5
    }

    /**
     * 解题思路：典型的动态规划问题，定义dp[i][j]表示将word1的[0,i]变为word2[0,j]所需要的最小的步骤，状态转移方程为：
     * dp[i][j] = dp[i - 1][j - 1] if word1[i] == word2[j]表示相同，直接从前一个继承
     * dp[i][j] = dp[i - 1][j - 1] + 1 if word1[i] != word2[j]不同，直接采用replace的方式
     * dp[i][j] = dp[i - 1][j] + 1，表示word1[i]是多余的，直接删除
     * dp[i][j] = dp[i][j - 1] + 1，表示还缺少一个word2[j]，采用insert的方式；
     * 上面几种情况选最小的即可,注意初始值的设置
     * 这道题可以优化空间复杂度，因为dp[i][j]只依赖于dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]，所以可以优化空间复杂度，
     * 这里就不写了，画坐标系可以使用一些常量来优化
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (null == word1 || null == word2) {
            return 0;
        }

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        //word2为空，直接删除的方式
        for (int i = 0; i < m; ++i) {
            dp[i + 1][0] = dp[i][0] + 1;
        }
        //word1为空，采用直接insert的方式
        for (int i = 0; i < n; ++i) {
            dp[0][i + 1] = dp[0][i] + 1;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1] + 1, dp[i + 1][j] + 1));
                }else{
                    dp[i + 1][j + 1] = Math.min(dp[i][j] + 1, Math.min(dp[i][j + 1] + 1, dp[i + 1][j] + 1));
                }
            }
        }
        return dp[m][n];
    }
}