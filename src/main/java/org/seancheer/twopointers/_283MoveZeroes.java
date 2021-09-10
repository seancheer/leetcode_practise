package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * 要求不能copy该数组，只能就地完成目标
 *
 * @author: seancheer
 * @date: 2021/7/27
 **/
public class _283MoveZeroes {
    public static void main(String[] args) {
        _283MoveZeroes obj = new _283MoveZeroes();
        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2)); //1,3,12,0,0

        nums = new int[]{0};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));  //0

        nums = new int[]{0, 0, 0, 0, 0};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{1, 1, 1, 1, 1};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));

        nums = new int[]{0, 0, 1, 1, 1};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2)); //1, 1, 1, 0, 0

        nums = new int[]{1, 1, 1, 0, 0};
        nums2 = Arrays.copyOf(nums, nums.length);
        obj.moveZeroes(nums);
        obj.moveZeroes2(nums2);
        System.out.println("res:" + Arrays.toString(nums) + "   res2:" + Arrays.toString(nums2));  //1, 1, 1, 0, 0
    }

    /**
     * 解题思路：双指针问题
     * 把0和第一个不是0的数字交换即可，一直重复下去
     * 思路2更好，也更容易理解
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        int i = 0, j = 0;
        int len = nums.length;
        while (i < len && j < len) {
            while (i < len && nums[i] != 0) {
                ++i;
            }

            //因为i走过的位置都不可能等于0，所以j要从i的后面开始查找不等于0的值并把它移动过来
            j = i + 1;
            while (j < len && nums[j] == 0) {
                ++j;
            }

            if (i < len && j < len) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            ++i;
            ++j;
        }
    }

    /**
     * 解题思路：把遇到的不为0的数字从头开始放，然后剩下的部分填充0即可，更好的做法
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        int i = 0;
        for (int item : nums) {
            if (item != 0) {
                nums[i++] = item;
            }
        }

        //剩下的部分记得填充0
        for (; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }
}
