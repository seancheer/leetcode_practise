package org.seancheer.greedy;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * @author: seancheer
 * @date: 2021/7/29
 **/
public class _45JumpGame_2 {
    public static void main(String[] args) {
        _45JumpGame_2 obj = new _45JumpGame_2();
        int[] nums = new int[]{2, 3, 1, 1, 4};
        var res = obj.jump(nums);
        var res2 = obj.jump(nums);
        System.out.println("res:" + res + "   res2:" + res2); //2

        nums = new int[]{2, 3, 0, 1, 4};
        res = obj.jump(nums);
        res2 = obj.jump(nums);
        System.out.println("res:" + res + "   res2:" + res2); //2

        nums = new int[]{2, 1};
        res = obj.jump(nums);
        res2 = obj.jump(nums);
        System.out.println("res:" + res + "   res2:" + res2); //1
    }

    /**
     * 解题思路：该题保证了一定会跳到末尾，求解跳到末尾时候的最小step
     * 和JumpGame其实很类似，唯一的区别就是我们需要每一次的起跳位置i，和其所能跳的最远位置j；在(i,j]段内，我们需要选择一个点
     * 作为落脚点，怎么选择呢？当然是选择能跳到最远位置的点作为新的起跳点，这个时候跳数+1，按照这样的贪心思路，我们可以得到最优解
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int maxDis = nums[0];
        //表示从该位置起跳，所能走到的最远位置
        int nextEnd = maxDis;
        nextEnd = Math.min(nums.length - 1, nextEnd);
        for (int i = 1; i < nums.length; ++i) {
            maxDis = Math.max(maxDis, i + nums[i]);
            //已经到了上一个位置所能走到的最远位置了，这个时候，我们需要在这一区间段内选择一个能跳到最远的起跳位置（贪心）作为新的
            //起跳点（也就是说，直接跳到该位置），所以跳数res++
            if (i == nextEnd) {
                ++res;
                nextEnd = maxDis;
                //避免跳到外面去，导致最后一跳没有计算
                nextEnd = Math.min(nums.length - 1, nextEnd);
            }
        }

        return res;
    }

}
