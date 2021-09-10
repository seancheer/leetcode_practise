package org.seancheer.math;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * <p>
 * Return the maximum product you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 58
 *
 * @author: seancheer
 * @date: 2021/9/7
 **/
public class _343IntegerBreak extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _343IntegerBreak();
        int n = 2;
        var res = obj.integerBreak(n);
        var res2 = obj.integerBreak2(n);
        System.out.println("res:" + res + "  res2:" + res2); // 1

        n = 10;
        res = obj.integerBreak(n);
        res2 = obj.integerBreak2(n);
        System.out.println("res:" + res + "  res2:" + res2); // 36

        n = 8;
        res = obj.integerBreak(n);
        res2 = obj.integerBreak2(n);
        System.out.println("res:" + res + "  res2:" + res2); // 18

        n = 5;
        res = obj.integerBreak(n);
        res2 = obj.integerBreak2(n);
        System.out.println("res:" + res + "  res2:" + res2); // 6
    }

    /**
     * 解题思路：这道题的关键在于理解乘积最大的因子为2和3，其中如果能选3就选3，有几个特殊的值：
     * 1 很明显，结果为1， 2 很明显，结果因为1， 3 很明显，结果为2
     * 4 是个很特殊的值，如果选了3，另外一个会是1，结果会出错，所以应该选2
     * 对于大于4的值，我们总是倾向于能选3就选3，这样子就能得到最大的值，这个有个数学的证明过程，从【算术几何均值不等式】
     * 推导而来，具体可以网上搜索寻找答案
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n <= 2) {
            return 1;
        }

        if (n <= 3) {
            return 2;
        }

        int res = 1;
        //每次能选3就选3
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res *= n;
        return res;
    }


    /**
     * 解题思路：动态规划思想，使用dp[i]表示i拆分后的最大乘积；
     * 其实问题的关键还是和解法1一样，在于因子如何选，很明显，2和3是最好的选择，超过4之后，只选取2和3的因子，就能得到最大值
     *
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        int[] dp = new int[n + 1];
        //注意，起始值为1，2，3
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; ++i) {
            int thre = (i >> 1);
            for (int j = 1; j <= thre; ++j) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }

        return dp[n];
    }

}
