package org.seancheer.math;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: 2
 * Explanation: 5 = 2 + 3
 * Example 2:
 * <p>
 * Input: n = 9
 * Output: 3
 * Explanation: 9 = 4 + 5 = 2 + 3 + 4
 * Example 3:
 * <p>
 * Input: n = 15
 * Output: 4
 * Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * <p>
 * 找出组合出目标值的正数和的表现方式（值必须连续），比如目标5，有两种方式
 * 5 = 2 + 3
 * 注意，1和4不行，因为必须连续
 * Constraints:
 * <p>
 * 1 <= n <= 109
 *
 * @author: seancheer
 * @date: 2021/9/5
 **/
public class _829ConsecutiveNumbersSum extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _829ConsecutiveNumbersSum();
        int n = 5;
        var res = obj.consecutiveNumbersSum(n);
        System.out.println("res: " + res); //2

        n = 9;
        res = obj.consecutiveNumbersSum(n);
        System.out.println("res: " + res); //3

        n = 15;
        res = obj.consecutiveNumbersSum(n);
        System.out.println("res: " + res); //4
    }

    /**
     * 解题思路：这是一道数学题，采用如下的推算方式，假设有k个连续的数能够组成n，那么有如下关系
     * x + (x + 1) + (x + 2) + (x + 3) + ... + (x + k - 1) = n
     * kx + k(k - 1) / 2 = n
     * kx = n - k * (k - 1) / 2
     * kx = n - (k^2 - k) / 2
     * 问题就转变成了该表达式可能存在的解的个数，接下来我们的目标就是尝试给k取值，找出这个方程组可能存在的解的个数
     * （为什么选择k呢？因为k固定的情况下，只有一组解），为了减少遍历的次数，我们需要确定k的范围
     * 因为k和x都大于1，所以就意味着
     * n - (k^2 - k) / 2 > 0
     * k^2 - k < 2n ≈ (k - 1)^2 < 2n = k - 1 < sqrt(2n) = k < sqrt(2n) + 1
     * 这样子，我们就找到了k的大致范围，然后根据这个范围，我们挨个取k的值，并统计结果
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        if (n <= 0) {
            return 0;
        }

        int res = 1;
        //从两个数开始
        for (int k = 2; k < Math.sqrt(2 * n) + 1; ++k) {
            //因为另外一边是kx,而且k和x都大于0且是整数，所以结果必须可以整除k
            //而不同的k只有一组答案，所以也保证了结果的唯一性
            int xVal = (n - k * (k - 1) / 2) / k;  //x不能等于0
            //需要是k的倍数
            boolean valid = ((n - k * (k - 1) / 2) % k == 0);
            if (xVal > 0 && valid) {
                ++res;
            }
        }
        return res;
    }
}
