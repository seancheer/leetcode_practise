package org.seancheer.dynamic_programming;

import org.seancheer.utils.LeetCodeParent;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 * <p>
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 * <p>
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 * <p>
 * Input: nums = [1,5]
 * Output: 10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 *
 * @author: seancheer
 * @date: 2021/9/11
 **/
public class _312BurstBallons extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _312BurstBallons();
        int[] nums = new int[]{3, 1, 5, 8};
        var res = obj.maxCoins(nums);
        System.out.println("res:" + res); // 167


        nums = new int[]{1, 5};
        res = obj.maxCoins(nums);
        System.out.println("res:" + res); // 10

        nums = new int[]{3, 1, 8, 1};
        res = obj.maxCoins(nums);
        System.out.println("res:" + res); // 64
    }


    /**
     * 解题思路：hard级别的动态规划，尝试找出递推公式
     * 使用dp[i][j]表示区间(i,j)（注意都是开区间）气球被爆破的话所能得到的最大分数，那么对于任意的gap，假设当前的爆破位置为k，
     * 其中i < k < j，那么其递推公式为：
     * for k in range(i,j):
     *     dp[i][j] = max(dp[i][j], nums[k] * nums[i] * nums[j] + dp[i][k] + dp[k][j])
     * 相当于从前一个gap进行状态的转移，比如当前气球状态为：
     * 0 1 2 3 4 5 6 7
     * for k in range(2,5):
     *     dp[2][5] = max(dp[2][5] + nums[k] * nums[2] * nums[5] + dp[2][k] + dp[k][5])
     * 因为dp[i][j]表示的(i,j)区间，所有的气球都被爆破了，所以k与i和j就已经相邻了，因此可以这么计算
     * 注意：因为题目要求边缘位置的爆破的话, nums[-1]和nums[len]为1，所以在创建dp数组的时候，可以多创建两个位置给这两个
     * 因为这道题的爆破气球的顺序并没有严格规定，所以可以左边爆破一部分，右边爆破一部分，这样子找到最大的爆破位置即可，好好理解
     * 这个递推公式
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int len = nums.length;
        int sz = len + 2;
        int[][] dp = new int[sz][sz];
        //注意，gap要等于size，这样子dp[i][j]才能覆盖所有的size
        for (int gap = 2; gap < sz; ++gap) {
            for (int i = 0; i < sz - gap; ++i) {
                int j = i + gap;
                //在(i,j)的范围内寻找合适的位置
                for (int k = i + 1; k < j; ++k) {
                    //两端的值，注意边界的1，因为dp里面的i表示nums里面的i - 1，所以这里要注意
                    int neighborVal = ((i - 1) < 0 ? 1 : nums[i - 1]) *
                            ((j - 1) >= len ? 1 : nums[j - 1]);
                    //dp里面的i表示nums里面的i - 1，因此，递推公式里面的index都要进行-1操作，k - 1不会越界，这里注意
                    dp[i][j] = Math.max(dp[i][j], neighborVal * nums[k - 1] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][sz - 1];
    }
}
