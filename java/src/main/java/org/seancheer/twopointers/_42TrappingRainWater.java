package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * 这道题的{@link _11ContainerWithMostWater}不同的是水不能穿过柱子，有柱子的地方是无法容纳水的
 *
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _42TrappingRainWater {

    public static void main(String[] args) {
        var obj = new _42TrappingRainWater();
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        var res = obj.trap(nums);
        var res2 = obj.trap2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//6


        nums = new int[]{4, 2, 0, 3, 2, 5};
        res = obj.trap(nums);
        res2 = obj.trap2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//9

        nums = new int[]{4, 1, 1, 1, 1, 2};
        res = obj.trap(nums);
        res2 = obj.trap2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//9

    }

    /**
     * 解题思路：首先找到最高的位置坐标，假设为index max，然后从开头start和末尾end分别向
     * i靠近，直到到达i为止；中间判断的方式如下：
     * 假设当前位置为start，我们一直往后遍历，直到出现第一个比start位置还高的位置j，就停下来，那么这一段如果中间部分为0，其蓄水量
     * 最大为：
     * height[start] * (j - start - 1)
     * 因为中间肯定也有高度，所以我们直接减去中间的高度，就是最终的蓄水量：height[start] * (j - start - 1) - sum(i + 1, j - 1);
     * <p>
     * 从末尾开始的方式也是一样的
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (null == height || height.length <= 2) {
            return 0;
        }

        int len = height.length;
        int maxPos = 0;
        int maxVal = height[0];
        for (int i = 1; i < len; ++i) {
            if (height[i] > maxVal) {
                maxVal = height[i];
                maxPos = i;
            }
        }

        //接下来从头部和尾部同时向maxPos靠近
        int start = 0, end = len - 1;
        //排除开头0的情况
        while (start <= maxPos && height[start] == 0) {
            ++start;
        }
        //跳过末尾0的情况
        while (end >= maxPos && height[end] == 0) {
            --end;
        }

        int res = 0;
        while (start <= maxPos || end >= maxPos) {
            //查找第一个比start还大的位置，并记录中间的水位
            int tmp = 0;
            int i = start + 1;
            while (i <= maxPos && height[i] <= height[start]) {
                tmp += (height[i]);
                ++i;
            }
            //计算这过程里面的水位
            res += ((i - start - 1) * height[start] - tmp);
            start = i;

            i = end - 1;
            tmp = 0;
            while (i >= maxPos && height[i] <= height[end]) {
                tmp += (height[i]);
                --i;
            }
            res += ((end - i - 1) * height[end] - tmp);
            end = i;
        }
        return res;
    }


    /**
     * 解题思路：更加巧妙的双指针解法
     * 使用两个指针，一个从头开始left，一个从末尾开始right
     * 1 如果height[left] < height[right]，那么移动left，说明[left, right]之间我们一定可以容纳水，移动的过程中我们记录left
     * 的height最大值，因为这意味着left之前的所有值都低于height[right]，也就意味着我们可以放心的存储maxLeft - height[i]的水量（有
     * 更大的height[right]作为屏障，所以一定能存储下来，maxLeft - height[i]也很好理解，比maxLeft矮的需要减掉其高度才是真正的蓄水量
     * 2 如果height[left] > height[right]，同样的，我们记录maxRight，所有的height[right]都小于height[left]，使用maxRight - height[i]，
     * 就可以得到真正的蓄水位
     * 时间复杂度和解法1一样，但是这个思路更加巧妙
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (null == height || height.length <= 2) {
            return 0;
        }

        int len = height.length;
        int left = 0, maxLeft = 0;
        int right = len - 1, maxRight = 0;
        int res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                //发现了新的高度，可以蓄更多的水
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                } else {
                    res += (maxLeft - height[left]);
                }
                ++left;
            } else {
                if (height[right] > maxRight) {
                    maxRight = height[right];
                } else {
                    res += (maxRight - height[right]);
                }
                --right;
            }
        }
        return res;
    }
}
