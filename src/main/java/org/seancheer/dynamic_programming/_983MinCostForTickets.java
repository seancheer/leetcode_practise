package org.seancheer.dynamic_programming;

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

        nums = new int[]{1, 2, 3};
        res = obj.mincostTickets(nums, costs);
        System.out.println("res:" + res); //6

        nums = new int[]{1, 2, 3, 4, 6, 8, 9, 10, 13, 14, 16, 17, 19, 21, 24, 26, 27, 28, 29};
        costs = new int[]{3, 14, 50};
        res = obj.mincostTickets(nums, costs);
        System.out.println("res:" + res); //50

        nums = new int[]{364};
        costs = new int[]{3,3,1};
        res = obj.mincostTickets(nums, costs);
        System.out.println("res:" + res); //1
    }


    /**
     * 解题思路：动态规划问题，本质上是寻找状态转移方程
     * 假设days为[1, 4, 6, 7, 8, 20]，那么
     * dp[i] = min(dp[i - 1] + cost[0], dp[i - 7] + cost[1], dp[i - 30] + cost[2])
     * 这个可以这么理解：因为第i天，买了第一种方案，那么就意味着第i天的花费为cost[0]，加上第i-1天的花费，就是总花费；
     * 同样的，买了第二种方案，那么就意味着第[i - 6, i]天的花费都被cost[1]包了， 加上第i-7天的花费，就是总花费；
     * 以此类推，30天也是同样的。
     * <p>
     * 如果某一天没有旅行计划，那么只需要简单的将dp[i] = dp[i - 1]即可，这里仅仅只是一个简单的状态推算设置。
     * 写代码的时候，我们从前往后，代码会好理解一些。
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        if (null == days || null == costs || costs.length < 3) {
            return 0;
        }

        final int upBound = 366;
        int[] map = new int[upBound];
        for (int day : days) {
            map[day] = 1;
        }

        final int maxDay = days[days.length - 1];
        int[] status = new int[maxDay + 1];
        //这里要严格算到最后一天，因为可能存在最后一天单独买个一天就能得到最佳的情况。
        for (int day = 1; day <= maxDay; ++day) {
            //如果这一天没有旅游的话，那么我们直接继承前一天的花费
            if (map[day] == 0) {
                status[day] = status[day - 1];
                continue;
            }
            //当day不足7天或者30天的时候，也可以直接买7或者30，这里是一个细节，需要注意！！！
            status[day] = Math.min(status[Math.max(0, day - 1)] + costs[0],
                    Math.min(status[Math.max(0, day - 7)] + costs[1], status[Math.max(0, day - 30)] + costs[2]));
            if (day >= 7) {
                status[day] = Math.min(status[day - 7] + costs[1], status[day]);
            }

            if (day >= 30) {
                status[day] = Math.min(status[day - 30] + costs[2], status[day]);
            }
        }

        return status[maxDay];
    }
}
