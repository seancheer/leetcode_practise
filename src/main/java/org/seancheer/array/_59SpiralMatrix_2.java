package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 *
 * @author: seancheer
 * @date: 2021/8/29
 **/
public class _59SpiralMatrix_2 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _59SpiralMatrix_2();
        int n = 3;
        var res = obj.generateMatrix(n);
        System.out.println("res:" + toStringRes(res));

        n = 1;
        res = obj.generateMatrix(n);
        System.out.println("res:" + toStringRes(res));

        n = 10;
        res = obj.generateMatrix(n);
        System.out.println("res:" + toStringRes(res));
    }

    /**
     * 解题思路：和{@link _54SpiralMatrix}类似，再遍历的过程中，依次填写好需要放的数据即可
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }

        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        int[][] res = new int[n][n];
        int idx = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //从左到右
            for (int i = colBegin; i <= colEnd; ++i) {
                res[rowBegin][i] = idx++;
            }
            ++rowBegin;

            //从上到下
            for (int i = rowBegin; i <= rowEnd; ++i) {
                res[i][colEnd] = idx++;
            }
            --colEnd;

            //从右到左
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; --i) {
                    res[rowEnd][i] = idx++;
                }
            }
            --rowEnd;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; --i) {
                    res[i][colBegin] = idx++;
                }
            }
            ++colBegin;
        }
        return res;
    }
}
