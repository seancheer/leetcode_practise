package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * Example 4:
 * <p>
 * Input: height = [1,2,1]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 * 如果中间比较高，那么水也可以穿过他，只关心两边是否能容纳，比如[1,8,1]，这个就可以容纳2 * 1，虽然中间的
 * 8比较高，不过没关系
 *
 * @author: seancheer
 * @date: 2021/8/12
 **/
public class _11ContainerWithMostWater {
    public static void main(String[] args) {
        var obj = new _11ContainerWithMostWater();
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        var res = obj.maxArea(height);
        var res2 = obj.maxArea2(height);
        System.out.println("res:" + res + "   res2:" + res2);//49

        height = new int[]{1, 1};
        res = obj.maxArea(height);
        res2 = obj.maxArea2(height);
        System.out.println("res:" + res + "   res2:" + res2);//1

        height = new int[]{4, 3, 2, 1, 4};
        res = obj.maxArea(height);
        res2 = obj.maxArea2(height);
        System.out.println("res:" + res + "   res2:" + res2);//16

        height = new int[]{1, 2, 1};
        res = obj.maxArea(height);
        res2 = obj.maxArea2(height);
        System.out.println("res:" + res + "   res2:" + res2);//2
    }

    /**
     * 解题思路：首先找到height的最大值，然后从最大值作为一条切线，往下走，寻找刚好能够在这个高度上容纳下水的位置，
     * 计算最大值。效率比较低，还有更快的方式，即解法2，使用双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }

        int maxVal = Arrays.stream(height).max().getAsInt();
        int res = 0;
        //maxVal是一条切线，从上往下走
        for (; maxVal > 0; --maxVal) {
            int i = 0, j = height.length - 1;
            while (i <= j && height[i] < maxVal) {
                ++i;
            }

            while (i <= j && height[j] < maxVal) {
                --j;
            }

            res = Math.max(res, (j - i) * maxVal);
        }
        return res;
    }

    /**
     * 解题思路：双指针解法，可以证明的是，从头开始遍历的时候，如果发现一个更高的，那么就意味着以该位置为起点可能会产生更大
     * 的结果，在该位置后面且比该位置低的，不可能产生更大的结果，比如：
     * [8,7,5,2]，以8为起点比以7，5为起点的结果绝对会更大。
     * 但是在该位置之前，比该位置低的，有可能会产生更大的结果，比如：
     * [1,3,1,1,1,1]，很明显的是，以1为起点结果会更大
     * 所以，思路来了，正想看是这样，其实反向看（从末尾往起点走）也是如此，所以我们使用两个指针，一个从头开始，一个从尾部开始，
     * 同时向中间移动来寻找最大的位置，在O(n)的时间内就可以找出最佳答案。
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }

        int len = height.length;
        int start = 0, end = len - 1;
        int res = 0;
        while (start < end) {
            int h = Math.min(height[start], height[end]);
            res = Math.max(res, h * (end - start));
            //谁小谁往前移动
            if (height[start] < height[end]) {
                ++start;
            } else {
                --end;
            }
        }
        return res;
    }
}
