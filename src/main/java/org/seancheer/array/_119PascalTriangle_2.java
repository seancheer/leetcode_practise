package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 * <p>
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 * <p>
 * Input: rowIndex = 1
 * Output: [1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= rowIndex <= 33
 * <p>
 * <p>
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 *
 * @author: seancheer
 * @date: 2021/9/11
 **/
public class _119PascalTriangle_2 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _119PascalTriangle_2();
        int rowIndex = 3;
        var res = obj.getRow(rowIndex);
        System.out.println("res:" + res); //  [1,3,3,1]

        rowIndex = 0;
        res = obj.getRow(rowIndex);
        System.out.println("res:" + res); //  [1]

        rowIndex = 1;
        res = obj.getRow(rowIndex);
        System.out.println("res:" + res); //  [1,1]
    }


    /**
     * 解题思路：easy级别的题目，题目中额外要求 O(rowIndex)的空间复杂度，其实也很简单，因为每一行只依赖于前一行的结果，
     * 所以使用一个数组存储当前状态即可
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }

        Integer[] row = new Integer[rowIndex + 1];
        Arrays.fill(row, 1);
        for (int i = 1; i <= rowIndex; ++i) {
            int lastVal = row[0];
            for (int j = 1; j < i; ++j) {
                //保存旧值，因为下一个循环会用到
                Integer tmp = row[j];
                row[j] = row[j] + lastVal;
                lastVal = tmp;
            }
        }
        return Arrays.asList(row);
    }
}
