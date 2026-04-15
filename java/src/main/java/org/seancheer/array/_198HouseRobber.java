package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 注意，这道题的变形题，只是题目描述不一样而已，比如一个数组，求不相邻子序列的最大和，本质上就是这道题
 * @author: seancheer
 * @date: 2021/9/5
 **/
public class _198HouseRobber extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _198HouseRobber();
        int[] nums = new int[]{1, 2, 3, 1};
        var res = obj.rob(nums);
        var res2 = obj.rob2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //4

        nums = new int[]{2, 7, 9, 3, 1};
        res = obj.rob(nums);
        res2 = obj.rob2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //12

        nums = new int[]{2, 1, 1, 2};
        res = obj.rob(nums);
        res2 = obj.rob2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //4

        nums = new int[]{1, 2, 2, 1};
        res = obj.rob(nums);
        res2 = obj.rob2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //3
    }

    /**
     * 解题思路：不能rob相邻的屋子，碰到一个屋子，存在两种选择，偷或者不偷，那么就需要维护两种状态。
     * 假设两种状态分别为rob[]（偷）和unRob（不偷）
     * 比如碰到当前的屋子，两种状态如下：
     * 1 偷：那么上一个屋子绝对不能偷，其状态转移方程为：rob[i] = unRob[i - 1] + nums[i]，因为当前要偷，所以加上当前的财物价值。
     * 2 不偷：那么上一个屋子可以偷，也可以不偷，其状态转移方程为：unRob[i] = max(unRob[i - 1] + rob[i -1])；
     * 为什么2中的上一个屋子可以偷，也可以不偷呢？看下面的例子：
     * 假设nums[i] = 5,1,2,13,1,7，很明显，我们选择2,13,7才能得到最大的结果，对于2来讲（不偷），1只有选择不偷的情况下，才能
     * 得到最佳的结果，这也就是2中当前位置不偷，其前一个还选择不偷的原因。
     *
     * 一如既往，算法已经出来了，这道题可以根据状态转移方程来优化，减少内存占用
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return nums[0];
        }

        int[] rob = new int[len];
        int[] unRob = new int[len];
        rob[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            //当前位置偷，那么前一个位置不能偷，所以只能从前一个位置不偷进行继承
            rob[i] = unRob[i - 1] + nums[i];
            //当前位置不偷，那么前一个位置可以偷也可以不偷，因此需要判断谁比较大
            unRob[i] = Math.max(rob[i - 1], unRob[i - 1]);
        }
        return Math.max(rob[len - 1], unRob[len - 1]);
    }


    /**
     * 解题思路：解法1的空间优化
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len <= 1) {
            return nums[0];
        }

        int robSum = nums[0];
        int unRobSum = 0;
        for (int i = 1; i < len; ++i) {
            int lastRobSum = robSum;
            //当前位置偷，那么前一个位置不能偷，所以只能从前一个位置不偷进行继承
            robSum = unRobSum + nums[i];
            //当前位置不偷，那么前一个位置可以偷也可以不偷，因此需要判断谁比较大
            unRobSum = Math.max(unRobSum, lastRobSum);
        }
        return Math.max(unRobSum, robSum);
    }
}
