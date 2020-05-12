package org;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.List;

/**
 * 152. Maximum Product Subarray
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class _152MaxProductSubarr extends LeetCodeParent {
    public static void main(String[] args) {
        _152MaxProductSubarr obj = new _152MaxProductSubarr();
        int[] nums = new int[]{2,3,-2,4,3};
        var res = obj.maxProduct(nums);
        System.out.println("res : " + res);

        nums = new int[]{-2,0,-1};
        res = obj.maxProduct(nums);
        System.out.println("res : " + res);

        nums = new int[]{-2,-3,-1};
        res = obj.maxProduct(nums);
        System.out.println("res : " + res);

        nums = new int[]{0, 2};
        res = obj.maxProduct(nums);
        System.out.println("res : " + res);
    }

    /**
     * 求乘积最大的子串，使用两个变量，min和max分别存储当前位置最大的值和最小的值，
     * 如果当前为负值，那么交换这两个值，否则的话，对这两个值进行更新
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length <= 0){
            return 0;
        }

        int n = nums.length;
        int res= nums[0];
        int min = nums[0];
        int max = nums[0];
        for(int i = 1;i < n;++i){
            //因为只有负值会改变乘积的大小，所以这里对负值进行特殊处理.
            if (nums[i] < 0){
                int tmp = min;
                min = max;
                max = tmp;
            }

            max = Math.max(nums[i], nums[i] * max);
            min = Math.min(nums[i], nums[i] * min);

            res = Math.max(res, max);
        }

        return res;
    }
}
