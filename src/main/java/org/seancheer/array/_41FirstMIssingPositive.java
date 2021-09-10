package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 * @author: seancheer
 * @date: 2021/8/28
 **/
public class _41FirstMIssingPositive extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _41FirstMIssingPositive();
        int[] nums = new int[]{1, 2, 0};
        var res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//3

        nums = new int[]{3, 4, -1, 1};
        res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//2

        nums = new int[]{7, 8, 9, 11, 12};
        res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//1

        nums = new int[]{-5};
        res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//1

        nums = new int[]{2};
        res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//1

        nums = new int[]{999,500,1};
        res = obj.firstMissingPositive(nums);
        System.out.println("res:" + res);//2
    }

    /**
     * 解题思路：题目的难点在于使用O(n)的时间复杂度和O(1)的空间复杂度，思路如下：
     * 1 我们每次都把正数放在其应该放的位置，比如1，我们把它放在index0的位置，2放在index1，其他同理
     * 2 因为数组有可能为负值和0，也有可能大于数组的长度，因此我们一旦遇到这些数，直接跳过即可，不予处理
     * 3 等处理完数组之后，我们从头开始遍历数组，一旦发现数组的index i位置上放的不是i+1，那么i+1就是最终的结果
     * 其实基本思路就是一个萝卜一个坑的思路，发现坑的位置是别人，那说明这个缺少这个坑该有的数字
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 1;
        }
        //每次都将数字放在其对应的index上面，会有三种情况：
        // 1 如果要放的index上已经是正确的值了，那么跳过当前值（说明出现了重复）
        // 2 如果发现为非正值，跳过
        // 3 如果发现当前值比数组还要大，跳过
        int len = nums.length;
        int i = 0;
        while (i < len) {
            //我们把nums[i]放在nums[i] - 1的index上，三种需要跳过的情况
            if (nums[i] <= 0 || nums[i] > len || nums[nums[i] - 1] == nums[i]) {
                ++i;
                continue;
            }

            //到了这里，把nums[i]交换到正确的位置上，注意，需要循环交换
            int tmp = nums[i];
            int idx = nums[i] - 1;
            //注意，不能直接写nums[nums[i] - 1]，因为这里会修改nums[i]，第三行就会问题
            nums[i] = nums[idx];
            nums[idx] = tmp;

            //当前位置大于0，继续交换，如果小于等于0，跳过该位置
            if (nums[i] <= 0) {
                ++i;
            }
        }

        //最后，遍历数组，寻找第一个缺失的正数
        for (i = 1; i <= len; ++i) {
            if (nums[i - 1] != i) {
                return i;
            }
        }
        return i;
    }
}
