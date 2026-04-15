package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 * <p>
 * Input: matrix = [[1]]
 * Output: 1
 * <p>
 * 不能对角线移动，且不能移动到边界外面，只能上下左右移动
 *
 * @author: seancheer
 * @date: 2021/7/28
 **/
public class _329LongestIncreasingPathInaMatrix extends LeetCodeParent {
    public static void main(String[] args) {
        _329LongestIncreasingPathInaMatrix obj = new _329LongestIncreasingPathInaMatrix();
        String nums = " [[9,9,4],[6,6,8],[2,1,1]]";
        var res = obj.longestIncreasingPath(convertStrTo2DemArr(nums));
        System.out.println("res:" + res); //4

        nums = "[[3,4,5],[3,2,6],[2,2,1]]";
        res = obj.longestIncreasingPath(convertStrTo2DemArr(nums));
        System.out.println("res:" + res); //4

        nums = "[[1]]";
        res = obj.longestIncreasingPath(convertStrTo2DemArr(nums));
        System.out.println("res:" + res); //1

    }

    /**
     * 解题思路：带记忆化搜索的DFS，记忆化某个点作为起点的最长路径，由于路径是严格递增的，因此不可能出现回路，可以放心的进行
     * DFS遍历
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        Integer[][] memo = new Integer[row][col];
        int res = 1;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (null == memo[i][j]) {
                    helper(i, j, row, col, matrix, memo);
                }
                res = Math.max(res, memo[i][j]);
            }
        }
        return res;
    }

    /**
     * 带有记忆化搜索的路径
     *
     * @param i
     * @param j
     * @param row
     * @param col
     * @param memo
     */
    private int helper(int i, int j, int row, int col, int[][] matrix, Integer[][] memo) {
        if (null != memo[i][j]) {
            return memo[i][j];
        }

        int cur = matrix[i][j];
        int left = 0, right = 0, top = 0, down = 0;
        if (j - 1 >= 0 && matrix[i][j - 1] > cur) {
            left = helper(i, j - 1, row, col, matrix, memo);
        }

        if (j + 1 < col && matrix[i][j + 1] > cur) {
            right = helper(i, j + 1, row, col, matrix, memo);
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > cur) {
            top = helper(i - 1, j, row, col, matrix, memo);
        }

        if (i + 1 < row && matrix[i + 1][j] > cur) {
            down = helper(i + 1, j, row, col, matrix, memo);
        }
        int res = Math.max(left, Math.max(right, Math.max(top, down))) + 1;
        memo[i][j] = res;
        return res;
    }



}
