package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * <p>
 * It is guaranteed that the answer will fit in a 32-bit integer.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * @author: seancheer
 * @date: 2021/9/2
 **/
public class _152MaximumProductSubArr extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        var obj = new _152MaximumProductSubArr();
        var res = obj.maxProduct(nums);
        var res2 = obj.maxProduct2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //6

        nums = new int[]{-2, 0, -1};
        res = obj.maxProduct(nums);
        res2 = obj.maxProduct2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //0

        nums = new int[]{-2};
        res = obj.maxProduct(nums);
        res2 = obj.maxProduct2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //-2

        nums = new int[]{-2, -3, -1};
        res = obj.maxProduct(nums);
        res2 = obj.maxProduct2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //6

        nums = new int[]{-3, 0, 1, -2};
        res = obj.maxProduct(nums);
        res2 = obj.maxProduct2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //1
    }

    /**
     * 解题思路：如何在O(n)的情况解决这个问题呢？
     * 思路1的思想是使用一个min和max，分别记录当前最小的一个乘积min和最大的一个乘积max，让他们分别乘以当前的nums[i]，因为
     * nums[i]如果为负数的话，min和max可能会交换位置，所以我们需要重新计算min和max，且看代码（因为0的存在，所以我们还需要将乘积结果
     * 和当前的nums[i]做对比，那个大/小，选哪个）
     * 解法2的思路会更加直观些，但是相应的也需要遍历两遍
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int res = nums[0];
        int min = res, max = res;
        for (int i = 1; i < len; ++i) {
            int productMax = Math.max(min * nums[i], max * nums[i]);
            int productMin = Math.min(min * nums[i], max * nums[i]);
            //这些判断还能处理nums[i]为0的情况，比如nums[i - 1]为0，那么我们直接从nums[i]作为起点重新计算
            //之所以这么判断，是因为如果出现负值且max是正值，那么乘积会变得更小，还不如不乘
            max = Math.max(nums[i], productMax);
            //这里一样的道理，如果min是负值，nums[i]也是负值，此时乘还不如不乘，之所以要保留min，是因为min到最后如果又遇到一个负值，能够瞬间变成最大的正值
            min = Math.min(nums[i], productMin);
            //这里注意一定要每次循环都判断，否则会导致结果不对，比如[-2,-3,-1]，因为最后的max不一定会是最大值
            res = Math.max(max, res);
        }
        return res;
    }


    /**
     * 解题思路：我们思考这样一个问题，对于数组来讲，其乘积最大值取决于数组中负数的个数，如果负数为偶数，那么最大结果
     * 为挨个的乘积，如果为奇数，那么最大乘积在[start, 最后一个负数)和(第一个负数，end]中间中的一个。
     * 当然，会有一个问题，可能会存在0，所以我们一旦遇到0，乘积直接置为1重新开始上述的逻辑，为了达到上面的逻辑，我们正向计算一次乘积，
     * 然后反向再计算一次乘积，中间记录出现的最大值，这样子就达到了目的
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int product = 1;
        int res = nums[0];
        for (int i = 0; i < nums.length; ++i) {
            product *= nums[i];
            res = Math.max(product, res);
            if (nums[i] == 0) {
                product = 1;
            }
        }

        product = 1;
        //再反向计算一遍
        for (int i = nums.length - 1; i >= 0; --i) {
            product *= nums[i];
            res = Math.max(product, res);
            if (nums[i] == 0) {
                product = 1;
            }
        }
        return res;
    }
}
