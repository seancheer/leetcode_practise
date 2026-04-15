package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 * <p>
 * Input: matrix = []
 * Output: 0
 * Example 3:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 4:
 * <p>
 * Input: matrix = [["1"]]
 * Output: 1
 * Example 5:
 * <p>
 * Input: matrix = [["0","0"]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == matrix.length
 * cols == matrix[i].length
 * 0 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 * 做本题之前请先参考{@link _84LargestRectangleInHistogram}的解法，两者有共通之处。
 *
 * @author: seancheer
 * @date: 2021/9/8
 **/
public class _85MaximalRectangle extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _85MaximalRectangle();
        String[][] nums = new String[][]{{"1", "0", "1", "0", "0"},
                {"1", "0", "1", "1", "1"},
                {"1", "1", "1", "1", "1"},
                {"1", "0", "0", "1", "0"}};
        var res = obj.maximalRectangle(convertMatrix(nums));
        var res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 6

        nums = new String[0][0];
        res = obj.maximalRectangle(convertMatrix(nums));
        res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 0

        nums = new String[][]{{"0"}};
        res = obj.maximalRectangle(convertMatrix(nums));
        res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 0

        nums = new String[][]{{"1"}};
        res = obj.maximalRectangle(convertMatrix(nums));
        res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 1

        nums = new String[][]{{"0", "0"}};
        res = obj.maximalRectangle(convertMatrix(nums));
        res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 0

        nums = new String[][]{{"1", "0"}};
        res = obj.maximalRectangle(convertMatrix(nums));
        res2 = obj.maximalRectangle2(convertMatrix(nums));
        System.out.println("res:" + res + "   res2:" + res2); // 1
    }

    /**
     * 解题思路：这道题的思路其实和{@link _84LargestRectangleInHistogram}是很像的，我们以每一行为起点，计算其连续高度，那么
     * 是不是就可以看作求解直方图的最大面积呢？
     * 该题的解法1采用这种思路，解法2采用动态规划的思路，非常难以理解，所以面试的时候建议记住该解法
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];
        int res = 0;
        for (int i = 0; i < row; ++i) {
            boolean zeroHeight = true;
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                    zeroHeight = false;
                } else {
                    //出现断层，立马置为0
                    heights[j] = 0;
                }
            }
            //统计好高度后，传给计算面积的函数，求解面积
            //短路判断，全部为0，说明本层没有计算的必要（直方图出现了断层）
            if (!zeroHeight) {
                res = Math.max(res, maximalArea(heights));
            }
        }
        return res;
    }

    /**
     * {@link _84LargestRectangleInHistogram}求解直方图的解法
     *
     * @param heights
     * @return
     */
    private int maximalArea(int[] heights) {
        int len = heights.length;
        //存储的是index
        int[] lessLeft = new int[len];
        int[] lessRight = new int[len];
        lessLeft[0] = -1;
        lessRight[len - 1] = len;

        //左边的关系
        for (int i = 1; i < len; ++i) {
            int pos = i - 1;
            //这是往起点靠近，所以必须>=0
            while (pos >= 0 && heights[pos] >= heights[i]) {
                pos = lessLeft[pos];
            }
            lessLeft[i] = pos;
        }

        //右边的关系
        for (int i = len - 2; i >= 0; --i) {
            int pos = i + 1;
            //这是往终点靠近，所以必须<len
            while (pos < len && heights[pos] >= heights[i]) {
                pos = lessRight[pos];
            }
            lessRight[i] = pos;
        }

        int res = heights[0];
        for (int i = 0; i < len; ++i) {
            res = Math.max(res, (lessRight[i] - lessLeft[i] - 1) * heights[i]);
        }
        return res;
    }


    /**
     * 解题思路：动态规划的思路，这道题的动态规划思路非常难以理解，建议参考
     * https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
     * 的第一个评论，讲解的更为清楚，这里只写下状态转移方程，定义三个数组left, right, height，分别的意思如下：
     * 注意，index k均为列的index
     * left：表示任意index k在[i,j]范围中height[k] >= height[i]的最左边index
     * right：表示任意index k在[j,i]范围中height[k] >= height[i]的最右边index
     * height：表示任意index k的高度
     * <p>
     * 那么这就很明显了，每行的面积为area = (right[i] - left[i] + 1) * height[i]
     * 问题的难点在于怎么去更新left和right（height的更新比较简单，这里就不赘述了），left和right的状态转移方程如下：
     * left[i] = Math.max(left[i - 1], curLeft) if matrix[row][i] == '1' else left[i] = 0
     * right[i] = Math.min(right[i - 1], curRight) if matrix[row][i] = '1' else right[i] = col - 1
     * <p>
     * 其中curLeft为本row开始连续为'1'的起始位置，curRight也是同理；
     * left表示左边界，因此，为了和上一行结合起来，所以用max
     * right表示右边界，因此，为了和上一行结合起来，所以用min
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        //所有的数组都是观察列方向，因此长度为col
        int[] height = new int[col];
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col - 1);
        int res = 0;
        for (int i = 0; i < row; ++i) {
            //首先更新height
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    //出现了高度断层
                    height[j] = 0;
                }
            }

            //更新left
            int curLeft = 0;
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    //这里这么赋值的目的其实和height一样，重置旧值，避免下一行出现错误的判断
                    left[j] = 0;
                    //curLeft始终表示第一次出现'1'的index
                    curLeft = j + 1;
                }
            }

            //更新right
            int curRight = col - 1;
            for (int j = col - 1; j >= 0; --j) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    //这里这么赋值的目的其实和height一样，重置旧值，避免下一行出现错误的判断
                    right[j] = col - 1;
                    curRight = j - 1;
                }
            }

            //接下来开始统计面积，很明显，有几个for循环是可以进行合并的，这里就不做代码优化了，解法思路更重要
            //和解法1一样，以每一个位置为中心计算最大的面积。
            for (int j = 0; j < col; ++j) {
                res = Math.max(res, height[j] * (right[j] - left[j] + 1));
            }
        }
        return res;
    }


    /**
     * 本题专用的工具函数，负责将二维String数组转换为char二维数组
     *
     * @param m
     * @return
     */
    private static char[][] convertMatrix(String[][] m) {
        if (null == m || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new char[0][0];
        }

        int row = m.length;
        int col = m[0].length;
        char[][] matrix = new char[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                matrix[i][j] = m[i][j].charAt(0);
            }
        }
        return matrix;
    }
}
