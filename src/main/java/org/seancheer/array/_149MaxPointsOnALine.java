package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * Example 2:
 * <p>
 * <p>
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * All the points are unique.
 *
 * @author: seancheer
 * @date: 2021/9/3
 **/
public class _149MaxPointsOnALine extends LeetCodeParent {

    public static void main(String[] args) {
        var obj = new _149MaxPointsOnALine();
        String points = "[[1,1],[2,2],[3,3]]";
        var res = obj.maxPoints(convertStrTo2DemArr(points));
        System.out.println("res:" + res); // 3

        points = "[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]";
        res = obj.maxPoints(convertStrTo2DemArr(points));
        System.out.println("res:" + res); // 4
    }


    /**
     * 解题思路：设想有三个点(x1,y1) (x2,y2) (x3,y3)，如果这三个点在同一条直线上，那么就意味着任意两个点的斜率是一样的；
     * 针对于这个特点，我们就可以挨个遍历，存储同一个斜率出现的次数，最大的即为最终答案；
     * 注意，因为斜率有可能会存在分母为0的情况，即平行于y轴，此时数学除法会有问题，所以秒就妙在，如果斜率相等，那么
     * diffX = x2 - x1
     * diffY = y2 - y1
     * 除以diffX和diffY的最大公约数，如果斜率相等，那么除以最大公约数的结果一定会相同，所以我们可以直接使用gcd的结果作为key来
     * 标识相同的斜率，这样子就成功避免了分母为0的情况
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        if (null == points || points.length == 0 || points[0] == null) {
            return 0;
        }

        if (points.length <= 2) {
            return points.length;
        }

        int len = points.length;
        int res = 0;
        for (int i = 0; i < len; ++i) {
            Map<String, Integer> statusMap = new HashMap<>();
            //这道题加了规定，坐标都是唯一的，这里多做考虑，假设有重叠
            int overLap = 0;
            int tmpMax = 0;
            //直接从i后面开始，因为如果从0开始，就重复计算了，属实没有必要
            for (int j = i + 1; j < len; ++j) {
                int diffX = points[j][0] - points[i][0];
                int diffY = points[j][1] - points[i][1];
                if (diffX == 0 && diffY == 0) {
                    ++overLap;
                    continue;
                }

                //很巧妙的做法，避免了对于平行于y轴斜率（分母为0）的特殊处理
                int maxGCD = maxGCDInternal(diffX, diffY);
                diffX /= maxGCD;
                diffY /= maxGCD;

                String key = diffX + "," + diffY;
                if (statusMap.containsKey(key)) {
                    statusMap.put(key, statusMap.get(key) + 1);
                } else {
                    statusMap.put(key, 1);
                }
                tmpMax = Math.max(tmpMax, statusMap.get(key));
            }

            //统计最终的结果，注意加上overlap，同时记得算上points[i]
            res = Math.max(res, tmpMax + overLap + 1);
        }
        return res;
    }

    private int maxGCDInternal(int m, int n) {
        return n == 0 ? m : maxGCDInternal(n, m % n);
    }
}
