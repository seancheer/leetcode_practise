package org.seancheer;

import java.util.ArrayList;
import java.util.List;

/**
 * A split of an integer array is good if:
 * <p>
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 *
 * @author: seancheer
 * @date: 2021/3/5
 **/
public class _1712WaysToSplitArrInto3Subarr {

    public static void main(String[] args) {
        _1712WaysToSplitArrInto3Subarr obj = new _1712WaysToSplitArrInto3Subarr();
        int[] arr = new int[]{1, 1, 1};
        var res = obj.waysToSplit(arr);
        System.out.println("res : " + res);

        arr = new int[]{1, 2, 2, 2, 5, 0};
        res = obj.waysToSplit(arr);
        System.out.println("res : " + res);

        arr = new int[]{3, 2, 1};
        res = obj.waysToSplit(arr);
        System.out.println("res : " + res);

        arr = new int[]{0, 3, 3};
        res = obj.waysToSplit(arr);
        System.out.println("res : " + res);

    }

    /**
     * 解题思路：使用三个指针i，j，k，第一个指针从头开始，第二个指针j指向中间数组最低的位置，第三个指针k执行中间数组值最大的位置，
     * 因为数组里面的值都是>=0的，所以当处于j和k的位置的时候，那么中间数组一定是满足条件的，然后不断的往前摞动指针即可。
     * 具体的解法可以看这里：https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/discuss/999257/C%2B%2BJavaPython-O(n)-with-picture
     * 解释的很详细。需要满足的条件如下：
     * prefix[j] - prefix[i] >= prefix[i];  //中间数组大于左边数组的最低位置
     * prefix[size - 1] - prefix[k] >= prefix[k] - prefix[i]; //右边数组大于中间数组的最高位置。
     * 其中，prefix为数组和的prefix
     *
     * @param nums
     * @return
     */
    public int waysToSplit(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        final int mod = 1000000000 + 7;
        int len = nums.length;
        int[] prefix = new int[len];
        prefix[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int i = 0, j = 0, k = 0, res = 0;
        for (; i < len - 2; ++i) {
            //满足中间数组的最小位置，注意，j必须大于i，这里是为了找到j的最小位置，所以prefix[j] - prefix[i] < prefix[i]的目的
            //是为了找到第一个满足条件的位置
            while (j <= i || j < len - 1 && prefix[j] - prefix[i] < prefix[i]) {
                ++j;
            }

            //满足中间数组小于右边数组的最大位置，k必须大于等于j，并且不能处于len-1的位置，否则会导致右边数组没有值可以选
            //这里判断prefix[len - 1] - prefix[k] >= prefix[k] - prefix[i]的原因是为了找到中间数组的最大位置，所以和上一个while不一样
            //为了找到第一次不满足条件的位置，也就是k所能走的最大位置。
            while (k < j || (k < len - 1 && prefix[len - 1] - prefix[k] >= prefix[k] - prefix[i])) {
                ++k;
            }

            //这里之所以是k -j，而不是k - j + 1是因为上面的k已经第一个不满足右边数组大于等于左边数组的位置了，所以k应该给右边数组
            //而不是给左边数组，因为相当于k走到了右边数组的领域，所以不需要+1
            res += (k - j);
            res %= mod;
        }

        return res % mod;
    }
}
