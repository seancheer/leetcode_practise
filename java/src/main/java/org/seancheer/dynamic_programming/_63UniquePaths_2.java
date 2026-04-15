package org.seancheer.dynamic_programming;

import org.seancheer.utils.LeetCodeParent;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 * <p>
 * <p>
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 * 注意，这道题的障碍物可能在起点[0,0]也可能在重点[m - 1][n - 1]
 * @author: seancheer
 * @date: 2021/8/12
 **/
public class _63UniquePaths_2 extends LeetCodeParent {
    public static void main(String[] args) {
        _63UniquePaths_2 obj = new _63UniquePaths_2();
        String grid = "[[0,0,0],[0,1,0],[0,0,0]]";
        int[][] obstacleGrid = convertStrTo2DemArr(grid);
        int res = obj.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("res:" + res);  //2

        grid = "[[0,1],[0,0]]";
        obstacleGrid = convertStrTo2DemArr(grid);
        res = obj.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("res:" + res);  //1
    }

    /**
     * 解题思路：其实就是{@link _62UniquePaths}的小小的升级版，继续使用dp[i][j]表示到达[i,j]的path数量
     * 状态转移方程依旧还是：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]，不过需要注意的是，在推导dp[i][j]
     * 的时候需要判断当前位置上是不是有障碍物，如果有的话，那么dp[i][j] = 0
     * 空间复杂度可以进行压缩，这里就不写了，自己推算即可
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (null == obstacleGrid || obstacleGrid.length == 0 || obstacleGrid[0] == null) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //判断障碍物是不是在起点或者在终点，如果在的话，直接返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1){
            return 0;
        }


        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        //初始状态，注意判断是否有障碍物
        for (int i = 1; i < m; ++i) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < n; ++i) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }

        //接下来开始状态推算
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
