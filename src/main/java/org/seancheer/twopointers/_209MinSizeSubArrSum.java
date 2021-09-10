package org.seancheer.twopointers;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * nums不可能小于1
 *
 * @author: seancheer
 * @date: 2021/7/27
 **/
public class _209MinSizeSubArrSum {
    public static void main(String[] args) {
        _209MinSizeSubArrSum obj = new _209MinSizeSubArrSum();
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int target = 7;
        var res = obj.minSubArrayLen(target, nums);
        System.out.println("res:" + res); //2


        nums = new int[]{1, 4, 4};
        target = 4;
        res = obj.minSubArrayLen(target, nums);
        System.out.println("res:" + res); //1


        nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        target = 11;
        res = obj.minSubArrayLen(target, nums);
        System.out.println("res:" + res); //0
    }

    /**
     * 解题思路：使用双指针，一个在后面，一个在前面，计算和，和如果满足要求了，往前推i，直到最小的满足题目要求的数量
     * nums的值大于0
     * 时间复杂度为O(n)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int len = nums.length;
        int sum = 0;
        while (j < len) {
            while (j < len && sum < target) {
                sum += nums[j];
                ++j;
            }

            if (sum >= target) {
                res = Math.min(res, j - i);
            }

            boolean valid = (sum >= target);
            //接下来尝试往前推i，尝试最小化i和j的长度
            while (i < len && sum >= target) {
                sum -= nums[i];
                ++i;
            }

            if (valid) {
                res = Math.min(res, j - i + 1);
            }

            //min
            if (res == 1){
                return 1;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
