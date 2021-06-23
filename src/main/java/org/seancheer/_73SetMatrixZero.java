package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * 73. Set Matrix Zeroes
 */
public class _73SetMatrixZero extends LeetCodeParent {
    public static void main(String[] args) {
        _73SetMatrixZero obj = new _73SetMatrixZero();
        int[][] matrix = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        obj.setZeroes(matrix);
        printRes(matrix);
    }


    /**
     * 题目有一定的要求：
     * A straight forward solution using O(mn) space is probably a bad idea.
     * A simple improvement uses O(m + n) space, but still not the best solution.
     * Could you devise a constant space solution?
     * Example 2:
     * <p>
     * Input:
     * [
     * [0,1,2,0],
     * [3,4,5,2],
     * [1,3,1,5]
     * ]
     * Output:
     * [
     * [0,0,0,0],
     * [0,4,5,0],
     * [0,3,1,0]
     * ]
     * 需要在常量空间复杂度里面解决
     * 基本思路：单独记录第一列是否有0，然后其他位置如果有0，那么将其映射到第一列，最后根据第一列是否为0决定是否需要
     * 将对应位置置为0
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (null == matrix || matrix.length <= 0) {
            return;
        }

        boolean isZerp = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                isZerp = true;
            }
            // 将结果全部映射到第一列，但是第一行为什么就可以呢？
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        /**
         * 将0映射好之后，开始对对应的位置进行置0处理，这里必须要反着处理，因为如果不反着处理，
         * 那么会导致循环的第一遍，所有的matrix[0][i]都被更新，如果matrix[0][0]为0，那么会导致把整个matrix都更新为0
         * 倒着处理就不会有这样的情况，因为当i为0的时候，此时已经是最后一次循环了，并且因为这样的特殊处理，如果第一行中有0，将会被映射
         * 到matrix[0][0]的位置，这样子在最后一次循环过程中，也可以保证一行的数据是正确的。
         * */
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 1; --j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (isZerp) {
                matrix[i][0] = 0;
            }
        }
    }
}
