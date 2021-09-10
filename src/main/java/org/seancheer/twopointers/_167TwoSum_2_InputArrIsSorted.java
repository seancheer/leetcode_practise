package org.seancheer.twopointers;

import java.util.Arrays;

/**
 *Given an array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number.
 *
 * Return the indices of the two numbers (1-indexed) as an integer array answer of size 2, where 1 <= answer[0] < answer[1] <= numbers.length.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _167TwoSum_2_InputArrIsSorted {
    public static void main(String[] args) {
        var obj = new _167TwoSum_2_InputArrIsSorted();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        var res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [1,2]

        nums = new int[]{2, 3,4};
        target = 6;
        res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [1,3]

        nums = new int[]{-1, 0};
        target = -1;
        res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [1,2]
    }

    /**
     * 解题思路：注意，题目要求返回的结果的index是从1开始的
     *
     * 由于数组是排好序的，使用两个指针，一个从头开始begin，一个从末尾end开始，如果发现当前和小于target，那么移动begin，如果发现大于target那么移动end
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        if (null == numbers || numbers.length <= 1){
            return new int[0];
        }

        int start = 0, end = numbers.length -1;
        while(start < end){
            while(start < end && numbers[start] + numbers[end] < target){
                ++start;
            }
            if (start != end && numbers[start] + numbers[end] == target){
                return new int[]{start + 1, end + 1};
            }

            while(start < end && numbers[start] + numbers[end] > target){
                --end;
            }
            if (start != end && numbers[start] + numbers[end] == target){
                return new int[]{start + 1, end + 1};
            }
        }
        return new int[0];
    }
}
