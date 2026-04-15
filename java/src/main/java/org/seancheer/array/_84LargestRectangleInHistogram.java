package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 * 求直方图里面的最大面积
 *
 * @author: seancheer
 * @date: 2021/9/8
 **/
public class _84LargestRectangleInHistogram extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _84LargestRectangleInHistogram();
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        var res = obj.largestRectangleArea(heights);
        var res2 = obj.largestRectangleArea2(heights);
        var res3 = obj.largestRectangleArea3(heights);
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3); // 10

        heights = new int[]{2, 4};
        res = obj.largestRectangleArea(heights);
        res2 = obj.largestRectangleArea2(heights);
        res3 = obj.largestRectangleArea3(heights);
        System.out.println("res:" + res + "  res2:" + res2 + "   res3:" + res3); // 4
    }


    /**
     * 解题思路：思路其实很简单，以任意一个位置i开始，分别向左边和右边进行遍历，遇到比heights[i]小的则停止，
     * 假设左边和右边的位置分别为left和right，那么此时的面积为area = heights[i] * (right - left - 1);（left和right
     * 的位置刚好比heights[i]小），对每一个位置i都进行这样的算法，最终就能得到正确的答案
     * 但是这个办法的时间复杂度为O(n^2)，不是最优解，会TLE，且看解法2对该办法的优化
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int res = heights[0];
        int len = heights.length;
        for (int i = 0; i < len; ++i) {
            int left = i;
            int right = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                --left;
            }

            while (right < len && heights[right] >= heights[i]) {
                ++right;
            }

            res = Math.max(res, heights[i] * (right - left - 1));
        }
        return res;
    }


    /**
     * 解题思路：解法1的优化，问题的关键在于怎么寻找任意位置i，heights[left] >= height[i]  height[right] >= height[i]
     * 最后一个left和right的位置；这里我们维护两个数组分别叫做lessThanLeft[len]和lessThanRight[len]，lessThanLeft[i]表示
     * 左边比heights[i]大于等于的边界idx，right同理，在遍历heights数组的时候，当我们遍历到了i + 1，
     * 如果发现heights[i + 1] <= heights[i]，因为lessThanLeft[i]存储的是比heights[i]大于等于的边界，所以是不是意味着
     * lessThanLeft[i]的边界就是lessThanLeft[i + 1]的边界？
     * 如果heights[i + 1] > heights[i]，那么左边的边界就在index i的位置
     * right同理，这样子就解决了确定左边和右边边界的问题。
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int len = heights.length;
        //存储的是index
        int[] lessThanLeft = new int[len];
        int[] lessThanRight = new int[len];
        //给边界赋值
        lessThanLeft[0] = -1;//因为index0没有左边
        lessThanRight[len - 1] = len; //因为index(len - 1)没有右边
        for (int i = 1; i < len; ++i) {
            int p = i - 1;
            //之所以用循环是因为lessThanLeft[i - 1]的位置刚好比height[i - 1]小，但是因为height[i - 1] >= height[i]
            //所以也就意味着height[lessThanLeft[i - 1]]的值可能比height[i]还要大，因此，需要循环往前判断（注意，这也是lessThanLeft
            //存储第一个不满足要求条件index而不是最后一个满足条件index的原因，这里可以简化逻辑）
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessThanLeft[p];
            }
            lessThanLeft[i] = p;
        }

        //右边和上面的同理
        for (int i = len - 2; i >= 0; --i) {
            //因为最右边没有右邻居，因此这里从len - 2开始
            int p = i + 1;
            //注意，这里p是往数组末端移动的，所以边界变成了len
            while (p < len && heights[p] >= heights[i]) {
                p = lessThanRight[p];
            }
            lessThanRight[i] = p;
        }

        //万事俱备，接下来开始解法1的逻辑
        int res = heights[0];
        for (int i = 0; i < len; ++i) {
            res = Math.max(res, (lessThanRight[i] - lessThanLeft[i] - 1) * heights[i]);
        }
        return res;
    }


    /**
     * 解题思路：解法3使用栈的方式来解决这个问题，我们使用栈存储当前直方图的index，按照从高度小到大的顺序，
     * 同时也就意味着如果height[i]的高度比stack顶的高度小，那么就pop掉栈顶，直到栈顶的idx位置的height[stack.top()] <= height[i]，
     * 其实很明显了，栈顶存储的idx对应的高度比height[i]要大，那也就意味着以height[stack.top()]为中心的有边界就在height[i]的位置，
     * 而左边界，我们通过不断的pop栈顶（条件为while(height[stack.top()]) > height[i]），这样子就能保持任意height[stack.top()]的
     * 右边界始终为height[i]，pop的过程中计算面积即可
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        if (null == heights || heights.length == 0) {
            return 0;
        }

        int res = heights[0];
        //按照高度从小到大存储index
        Deque<Integer> stack = new LinkedList<>();
        //这里有个小trick，为了处理边界情况，我们给stack提前放置一个-1处理只有一个height的情况
        stack.push(-1);
        int i = 0;
        for (; i < heights.length; ++i) {
            int topPos = stack.peek();
            //说明heights[topPos]的右边界就在heights[i]的位置
            while (-1 != topPos && heights[topPos] > heights[i]) {
                stack.pop();
                //计算这一段的面积，注意，这里之所以计算边界的时候没有用旧的topPos，是为了处理边界情况
                res = Math.max(res, (i - stack.peek() - 1) * heights[topPos]);
                topPos = stack.peek();
            }
            //到位置了，放入当前index
            stack.push(i);
        }

        //注意，如果是递增序列，会导致while无法进入，所以最后，我们还需要处理递增情况
        while (!stack.isEmpty() && -1 != stack.peek()) {
            int curIdx = stack.peek();
            stack.pop();
            res = Math.max(res, (i - stack.peek() - 1) * heights[curIdx]);
        }

        return res;
    }
}
