package org.seancheer.dynamic_programming;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 * <p>
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _64MinimumPathSum extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _64MinimumPathSum();
        String gridStr = "[[1,3,1],[1,5,1],[4,2,1]]";
        int[][] grid = convertStrTo2DemArr(gridStr);
        int res = obj.minPathSum(grid);
        System.out.println("res:" + res);  //7

        gridStr = "[[1,2,3],[4,5,6]]";
        grid = convertStrTo2DemArr(gridStr);
        res = obj.minPathSum(grid);
        System.out.println("res:" + res);  //12
    }

    /**
     * 解题思路：动态规划问题
     * 使用dp[i][j]表示到[i,j]时候的最小值，注意初始值设定即可，没什么难度
     * 状态转移方程为：
     * dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
