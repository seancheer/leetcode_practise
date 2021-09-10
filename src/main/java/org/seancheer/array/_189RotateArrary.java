package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * 有多种解法，要求在O(1)的空间复杂度解决该问题
 *
 * @author: seancheer
 * @date: 2021/9/7
 **/
public class _189RotateArrary extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _189RotateArrary();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = nums.clone();
        int k = 3;
        obj.rotate(nums, k);
        obj.rotate2(nums2, k);
        System.out.println("res:" + Arrays.toString(nums) + "  res2:" + Arrays.toString(nums2)); // [5,6,7,1,2,3,4]

        nums = new int[]{-1, -100, 3, 99};
        nums2 = nums.clone();
        k = 2;
        obj.rotate(nums, k);
        obj.rotate2(nums2, k);
        System.out.println("res:" + Arrays.toString(nums) + "  res2:" + Arrays.toString(nums2)); // [3,99,-1,-100]
    }

    /**
     * 解题思路：最简单直观的做法，使用O(n)的空间复杂度和O(n)的时间复杂度
     * 请看解法2，简单容易理解还不容易出错
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k <= 0) {
            return;
        }

        int len = nums.length;
        k %= len;
        //不需要进行旋转
        if (k == 0) {
            return;
        }

        int sz = len - k;
        int[] prevHalf = new int[sz];
        for (int i = 0; i < sz; ++i) {
            prevHalf[i] = nums[i];
        }

        //把后半部分摞动到前半部分
        int j = 0;
        for (int i = sz; i < len; ++i) {
            nums[j++] = nums[i];
        }

        int i = 0;
        //把前半部分放在正确的位置上
        for (; j < len; ++j) {
            nums[j] = prevHalf[i++];
        }
    }

    /**
     * 解题思路：高级一点的做法，使用O(1)的空间复杂度
     * 分为三部：
     * 1 首先把整个数组都交换一遍，这样子后半部分就在前面，前半部分就在后面
     * 2 交换[0, k - 1]的位置，保证前半部分（以前的后半部分）有序
     * 3 交换[k, nums.length - 1]位置，保证后半部分（以前的前半部分）有序
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k <= 0) {
            return;
        }

        int len = nums.length;
        k %= len;
        //不需要进行旋转
        if (k == 0) {
            return;
        }
        //接下来交换三次就能达到目的
        //1 整个翻转
        rotateHelper(nums, 0, len - 1);
        //2 保证前半部分有序（以前的后半部分）
        rotateHelper(nums, 0, k - 1);
        //3 保证后半部分有序（以前的前半部分）
        rotateHelper(nums, k, len - 1);
    }

    private void rotateHelper(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            ++start;
            --end;
        }
    }
}
