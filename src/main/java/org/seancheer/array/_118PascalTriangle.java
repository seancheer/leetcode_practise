package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 * @author: seancheer
 * @date: 2021/9/11
 **/
public class _118PascalTriangle extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _118PascalTriangle();
        int numRows = 5;
        var res = obj.generate(numRows);
        System.out.println("res:" + res); //  [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

        numRows = 1;
        res = obj.generate(numRows);
        System.out.println("res:" + res); //  [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    }

    /**
     * 解题思路：easy级别的题目，思路很直观
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0){
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));

        for(int i = 2;i <= numRows;++i){
            List<Integer> item = new ArrayList<>();
            item.add(1);
            List<Integer> lastRow = res.get(res.size() - 1);
            for(int j = 1;j < lastRow.size();++j){
                item.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            item.add(1);
            res.add(item);
        }
        return res;
    }
}
