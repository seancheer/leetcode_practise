package org.seancheer;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in three different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * <p>
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * Example 2:
 * <p>
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 * 注意，costs是固定的，不会变，始终是2，7，15；并且days始终是递增的。
 *
 * @author: seancheer
 * @date: 2021/6/4
 **/
public class _983MinCostForTickets {
    public static void main(String[] args) {
        _983MinCostForTickets obj = new _983MinCostForTickets();
        int[] nums = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs = new int[]{2, 7, 15};
        int res = obj.mincostTickets(nums, costs);
        System.out.println("res:" + res);  //11

        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        res = obj.mincostTickets(nums, costs);
        System.out.println("res:" + res); //17

    }


    /**
     * 解题思路：costs[3], 1-day, 7-days, 30-days
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        if (null == days || days.length == 0) {
            return 0;
        }

        int len = days.length;
        int maxDay = days[len - 1];

        return 0;
    }
}
