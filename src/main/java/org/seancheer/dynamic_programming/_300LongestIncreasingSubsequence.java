package org.seancheer.dynamic_programming;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * @author: seancheer
 * @date: 2021/7/28
 **/
public class _300LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        _300LongestIncreasingSubsequence obj = new _300LongestIncreasingSubsequence();
        var res = obj.lengthOfLIS(nums);
        var res2 = obj.lengthOfLIS2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //4

        nums = new int[]{0, 1, 0, 3, 2, 3};
        res = obj.lengthOfLIS(nums);
        res2 = obj.lengthOfLIS2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //4

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        res = obj.lengthOfLIS(nums);
        res2 = obj.lengthOfLIS2(nums);
        System.out.println("res:" + res + "   res2:" + res2);  //1

        nums = new int[]{7};
        res = obj.lengthOfLIS(nums);
        res2 = obj.lengthOfLIS2(nums);
        System.out.println("res:" + res + "   res2:" + res2);  //1
    }


    /**
     * 解题思路：动态规划问题，有子问题
     * 使用status来记录位置为i时最大的子串，对于status[j](j < i)，如果nums[j] < nums[i]，那么很明显
     * status[i] = Math.max(status[j] + 1, status[i])，
     * 注意status的初始值应该为1
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[] status = new int[len];
        status[0] = 1;
        int res = 1;
        for (int i = 1; i < len; ++i) {
            //初始值都应该为1
            status[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    status[i] = Math.max(status[i], status[j] + 1);
                }
            }
            res = Math.max(res, status[i]);
        }
        return res;
    }

    /**
     * 解题思路：更快的解法，不用动态规划的解法
     * 使用一种叫做耐心排序法的思路，用一个tail数组表示对于长度i下，末尾最小的数，比如tails[i]，表示长度为i的情况下末尾最小的数字，
     * 很容易可以推出来, tails[i] < taills[j] when i < j，因为j是在i的基础上衍生出来的，所以必然就有这样的一个关系，这样也就
     * 意味这tail可以用二分法来加快查找效率，假设来个新的num且当前tail的大小为size，更新tail的方式如下：
     * 1 如果num > tail[size - 1]，那么增加tail，即tail[size] = num，意味着最小子串的长度增加
     * 2 如果tail[i] < num < tail[j]，那么更新tail[j] = num，为什么我们还要更新size以前的值呢？这是为了维护tail的定义，以及单调自增性
     * 为什么这个方法可以奏效呢？这是因为我们每次都记录了某个长度i下末尾最小的数字，这样子就可以保证最终子串是最小的，就可以尽可能
     * 的往后面加入新的数字。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[] tails = new int[len];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            while (i != j) {
                int mid = (i + j) >> 1;
                if (tails[mid] < num) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }

            tails[i] = num;
            if (i == res){
                ++res;
            }
        }

        return res;
    }
}
