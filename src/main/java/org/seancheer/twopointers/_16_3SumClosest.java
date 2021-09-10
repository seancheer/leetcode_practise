package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 * 确定只有一个答案
 *
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _16_3SumClosest {

    public static void main(String[] args) {
        var obj = new _16_3SumClosest();
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        var res = obj.threeSumClosest(nums, target);
        var res2 = obj.threeSumClosest2(nums, target);
        System.out.println("res:" + res + "   res2:" + res2);//2


        nums = new int[]{0, 0, 0};
        target = 1;
        res = obj.threeSumClosest(nums, target);
        res2 = obj.threeSumClosest2(nums, target);
        System.out.println("res:" + res + "   res2:" + res2);//0

    }

    /**
     * 解题思路：类似于{@link _15_3Sum}，首先进行排序，然后确定一个值，从两端开始尝试贴近目标
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int len = nums.length;
        int curDis = Integer.MAX_VALUE;
        int resSum = 0;
        for (int i = 0; i < len - 2; ++i) {
            //和上一次相同的，就没必要继续测试了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int dis = Math.abs(sum - target);
                //记录距离target最小的位置
                if (dis < curDis) {
                    curDis = dis;
                    resSum = sum;
                }

                //不可能比0再小了
                if (dis == 0) {
                    return target;
                } else if (sum > target) {
                    //需要把right往回摞动
                    --right;
                    while (left < right && nums[right] == nums[right + 1]) {
                        --right;
                    }
                } else {
                    //需要增大left来增大sum
                    ++left;
                    while(left < right && nums[left] == nums[left - 1]){
                        ++left;
                    }
                }
            }
        }
        return resSum;
    }


    /**
     * 解题思路：
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] nums, int target) {
        return 0;
    }
}
