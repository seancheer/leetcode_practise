package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 * <p>
 * You must do it in place.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * @author: seancheer
 * @date: 2021/9/1
 **/
public class _73SetMatrixZeroes extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _73SetMatrixZeroes();
        String arrStr = "[[1,1,1],[1,0,1],[1,1,1]]";
        int[][] nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[1,0,1],[0,0,0],[1,0,1]]

        arrStr = "[[0,1,2,0],[3,4,5,2],[1,3,1,5]]";
        nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[0,0,0,0],[0,4,5,0],[0,3,1,0]]

        arrStr = "[[0,1]]";
        nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[0,0]]

        arrStr = "[[-4,-2147483648,6,-7,0],[-8,6,-8,-6,0],[2147483647,2,-9,-6,-10]]";
        nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[0,0,0,0,0],[0,0,0,0,0],[2147483647,2,-9,-6,0]]

        arrStr = "[[0,1,2,0],[3,4,5,2],[1,3,1,5]]";
        nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[0,0,0,0],[0,4,5,0],[0,3,1,0]]

        arrStr = "[[1,2,3,4],[5,0,7,8],[0,10,11,12],[13,14,15,0]]";
        nums = convertStrTo2DemArr(arrStr);
        obj.setZeroes(nums);
        System.out.println("res:" + toStringRes(nums));//[[0,0,3,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
    }

    /**
     * 解题思路：题目要求使用O(1)的空间复杂度，不能使用其他的
     * 两次遍历达到效果
     * 1 第一次遍历，如果发现某个位置为0，假设坐标为(i,j)，我们就把nums[0][j]和nums[i][0]置为0
     * 2 第二次倒序遍历数组，如果发现边上有0，对应位置就设置为0，这样子就达到了使用O(1)的空间目的
     * 需要注意一个细节，就是遍历第0列的问题，因为(0,0)位置的0有可能是原生的，也有可能是内部传播过来的，
     * 所以我们置0的时候，采用倒序的方式，因为第i行使用完成之后，(i,0)列将再也用不到，如果第0列有0，我们直接把其置为0
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstColZero = false;
        //重点关照第0列而不是第0行是因为对于[[0,1]]这样的用例会导致for循环根本无法进入
        for (int i = 0; i < row; ++i) {
            //发现第0列有原生0，记录下来
            if (matrix[i][0] == 0){
                firstColZero = true;
            }
            //j从1开始是因为第一列需要特殊照顾，如果处理的话，(0,0)位置有可能会因为(i,0)被置为0，从而导致第0行被置为0，所以必须不处理
            //第0列
            for (int j = 1; j < col; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        //接下来倒序开始数组，为什么倒序呢？因为因为第i行使用完成之后，(i,0)列将再也用不到，如果第0列有0，我们直接把其置为0
        for (int i = row - 1; i >= 0; --i) {
            //注意不要处理第0列，第0列特殊处理，否则会有问题
            for (int j = col - 1; j >= 1; --j) {
                //发现边上有0，直接置为0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (firstColZero){
                matrix[i][0] = 0;
            }
        }
    }
}
