package org.seancheer;

import java.util.ArrayList;
import java.util.List;

/**
 * 55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 */
public class _55JumpGame {
    public static void main(String[] args) {
        _55JumpGame obj = new _55JumpGame();
        boolean res = obj.canJump(new int[]{2, 0});
        boolean res2 = obj.canJump2(new int[]{2,0});
        System.out.println("res:" + res + "  res2:" + res2);

        res = obj.canJump(new int[]{2, 3, 1, 1, 4});
        res2 = obj.canJump2(new int[]{2, 3, 1, 1, 4});
        System.out.println("res:" + res + "  res2:" + res2);


        res = obj.canJump(new int[]{3, 2, 1, 0, 4});
        res2 = obj.canJump2(new int[]{3, 2, 1, 0, 4});
        System.out.println("res:" + res + "  res2:" + res2);
    }


    /**
     * 主方法：判断是否可达
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }

        int size = nums.length;
        Boolean[] status = new Boolean[size];
        int idx = 0;
        for (int item : nums) {
            if (0 == item) {
                status[idx++] = Boolean.FALSE;
            } else {
                status[idx++] = null;
            }
        }

        //0 could true if 0 is at the end of the nums;
        status[size - 1] = null;
        return jumpInternal(nums, 0, status);
    }

    /**
     * recursive
     *
     * @param nums
     * @param pos
     * @param status
     * @return
     */
    public boolean jumpInternal(int[] nums, int pos, Boolean[] status) {
        int size = nums.length;
        if (pos >= size) {
            return false;
        }

        if (pos == size - 1) {
            return true;
        }

        if (null != status[pos]) {
            return status[pos];
        }

        int cur = nums[pos];
        if (0 == cur) {
            return false;
        }

        status[pos] = Boolean.FALSE;
        for (int step = 1; step <= cur; ++step) {
            if (jumpInternal(nums, pos + step, status)) {
                status[pos] = Boolean.TRUE;
                return true;
            }
        }

        return false;
    }

    /**
     * 最简单的解决方式：本质上是一个bfs，遍历所有可能的路径
     * 每次开头进行检查，如果发现当前已经走到了maxStep的前面，那么说明此路径不可达。
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return false;
        }

        int maxStep = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > maxStep) {
                return false;
            }

            int pos = nums[i] + i;
            maxStep = Math.max(maxStep, pos);
        }

        return true;
    }
}
