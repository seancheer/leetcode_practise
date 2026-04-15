package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * Example 3:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: ["-1"]
 * Example 5:
 * <p>
 * Input: nums = [0]
 * Output: ["0"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 * 题目不太好理解，其实意思就是让连续的数在一个范围而已
 *
 * @author: seancheer
 * @date: 2021/9/7
 **/
public class _228SummaryRanges extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _228SummaryRanges();
        int[] nums = new int[]{0, 1, 2, 4, 5, 7};
        var res = obj.summaryRanges(nums);
        System.out.println("res:" + res); // ["0->2","4->5","7"]

        nums = new int[]{0, 2, 3, 4, 6, 8, 9};
        res = obj.summaryRanges(nums);
        System.out.println("res:" + res); // ["0","2->4","6","8->9"]

        nums = new int[]{-1};
        res = obj.summaryRanges(nums);
        System.out.println("res:" + res); // ["-1"]
    }

    /**
     * 解题思路：数组不重复且排好了序。
     * 类似于贪心算法，我们每次尽力去查找可能连续的数，作为新的range
     * easy级别的题目，很好实现，就是理解题意有点困难，题目要求连续的数字在一个范围，不连续的单独列出来
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        if (null == nums || nums.length <= 0) {
            return new ArrayList<>();
        }

        int len = nums.length;
        int i = 0;
        List<String> res = new ArrayList<>();
        while (i < len) {
            StringBuilder sb = new StringBuilder();
            sb.append(nums[i]);
            int start = i;
            ++i;
            //查找连续数组
            while (i < len && nums[i] - nums[i - 1] == 1) {
                ++i;
            }
            --i;

            //表示出现了不连续的数组
            if (i > start) {
                sb.append("->").append(nums[i]);
            }
            res.add(sb.toString());
            ++i;
        }
        return res;
    }
}
