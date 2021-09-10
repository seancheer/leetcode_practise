package org.seancheer.dynamic_programming;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 *
 * @author: seancheer
 * @date: 2021/8/4
 **/
public class _714BestTimeToBuySellStockWithTransFee {
    public static void main(String[] args) {
        _714BestTimeToBuySellStockWithTransFee obj = new _714BestTimeToBuySellStockWithTransFee();
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        var res = obj.maxProfit(prices, fee);
        System.out.println("res: " + res); //8

        prices = new int[]{1, 3, 7, 5, 10, 3};
        fee = 3;
        res = obj.maxProfit(prices, fee);
        System.out.println("res: " + res); //6
    }

    /**
     * 动态规划问题：用buy[i]表示到第i天买的状态，sell[i]表示第i天卖的最大利润
     * 具体介绍看代码注释
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (null == prices || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];
        for (int i = 1; i < len; ++i) {
            //表示在前一天卖掉的基础上sell[i - 1]买入今天的股票，或者什么都不做，继续状态buy[i - 1]，两者取较大者
            buy[i] = Math.max(sell[i - 1] - prices[i], buy[i - 1]);
            //表示在前一天买的基础上buy[i - 1]卖掉今天的股票，或者什么都不做，继续状态sell[i - 1]，两者取较大者
            sell[i] = Math.max(buy[i - 1] + prices[i] - fee, sell[i - 1]);
        }
        //因为最后一定是卖掉，所以这里的结果就是最终的结果
        return sell[len - 1];
    }
}
