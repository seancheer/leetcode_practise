package org.seancheer.greedy;

import org.seancheer.dynamic_programming._983MinCostForTickets;
import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.
 * <p>
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
 * <p>
 * Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * Example 2:
 * <p>
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= points.length <= 104
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
 *
 * @author: seancheer
 * @date: 2021/7/1
 **/
public class _452MinNumberOfArrowToBurstBalloons extends LeetCodeParent {
    public static void main(String[] args) {
        _452MinNumberOfArrowToBurstBalloons obj = new _452MinNumberOfArrowToBurstBalloons();
        var points = convertStrTo2DemArr("[[10,16],[2,8],[1,6],[7,12]]");
        int res = obj.findMinArrowShots(points);
        System.out.println("res:" + res);  //2

        points = convertStrTo2DemArr("[[1,2],[3,4],[5,6],[7,8]]");
        res = obj.findMinArrowShots(points);
        System.out.println("res:" + res);  //4

        points = convertStrTo2DemArr("[[1,2],[2,3],[3,4],[4,5]]");
        res = obj.findMinArrowShots(points);
        System.out.println("res:" + res);  //2

        points = convertStrTo2DemArr("[[-2147483646,-2147483645],[2147483646,2147483647]]");
        res = obj.findMinArrowShots(points);
        System.out.println("res:" + res);  //2
    }


    /**
     * 解题思路：典型的贪心算法问题
     * 对所有气球进行一个排序，排序规则如下：
     * 优先坐标0，如果left的坐标0比right的坐标0小，那么说明left的气球比right气球小；
     * 如果left的坐标0等于right的坐标0，比较坐标1，坐标1小者则小。
     * <p>
     * 为什么按照这种方式进行排序呢？这里直接以一个例子[[10,16],[2,8],[1,6],[7,12]]说明：
     * 排序后的结果为：[1,6],[2,8],[7,12],[10,16]
     * 此时开始遍历这个优先队列，我们先看到[1,6]，为了尽可能多的射爆气球，那么就需要将接下来气球的坐标0小于等于6一并射爆，
     * 直到遇到大于[1,6]的坐标1，这里遇到了[7,12]，然后根据一样的规则，[7,12]和[10,16]可以一并射爆，所以使用的箭数为2；
     * 按照这种方式得到的结果就是最优解。
     * <p>
     * 这里还需要注意一个条件，在这个遍历的过程中，气球的坐标1会注定我们所能射的最远的距离，因此也要作为一个截止的条件
     * 比如：[1,10],[3,7],[9,12]，
     * 其中的[3,7]注定了我们不能射气球超过7，所以[9,12]就无法和[1,10]一起射爆了
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (null == points || points.length == 0 || points[0] == null) {
            return 0;
        }

        /*这个最好还是采用下面的sort方式，因为相减如果数字过大，可能会存在翻转的问题，导致排序的结果不对
        Arrays.sort(points, (left, right) -> {
            return left[0] - right[0];
        });
        */
        Arrays.sort(points, Comparator.comparingInt(left -> left[0]));

        //赋初值，因为用例中包含Integer.MIN_VALUE这样的用例
        int minRight = points[0][1];
        int res = 1;
        for (int i = 1; i < points.length; ++i) {
            int[] item = points[i];
            if (item[0] > minRight) {
                minRight = item[1];
                res++;
                continue;
            }

            minRight = Math.min(minRight, item[1]);
        }
        return res;
    }
}
