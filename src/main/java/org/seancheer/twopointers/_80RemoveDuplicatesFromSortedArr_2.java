package org.seancheer.twopointers;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * <p>
 * int k = removeDuplicates(nums); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 * 数组已经排好序了，只需要保证每个数字最多出现2次就可以
 *
 * @author: seancheer
 * @date: 2021/8/15
 **/
public class _80RemoveDuplicatesFromSortedArr_2 {
    public static void main(String[] args) {
        var obj = new _80RemoveDuplicatesFromSortedArr_2();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        var res = obj.removeDuplicates(nums);
        var res2 = obj.removeDuplicates2(nums2);
        System.out.println("res:" + res + "   res2:" + res2);//5

        nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        nums2 = Arrays.copyOf(nums, nums.length);
        res = obj.removeDuplicates(nums);
        res2 = obj.removeDuplicates2(nums2);
        System.out.println("res:" + res + "   res2:" + res2);//7

        nums = new int[]{0, 2, 2, 2, 2, 2};
        nums2 = Arrays.copyOf(nums, nums.length);
        res = obj.removeDuplicates(nums);
        res2 = obj.removeDuplicates2(nums2);
        System.out.println("res:" + res + "   res2:" + res2);//3
    }

    /**
     * 解题思路：双指针问题，使用两个指针i和j，i = 1, j = 1
     * 1 j往前走，停下的条件为：如果count == 1，说明nums[i - 1]只出现了一次，因此j只需要碰到>=nums[i - 1]的就可以停下来；
     * 反之nums[i - 1]已经出现了2次，nums[j]必须>nums[i - 1]
     * 2 交换nums[i]和nums[j]，++j，检查nums[i]和nums[i - 1]是否相等，相等++count，不相等，count = 1，然后i = i + 1
     * 3 继续下一次的循环
     * 解法2思路更好，参考解法2
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (null == nums) {
            return 0;
        }

        int count = 1;
        int len = nums.length;
        int i = 1;
        int j = 1;
        while (j < len) {
            //count == 1说明nums[i - 1]还可以出现，此时这里偷个懒，不检测nums[j]，很简单，因为数组是排好序的，所以nums[j]必然>= nums[i]
            //出现了两次，不能在出现了，因此nums[j]必须大于nums[i]
            if (count == 2) {
                while (j < len && nums[j] <= nums[i - 1]) {
                    ++j;
                }
            }


            if (j == len) {
                break;
            }

            //交换i和j值
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            ++j;

            //相等，继续统计
            if (nums[i] == nums[i - 1]) {
                ++count;
                //不相等，重新开始统计
            } else {
                count = 1;
            }
            ++i;
        }

        return i;
    }


    /**
     * 解题思路：依旧双指针解决这个问题，不过该方法更加精巧也更加简单明了，直接看代码即可理解该思想
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (null == nums) {
            return 0;
        }

        int len = nums.length;
        int j = 0;
        for (int i = 0; i < len; ++i) {
            //两个一起往前走，只要发现nums[i]比nums[j - 2]还大，那么就可以放心的放在nums[j]的位置，很简单的方法
            //该解法可以拓展到任意最多重复k次的问题上
            if (j < 2 || nums[i] > nums[j - 2]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
