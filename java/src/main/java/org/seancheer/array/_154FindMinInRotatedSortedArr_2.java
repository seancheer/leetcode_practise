package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums is sorted and rotated between 1 and n times.
 * <p>
 * <p>
 * Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 * 和{@link _153FindMinInRotatedSortedArr}的区别在于，这里可能会存在重复值，同时，这道题有个自创的变形题，返回最小值的index，
 * 且看
 *
 * @author: seancheer
 * @date: 2021/9/6
 **/
public class _154FindMinInRotatedSortedArr_2 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _154FindMinInRotatedSortedArr_2();
        int[] nums = new int[]{1, 3, 5};
        var res = obj.findMin(nums);
        System.out.println("res:" + res); // 1

        nums = new int[]{2, 2, 2, 0, 1};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 0

        nums = new int[]{4, 5, 6, 7, 0, 1, 4};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 0

        nums = new int[]{2, 2, 2};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 2

        nums = new int[]{2, 2, 2, 1};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 1

        nums = new int[]{5, 1, 2, 5};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 1

        nums = new int[]{5, 1, 5, 5, 5};
        res = obj.findMin(nums);
        System.out.println("res:" + res); // 1
    }

    /**
     * 解题思路：和153的区别在于数组可能包含有重复值，时间复杂度还是要求尽可能的低
     * 注意，一个有序的数组是不可能旋转成为倒序的，这点很重要
     * 首先，因为有可能会出现重复的值，所以无论如何之前的短路判断是无法在使用了，所以，我们这里做一个转变，mid不在和start比较了，
     * 1 如果发现mid比end大，那么很明显，mid在前半部分，start = mid + 1
     * 2 如果mid比end小，mid在后半部分，end = mid，不减1是为了让start最终在正确的位置上
     * 3 如果mid和end相等，此时不知道在前半部分还是后半部分，只需要把end -= 1，这样子，最终end一定会停留在最小的位置上，到时候就会进入到
     *   1的情况
     *
     * 注意，这道题如果在最坏的情况下，时间复杂度会是O(n)，比如[2,1,2,2,2,2,2,2,2]，将不得不遍历大部分数组
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        //只有一个元素，或者本身就是有序的情况，注意不能相等，因为有可能会重复，比如[2,3,4,5,1,2]
        if (nums.length == 1 || nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }


        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            //由于有可能会相等，所以之前的短路判断不能在用了，比如[2,3,4,5,1,2]这种就会出问题
//            if(nums[start] < nums[end]){
//                return nums[start];
//            }

            int mid = (start + end) >> 1;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                --end;
            }
        }
        //这种是全相等的情况
        return nums[start];
    }

}
