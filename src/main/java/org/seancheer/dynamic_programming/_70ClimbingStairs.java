package org.seancheer.dynamic_programming;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _70ClimbingStairs {

    public static void main(String[] args) {
        _70ClimbingStairs obj = new _70ClimbingStairs();
        int n = 2;
        int res = obj.climbStairs(n);
        System.out.println("res:" + res);  //2

        n = 3;
        res = obj.climbStairs(n);
        System.out.println("res:" + res);  //3
    }


    /**
     * 解题思路：其实本质上就是一个斐波拉契数列，这里先用动态规划的思想来做。（实际上就是再实现斐波拉契数列的过程）
     * dp[i]表示爬到第i层的方式，因为一次要么爬一层要么爬两层，所以我们的状态转移方程为：
     * dp[i] = dp[i - 1] + dp[i - 2]
     * 表示从i - 1层爬1层到i层；从i - 2层爬2层到i层；很明显，到第i层的方式就是两者的和；
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
