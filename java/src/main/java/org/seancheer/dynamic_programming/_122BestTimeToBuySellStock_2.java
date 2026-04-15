package org.seancheer.dynamic_programming;

/**
 *You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e., max profit = 0.
 *
 * @author: seancheer
 * @date: 2021/7/16
 **/
public class _122BestTimeToBuySellStock_2 {
    public static void main(String[] args) {
        _122BestTimeToBuySellStock_2 obj = new _122BestTimeToBuySellStock_2();
        int[] prices = new int[]{7,1,5,3,6,4};
        var res = obj.maxProfit(prices);
        System.out.println("res:" + res);  //7

        prices = new int[]{1,2,3,4,5};
        res = obj.maxProfit(prices);
        System.out.println("res:" + res);  //4

        prices = new int[]{7,6,4,3,1};
        res = obj.maxProfit(prices);
        System.out.println("res:" + res);  //0
    }

    /**
     * 解题思路：这道题要求尽可能多的交易，因此，只要差值为正的，那么就直接进行交易计算，把所有能赚钱的天数加起来就是最后的结果，很简单的思路
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(null == prices || prices.length == 0){
            return 0;
        }

        int len = prices.length;
        int res = 0;
        for(int i = 0;i < len - 1;++i){
            int tmp = prices[i + 1] - prices[i];
            if (tmp > 0){
                res += tmp;
            }
        }
        return res;
    }
}
