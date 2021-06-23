package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 * <p>
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 *
 * @author: seancheer
 * @date: 2021/2/7
 **/
public class _368MissingNumber extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        _368MissingNumber obj = new _368MissingNumber();
        int res = obj.missingNumber(nums);
        int res2 = obj.missingNumber2(nums);
        System.out.println("res:" + res + "    res2:" + res2);
    }

    /**
     * 这种方式是采用位运算得方式，不断的和数组中的值以及对应的编号进行异或运算，这样子，相同的值会异或为0，
     * 最终剩下的就是缺失的值。
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0;i < nums.length;++i){
            res ^= (i + 1);
            res ^= nums[i];
        }

        return res;
    }


    /**
     * 这种办法解题思路很简单，就是简单得将所有得值加起来，然后再把期望得值加起来，相减，便可以得到缺失得那个值。
     *
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }

        int len = nums.length;
        return (int)(0.5 * len * (len + 1)) - sum;
    }
}
