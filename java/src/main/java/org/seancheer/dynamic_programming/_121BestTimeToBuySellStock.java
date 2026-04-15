package org.seancheer.dynamic_programming;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 * @author: seancheer
 * @date: 2021/7/16
 **/
public class _121BestTimeToBuySellStock {
    public static void main(String[] args) {
        _121BestTimeToBuySellStock obj = new _121BestTimeToBuySellStock();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        var res = obj.maxProfit(prices);
        var res2 = obj.maxProfit2(prices);
        System.out.println("res:" + res + "  res2:" + res2);  //5

        prices = new int[]{7, 6, 4, 3, 1};
        res = obj.maxProfit(prices);
        res2 = obj.maxProfit2(prices);
        System.out.println("res:" + res + "  res2:" + res2);  //0
    }

    /**
     * 解题思路：
     * 其实就是检查数组之间的差值，然后加上差值，如果差值一旦变为负的，假设当前index位i，
     * 那么需要重新开始，因为不重新开始的话，i左边的值为负值，加上i右边的，一定会更小，所以重新开始计算。
     * 解法2更容易理解一些，参考解法2
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int res = 0, tmp = 0;
        for (int i = 0; i < prices.length - 1; ++i) {
            tmp += (prices[i + 1] - prices[i]);
            if (tmp <= 0) {
                tmp = 0;
            } else {
                res = Math.max(res, tmp);
            }
        }
        return res;
    }


    /**
     * 解题思路：从头开始遍历，同时记录当前最小的位置，每到一个位置，
     * 1 如果遇到更小的值了，那么更新最小值
     * 2 如果遇到较大的值了，减去最小的值得到答案
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (null == prices || prices.length == 0) {
            return 0;
        }

        int res = 0, min = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] <= min) {
                min = prices[i];
            } else {
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }
}
