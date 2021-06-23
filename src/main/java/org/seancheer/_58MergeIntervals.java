package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class _58MergeIntervals extends LeetCodeParent {
    public static void main(String[] args) {
        _58MergeIntervals obj = new _58MergeIntervals();
        int[][] intervals = new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };
        var res = obj.merge(intervals);
        printRes(res);

        intervals = new int[][]{
                {1, 4}, {4, 5}
        };
        res = obj.merge(intervals);
        printRes(res);


        intervals = new int[][]{
                {2, 3}, {4, 5}, {1, 10}
        };
        res = obj.merge(intervals);
        printRes(res);
    }

    /**
     * 合并区间。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (null == intervals || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (int[] left, int[] right) -> {
            if (left[0] < right[0]) {
                return -1;
            } else if (left[0] == right[0]) {
                if (left[1] < right[1]) {
                    return -1;
                } else if (left[1] == right[1]) {
                    return 0;
                } else {
                    return 1;
                }
            }
            return 1;
        });

        List<int[]> resList = new ArrayList<>();
        resList.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int[] last = resList.get(resList.size() - 1);
            int[] cur = intervals[i];
            if (cur[1] > last[1] && cur[0] <= last[1]) {
                last[1] = cur[1];
            } else if (cur[0] > last[1]) {
                resList.add(cur);
            }
        }

        int[][] res = new int[resList.size()][2];
        resList.toArray(res);
        return res;
    }
}
