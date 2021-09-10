package org.seancheer.dynamic_programming;

import org.seancheer.utils.LeetCodeParent;

import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * <p>
 * <p>
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 *
 * @author: seancheer
 * @date: 2021/8/15
 **/
public class _120Triangle extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _120Triangle();
        String str = "[[2],[3,4],[6,5,7],[4,1,8,3]]";
        var numsList = convertStrTo2DemList(str);
        var res = obj.minimumTotal(numsList);
        var res2 = obj.minimumTotal2(numsList);
        System.out.println("res:" + res + "   res2:" + res2); //11

        str = "[-10]";
        numsList = convertStrTo2DemList(str);
        res = obj.minimumTotal(numsList);
        res2 = obj.minimumTotal2(numsList);
        System.out.println("res:" + res + "   res2:" + res2);  //-10
    }

    /**
     * 解题思路：动态规划问题
     * dp[i][j]表示到第i行第j列时的最小和，那么很简单的状态转移方程为：
     * dp[i][j] = Math.min(dp[i - 1][j] , dp[i - 1][j - 1]) + triangle[i][j]
     * 空间优化见解法2
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (null == triangle || triangle.isEmpty() || triangle.get(0) == null || triangle.get(0).isEmpty()) {
            return 0;
        }

        int row = triangle.size();
        int[][] dp = new int[row][triangle.get(row - 1).size()];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < row; ++i) {
            int col = triangle.get(i).size();
            for (int j = 0; j < col; ++j) {
                //在最后一个位置，由于是三角形，因此上一个少一列，注意处理这种情况
                if (j == col - 1) {
                    dp[i][j] = dp[Math.max(i - 1, 0)][Math.max(0, j - 1)] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[Math.max(i - 1, 0)][j], dp[Math.max(i - 1, 0)][Math.max(0, j - 1)]) + triangle.get(i).get(j);
                }

                //到了最后一行，需要统计结果
                if (i == row - 1) {
                    res = Math.min(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    /**
     * 解题思路：解法1的空间优化，因为题目要求
     * 很明显的是，我们当前的dp只依赖于上一行的i或者i-1，完全可以将空间复杂度压缩到1维
     * 注意在一维的情况下，状态转移会导致旧值被覆盖，所以记得处理这种情况
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (null == triangle || triangle.isEmpty() || triangle.get(0) == null || triangle.get(0).isEmpty()) {
            return 0;
        }

        int row = triangle.size();
        //只用最底下的那个作为dp的大小
        int[] dp = new int[triangle.get(row - 1).size()];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < row; ++i) {
            int col = triangle.get(i).size();
            int prev = dp[0];
            for (int j = 0; j < col; ++j) {
                //旧值下一次需要使用
                int tmp = dp[j];
                if (j == col - 1) {
                    dp[j] = prev + triangle.get(i).get(j);
                } else {
                    dp[j] = Math.min(dp[j], prev) + triangle.get(i).get(j);
                }

                if (i == row - 1) {
                    res = Math.min(res, dp[j]);
                }
                prev = tmp;
            }
        }
        return res;
    }
}
