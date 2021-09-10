package org.seancheer.dynamic_programming;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * Example 3:
 * <p>
 * Input: m = 7, n = 3
 * Output: 28
 * Example 4:
 * <p>
 * Input: m = 3, n = 3
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 100
 * It's guaranteed that the answer will be less than or equal to 2 * 109.
 *
 * @author: seancheer
 * @date: 2021/8/12
 **/
public class _62UniquePaths {

    public static void main(String[] args) {
        _62UniquePaths obj = new _62UniquePaths();
        int m = 3;
        int n = 7;
        int res = obj.uniquePaths(m, n);
        System.out.println("res:" + res);  //28

        m = 3;
        n = 2;
        res = obj.uniquePaths(m, n);
        System.out.println("res:" + res);  //3

        m = 7;
        n = 3;
        res = obj.uniquePaths(m, n);
        System.out.println("res:" + res);  //28

        m = 3;
        n = 3;
        res = obj.uniquePaths(m, n);
        System.out.println("res:" + res);  //6
    }

    /**
     * 解题思路：动态规划问题，使用dp[i][j]表示跳到坐标i,j的时候的方法数，那么状态转移方程为：
     * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * 默认值x=0或者y=0的时候均为1
     * 其实可以进行空间复杂度的优化，这里就不优化了，很简单
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; ++i) {
            dp[0][i] = 1;
        }

        //开始进行状态的推算
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != 0 && j != 0) {
                    dp[i][j] = dp[Math.max(i - 1, 0)][j] + dp[i][Math.max(j - 1, 0)];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
