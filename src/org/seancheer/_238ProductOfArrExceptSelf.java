package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class _238ProductOfArrExceptSelf extends LeetCodeParent {
    public static void main(String[] args) {
        _238ProductOfArrExceptSelf obj = new _238ProductOfArrExceptSelf();
        int[] nums = new int[]{
                1, 2, 3, 4
        };
        var res = obj.productExceptSelf(nums);
        System.out.println("res:" + Arrays.toString(res));

        nums = new int[]{
                3, 7
        };
        res = obj.productExceptSelf(nums);
        System.out.println("res:" + Arrays.toString(res));
    }

    /**
     * 打印出乘积，但是不包含自身
     * 题目要求不能用除法，并且时间复杂度为O(n)，且要求常数的空间复杂度（不计算存储返回结果的数组）。
     * 基本思路：把刚开始的乘积错位赋值给前一个数组，然后从末尾开始，用一个常量记录从右边开始的乘积，
     * 在根据左边记录的值挨个计算最终的结果并赋值到正确的位置上。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return new int[]{};
        }

        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            res[i] = res[i - 1] * nums[i];
        }

        //开始把结果赋回去
        res[len - 1] = res[len - 2];
        int rightProduct = nums[len - 1];
        for (int i = len - 2; i > 0; --i) {
            res[i] = res[i - 1] * rightProduct;
            rightProduct *= nums[i];
        }

        res[0] = rightProduct;
        return res;
    }
}
