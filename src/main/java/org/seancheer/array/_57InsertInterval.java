package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * Example 3:
 * <p>
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 * Example 4:
 * <p>
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 * Example 5:
 * <p>
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 *
 * @author: seancheer
 * @date: 2021/8/31
 **/
public class _57InsertInterval extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _57InsertInterval();
        String arrStr = "[[1,3],[6,9]]";
        int[][] intervals = convertStrTo2DemArr(arrStr);
        int[] newIntervals = new int[]{2, 5};
        var res = obj.insert(intervals, newIntervals);
        var res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[[1,5],[6,9]]

        arrStr = "[[1,2],[3,5],[6,7],[8,10],[12,16]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{4, 8};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));// [[1,2],[3,10],[12,16]]

        arrStr = "[]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{5, 7};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[[5,7]]

        arrStr = "[[1,5]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{2, 3};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[[1,5]]

        arrStr = "[[1,5]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{7, 70};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[1, 5][7, 70]

        arrStr = "[[10,50]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{1, 2};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[1, 2][10, 50]

        arrStr = "[[10,50]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{1, 100};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[[1,100]]

        arrStr = "[[3,5],[12,15]]";
        intervals = convertStrTo2DemArr(arrStr);
        newIntervals = new int[]{6, 6};
        res = obj.insert(intervals, newIntervals);
        res2 = obj.insert2(intervals, newIntervals);
        System.out.println("res:" + toStringRes(res) + "  res2:" + toStringRes(res2));//[[3,5],[6,6],[12,15]]
    }

    /**
     * 解题思路：注意，该题的intervals是排好序的，所以不需要额外的排序且不会出现重叠的情况
     * 遍历所有的intervals，遇到重叠的地方停下来，接下来开始处理重叠的叠放，直到区间不再重叠为止，然后继续遍历剩下的interval，注意，
     * 期间对每一个internal判断是否包含newInterval，如果发现包含了，直接返回intervals，因为再也没有必要继续合并了，需要注意可能的下面几种
     * 情况：
     * 1 newInterval范围在起始和末尾，和任意一个都不相交
     * 2 newInterval的start在interval范围内
     * 3 newInterval的end在interval范围内
     * 4 newInterval横跨整个interval，比如newinterval[4,5]和interval[2,3]
     * 5 newInterval在范围的中间，并且和任意一个都不相交
     * 这个解法复杂了，且看解法2
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (null == intervals || null == newInterval) {
            return null;
        }
        //newInterval直接是最终的结果
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> res = new ArrayList<>();
        int[] merge = new int[2];
        int i = 0, len = intervals.length;
        //如果要插入的数据end比第一个的起始还小，那么直接加入即可
        if (newInterval[1] < intervals[0][0]) {
            res.add(newInterval);
        }

        while (i < len) {
            int[] interval = intervals[i];
            //这种情况下，interval刚好在某个区间范围内，intervals直接就是最终的答案。
            if (interval[0] <= newInterval[0] && interval[1] >= newInterval[1]) {
                return intervals;
            }
            //说明发生了重叠，此刻一直往后遍历，处理完所有的重叠，三种情况会重叠
            //1 newInterval start在重叠范围内
            //2 newInterval end在重叠范围内
            //3 newInterval横跨整个interval
            if (newInterval[0] >= interval[0] && newInterval[0] <= interval[1] ||
                    (newInterval[1] >= interval[0] && newInterval[1] <= interval[1])
                    || (newInterval[0] <= interval[0] && newInterval[1] >= interval[1])) {
                //取较小者
                merge[0] = Math.min(newInterval[0], interval[0]);
                merge[1] = Math.max(newInterval[1], interval[1]);
                //如果merge的end坐标比接下来的起始坐标还要大，那么说明肯定重叠了，这里处理完所有的重叠
                for (; i < len && merge[1] >= intervals[i][0]; ++i) {
                    interval = intervals[i];
                    merge[1] = Math.max(merge[1], interval[1]);
                }
                res.add(merge);
            } else {
                //newInterval没有和任意一个interval相交
                if (i > 0 && newInterval[0] > intervals[i - 1][1] && newInterval[1] < intervals[i][0]) {
                    //此时直接加入newinterval即可
                    res.add(newInterval);
                }
                res.add(interval);
                ++i;
            }
        }

        //如果要加入的区间start比最后一个end还要大，那么说明是最后一个
        if (newInterval[0] > intervals[len - 1][1]) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][0]);
    }


    /**
     * 解题思路：更简单直观的解法，思路如下：
     * 1 首先把所有interval[1] < newInterval[0]的都加入到最终的结果里面
     * 2 接下来就到了重叠的地方了，重叠地方很好判断，只要interval[0] > max(newInterval[1], interval[1])，那么就说明重叠的地方结束了
     * 3 最后再把intervals里面剩余的加入到最终结果里面
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (null == intervals || null == newInterval) {
            return null;
        }

        int len = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();
        //先把所有小于newInterval的加入到res中，这样子就很好解决了解法1里面的各种情况
        while(i < len && intervals[i][1] < newInterval[0]){
            int[] interval = intervals[i];
            //一旦发现interval在范围内，直接返回intervals，不需要进行下面的逻辑
            if (interval[0] <= newInterval[0] && interval[1] >= newInterval[1]) {
                return intervals;
            }
            res.add(intervals[i++]);
        }

        int min = newInterval[0];
        int max = newInterval[1];
        //一旦发现start比max大了，说明不在重叠了
        while(i < len && intervals[i][0] <= max){
            min = Math.min(min, intervals[i][0]);
            max = Math.max(max, intervals[i][1]);
            ++i;
        }
        res.add(new int[]{min, max});
        //把剩余的在加入到res中，剩下的就没必要判断是否在区间内了，因为走到了这里就注定不可能会在区间内了
        while(i < len){
            res.add(intervals[i++]);
        }
        return res.toArray(new int[0][0]);
    }

}
