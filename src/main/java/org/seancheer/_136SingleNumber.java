package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * @author: seancheer
 * @date: 2021/2/7
 **/
public class _136SingleNumber extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        _136SingleNumber obj = new _136SingleNumber();
        int res = obj.singleNumber(nums);
        System.out.println("res:" + res);

        nums = new int[]{2,2,1};
        obj = new _136SingleNumber();
        res = obj.singleNumber(nums);
        System.out.println("res:" + res);
    }


    /**
     * 解题思路：按照顺序进行异或操作，这样子最后剩下的就是那个出现一次的数字，因为相同的数字都异或为0了。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int item : nums){
            res ^= item;
        }
        return res;
    }
}
