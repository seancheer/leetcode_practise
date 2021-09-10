package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * @author: seancheer
 * @date: 2021/8/29
 **/
public class _54SpiralMatrix extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _54SpiralMatrix();
        String arrStr = "[[1,2,3],[4,5,6],[7,8,9]]";
        int[][] nums = convertStrTo2DemArr(arrStr);
        var sz = obj.spiralOrder(nums);
        System.out.println("res:" + sz.toString());//[1,2,3,6,9,8,7,4,5]

        arrStr = "[[1,2,3,4],[5,6,7,8],[9,10,11,12]]";
        nums = convertStrTo2DemArr(arrStr);
        sz = obj.spiralOrder(nums);
        System.out.println("res:" + sz.toString());//[1,2,3,4,8,12,11,10,9,5,6,7]

        arrStr = "[[1,2,3,4]]";
        nums = convertStrTo2DemArr(arrStr);
        sz = obj.spiralOrder(nums);
        System.out.println("res:" + sz.toString());//[1,2,3,4]

        arrStr = "[[1],[2],[3],[4]]";
        nums = convertStrTo2DemArr(arrStr);
        sz = obj.spiralOrder(nums);
        System.out.println("res:" + sz.toString());//[1,2,3,4]
    }

    /**
     * 解题思路：顺时针打印二维数组，难点在于统一只有一行或者一列的时候如何处理，如何统一逻辑呢？
     * 我们使用四个变量rowBegin, rowEnd, colBegin, colEnd，然后分为四个方向来打印，每打印一个方向，就把对应方向的边界往回
     * 缩一行，直到任意一个边界相遇为止。这种方法不需要处理只有一行和一列这种特殊情况
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0] == null) {
            return new ArrayList<>();
        }

        int rowBegin = 0, colBegin = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //从左往右打
            for (int i = colBegin; i <= colEnd; ++i) {
                res.add(matrix[rowBegin][i]);
            }
            //打印完成之后，row往下摞动一层
            ++rowBegin;

            //从上往下打印
            for (int i = rowBegin; i <= rowEnd; ++i) {
                res.add(matrix[i][colEnd]);
            }
            //最后一列打印完了，缩colEnd
            --colEnd;

            //从右往左打，因为rowBegin变化了，所以这里要判断是否合法，col的合法判断在for循环里面
            if (rowBegin <= rowEnd){
                for(int i = colEnd;i >= colBegin;--i){
                    res.add(matrix[rowEnd][i]);
                }
            }
            //最后一行打印完了，缩rowEnd
            --rowEnd;

            //从下往上打印，因为colEnd发生了变化，所以需要判断是否合法，row的合法性判断在for循环里面
            if (colBegin <= colEnd){
                for(int i = rowEnd;i >= rowBegin;--i){
                    res.add(matrix[i][colBegin]);
                }
            }
            ++colBegin;
        }
        return res;
    }
}
