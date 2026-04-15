package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * @author: seancheer
 * @date: 2021/8/30
 **/
public class _58MergeIntervals extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _58MergeIntervals();
        String arrStr = "[[1,3],[2,6],[8,10],[15,18]]";
        int[][] intervals = convertStrTo2DemArr(arrStr);
        var res = obj.merge(intervals);
        System.out.println("res:" + toStringRes(res));//[[1,6],[8,10],[15,18]]

        arrStr = "[[1,4],[4,5]]";
        intervals = convertStrTo2DemArr(arrStr);
        res = obj.merge(intervals);
        System.out.println("res:" + toStringRes(res));//[[1,5]]

        arrStr = "[[1,3]]";
        intervals = convertStrTo2DemArr(arrStr);
        res = obj.merge(intervals);
        System.out.println("res:" + toStringRes(res));//[[1,3]]

        arrStr = "[[2,3],[5,5],[2,2],[3,4],[3,4]]";
        intervals = convertStrTo2DemArr(arrStr);
        res = obj.merge(intervals);
        System.out.println("res:" + toStringRes(res));//[[2,4],[5,5]]

        arrStr = "[[1,4],[0,5]]";
        intervals = convertStrTo2DemArr(arrStr);
        res = obj.merge(intervals);
        System.out.println("res:" + toStringRes(res));//[0, 5]
    }

    /**
     * 解题思路：首先按照第一个坐标进行排序，比如下面的坐标[[1,3],[1,7],[2,6],[8,10],[15,18]]
     * 先找到第一个坐标假设为[1,3]，然后记录1开头的最大值，此时为7，然后一直遍历到第一个坐标比7大的，同时记录整个过程中第二个
     * 坐标的最大值max，如果max都比下一个数组的第一个坐标小，重复上述逻辑即可
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return new int[0][0];
        }

        Arrays.sort(intervals, Comparator.comparingInt(left -> left[0]));
        List<int[]> res = new ArrayList<>();
        int len = intervals.length;
        int i = 0;
        while (i < len) {
            int[] resItem = new int[2];
            int first = intervals[i][0];
            resItem[0] = first;
            int secondMax = intervals[i][1];
            //一直遍历，直到某个数组的第一个坐标比当前的最大值还大，说明坐标出现了断层，必须重新开始
            while (i < len && secondMax >= intervals[i][0]) {
                secondMax = Math.max(secondMax, intervals[i][1]);
                ++i;
            }
            resItem[1] = secondMax;
            res.add(resItem);
        }

        return res.toArray(new int[res.size()][2]);
    }
}
