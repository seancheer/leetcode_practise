package org.seancheer.greedy;

import org.seancheer._342PowerOfFour;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * @author: seancheer
 * @date: 2021/7/29
 **/
public class _55JumpGame {
    public static void main(String[] args) {
        _55JumpGame obj = new _55JumpGame();
        int[] nums = new int[]{2, 3, 1, 1, 4};
        var res = obj.canJump(nums);
        var res2 = obj.canJump2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //true

        nums = new int[]{3, 2, 1, 0, 4};
        res = obj.canJump(nums);
        res2 = obj.canJump2(nums);
        System.out.println("res:" + res + "   res2:" + res2); //false
    }

    /**
     * 解题思路：贪心算法
     * 进行遍历的时候，我们记录下每个区段内所能走的最远的位置maxDis，如果到了位置i，发现maxDis还不能到i，那么就意味着我们
     * 不可能能跳到该位置，说明失败，否则的话就成功
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return true;
        }

        int maxDis = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            //已经到了i的位置，但是我们所能跳的最远的位置还没到i，失败
            if (maxDis < i) {
                return false;
            }
            //更新本区段内所能跳的最远的位置
            maxDis = Math.max(maxDis, nums[i] + i);
        }
        return true;
    }

    /**
     * 解题思路：动态规划算法，dp[i]表示当前位置时所能剩余的最大跳力。
     * 那么转移方程是什么呢？dp[i] = max(dp[i - 1] - 1, nums[i] - 1)，
     * dp[i - 1] - 1表示利用前一个位置的剩余跳力跳到当前这个位置，所以需要-1（在前一个位置不停）
     * nums[i - 1] - 1表示停到上一个位置后，利用上一个位置新获得的跳力跳到当前位置，所以需要-1，（一旦停了，跳力就要清零）
     * 上面两个取较大的值，为什么？如果说利用前一个位置剩余的跳力跳到当前位置比停到前一个位置后，新获得跳力跳到当前位置后（注意，
     * 一旦停下来，就算跳力没有用完，也会直接清零）的跳力还要大，那么我们就选择直接越过前一个位置跳到当前这个位置。
     *
     * 如果剩余跳力变成负值了，那就说明无法到达最后了，返回即可
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i -1], nums[i - 1]) - 1;
            //剩余跳力没了，好吧，返回false
            if (dp[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
