package org.seancheer.dynamic_programming;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 * <p>
 * Input: prices = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 * 要求交易两次，使得利润最大化。
 *
 * @author: seancheer
 * @date: 2021/7/16
 **/
public class _122BestTimeToBuySellStock_3 {
    public static void main(String[] args) {
        _122BestTimeToBuySellStock_3 obj = new _122BestTimeToBuySellStock_3();
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        var res = obj.maxProfit(prices);
        var res2 = obj.maxProfit2(prices);
        var res3 = obj.maxProfit3(prices);
        var res4 = obj.maxProfit4(prices);
        var res5 = obj.maxProfit5(prices);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3 + "   res4:" + res4 + "  res5:" + res5);  //6

        prices = new int[]{1, 2, 3, 4, 5};
        res = obj.maxProfit(prices);
        res2 = obj.maxProfit2(prices);
        res3 = obj.maxProfit3(prices);
        res4 = obj.maxProfit4(prices);
        res5 = obj.maxProfit5(prices);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3 + "   res4:" + res4 + "  res5:" + res5);  //4

        prices = new int[]{7, 6, 4, 3, 1};
        res = obj.maxProfit(prices);
        res2 = obj.maxProfit2(prices);
        res3 = obj.maxProfit3(prices);
        res4 = obj.maxProfit4(prices);
        res5 = obj.maxProfit5(prices);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3 + "   res4:" + res4 + "  res5:" + res5); //0

        prices = new int[]{1};
        res = obj.maxProfit(prices);
        res2 = obj.maxProfit2(prices);
        res3 = obj.maxProfit3(prices);
        res4 = obj.maxProfit4(prices);
        res5 = obj.maxProfit5(prices);
        System.out.println("res:" + res + "    res2:" + res2 + "    res3:" + res3 + "   res4:" + res4 + "  res5:" + res5);   //0
    }

    /**
     * 解题思路：动态规划问题，这道题的状态转移方程不太好想。
     * 使用dp[i][k]表示在i天进行第k次交易（本题目要求最多就两次）
     * 那么假设在第i天的时候，有两种选择：
     * 1 不交易，那么dp[i][k] = dp[i - 1][k]
     * 2 交易，那么必然在第i天之前进行了买入行为，假设在第j天买了，因此dp[i][k] = dp[j - 1][k - 1] + prices[i] - prices[j]
     * 综上，状态转移方程为：
     * dp[i][k] = max(dp[i - 1][k], dp[j - 1][k - 1] + prices[i] - prices[j]) （这里使用dp[j - 1][k - 1]或者dp[j][j - 1]都可以，因为同一天买入并卖出并不影响结果）
     * 上面存在一个问题，为了使dp的结果尽可能的大，因此，我们需要保证prices[i] - prices[j]的值尽量最大，因此需要在第i天之前找到
     * 最合适买入的时间j，但是状态状态方程中还依赖dp[j - 1][k - 1]，所以正确的做法应该是在查找合适买点的时候，prices[j] - dp[j - 1][k - 1]
     * 最小，这样子就能保证prices[i] - (prices[j] - dp[j - 1][k - 1])最大化。具体可以看代码来理解
     * 该答案超时，但是整体的变种思路是根据以这个答案为基础的。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        final int maxTrans = 3;
        int len = prices.length;
        int[][] dp = new int[len][maxTrans];

        for (int k = 1; k < maxTrans; ++k) {
            for (int i = 1; i < len; ++i) {
                int min = prices[0];
                for (int j = 1; j <= i; ++j) {
                    //保证这个值尽可能的小
                    min = Math.min(min, prices[j] - dp[j - 1][k - 1]);
                }
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[len - 1][maxTrans - 1];
    }

    /**
     * 解题思路：1的简单优化，可以认真观察，就是在第三层循环里面寻找最小值的时候，每一次都进行了重复计算，因此，可以省去这部分的
     * 重复计算
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }
        final int maxTrans = 3;
        int len = prices.length;
        int[][] dp = new int[len][maxTrans];

        for (int k = 1; k < maxTrans; ++k) {
            int min = prices[0];
            for (int i = 1; i < len; ++i) {
                //这里重复计算了，因为每次第三层for循环完成之后，dp[i][k]只是向前推动了一个维度，在下一次的第三层for循环的时候，i+1了，
                //但是j又从1开始计算，因为上一般只把dp[i][k]推进到了dp[i + 1][k]，所以本次将会重复计算。故可以优化成两层循环
//                for (int j = 1; j <= i; ++j) {
//                    min = Math.min(min, prices[j] - dp[j - 1][k - 1]);
//                }
                min = Math.min(min, prices[i] - dp[i - 1][k - 1]);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[len - 1][maxTrans - 1];
    }

    /**
     * 解题思路：1的进一步优化，把交易次数放在内层循环，需要注意的是，因为交易次数在内层循环，所以必须用一个数组来存储min，
     * 以前是在内层循环来计算min的，把交易次数放在内层循环后，就必须存储这个min了，供下次使用
     * 重复计算
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }
        final int maxTrans = 3;
        int len = prices.length;
        int[][] dp = new int[len][maxTrans];
        int[] min = new int[maxTrans];
        min[1] = min[2] = prices[0];
        for (int i = 1; i < len; ++i) {
            for (int k = 1; k < maxTrans; ++k) {
                //以前是在外层循环来计算min的，现在放在了交易次数里面，那么就意味着我们必须存储这个min值。
                min[k] = Math.min(min[k], prices[i] - dp[i - 1][k - 1]);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min[k]);
            }
        }


        return dp[len - 1][maxTrans - 1];
    }


    /**
     * 解题思路：在3的基础上，进行进一步优化，我们从3中可以看到，dp[i][k]依赖于dp[i - 1][k - 1]和dp[i - 1][k]，
     * 因此，可以将dp压缩为一维数组，类似于其他的动态规划压缩的思路。
     * 注意，可以在二维坐标系上面画图示例一下，这里很明显压缩dp交易次数的那一维，因为每次内层循环的i是始终不变的。
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }
        final int maxTrans = 3;
        int len = prices.length;
        int[] dp = new int[maxTrans];
        int[] min = new int[maxTrans];
        min[1] = min[2] = prices[0];
        for (int i = 1; i < len; ++i) {
            for (int k = 1; k < maxTrans; ++k) {
                //以前是在外层循环来计算min的，现在放在了交易次数里面，那么就意味着我们必须存储这个min值。
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }


        return dp[maxTrans - 1];
    }

    /**
     * 解题思路：在4的基础上的终极优化，可以看到的是，我们的dp和min都是大小为3的一维数组，这就意味着我们只有3个状态需要维护，
     * 因此可以将这个三维数组直接展开为单个变量
     *
     * 这种优化我们也可以使用下面的方式来解释：
     * 1 buy1：表示我们在进行第一次交易的买的时候，尽可能的选择价格低的买
     * 2 sell1：表示我们在第一次交易卖的时候，尽可能的保证利益最大化，prices[i] - buy1表示当天卖的利润
     * 3 buy2：表示我们在进行第二次交易买的时候，尽可能的选择低价格买（还需要叠加第一次交易的利润，因此prices[i] - sell1）
     * 4 sell2：表示在进行第二次交易卖的时候，尽可能的保证利益最大化
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }
        final int maxTrans = 3;
        int len = prices.length;
        int buy1 = prices[0], buy2 = prices[0];
        int sell1 = 0, sell2 = 0;
        for (int i = 1; i < len; ++i) {
            //因为内层循环最大值为2，因此展开后其实是下面的结果，进一步使用单个变量优化后的结果为：
//            min[1] = Math.min(min[1], prices[i] - dp[0]);
//            dp[1] = Math.max(dp[1], prices[i] - min[1]);
//            min[2] = Math.min(min[2], prices[i] - dp[1]);
//            dp[2] = Math.max(dp[2], prices[i] - min[2]);

            //相当于把内层的for循环直接展开
            buy1 = Math.min(buy1, prices[i]); //注意观察，dp[0]始终为0，所以这里直接省略
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }

        return sell2;
    }
}
