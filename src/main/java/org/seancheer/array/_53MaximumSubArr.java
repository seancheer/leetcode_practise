package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 * @author: seancheer
 * @date: 2021/9/2
 **/
public class _53MaximumSubArr extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        var obj = new _53MaximumSubArr();
        var res = obj.maxSubArray(nums);
        System.out.println("res:" + res); //6

        nums = new int[]{1};
        res = obj.maxSubArray(nums);
        System.out.println("res:" + res); //1

        nums = new int[]{5, 4, -1, 7, 8};
        res = obj.maxSubArray(nums);
        System.out.println("res:" + res); //23
    }

    /**
     * 解题思路：很简单的思路，一旦发现和小于等于0了，重新开始，不要紧张，
     * 面试这道题很简单的，怎么还磨叽了半天？什么奇怪的心态
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (null == nums) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum < 0) {
                sum = 0;
            }
            sum += num;
            res = Math.max(res, sum);
        }
        return res;
    }
}
