package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * 33. Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 注意：此题坑爹的一点是因为数组在某个点被旋转，所以不可能是反序的，这点需要注意，但是恶心的是有可能会是按照顺序的方式。
 * 比如[1],[1,3], [1,2,3]这种情况，不需要考虑完全反序的情况，别想太多！！！！
 */
public class _33SearchInRotatedArr extends LeetCodeParent {
    public static void main(String[] args) {
        testSearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 0); //4
        testSearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 3); //-1
        testSearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 2); //6
        testSearch(new int[]{4, 5, 6, 7, 0, 1, 2}, 4); //0
        testSearch(new int[]{1}, 4); //-1
        testSearch(new int[]{1}, 1); //0
        testSearch(new int[]{1, 3}, 3); //1

    }

    /**
     * driver test case
     *
     * @param nums
     * @param target
     */
    private static void testSearch(int[] nums, int target) {
        _33SearchInRotatedArr obj = new _33SearchInRotatedArr();
        var res = obj.search(nums, target);
        System.out.println("res:" + res);
    }

    /**
     * 解题思路：本题得难点在于时间复杂度要求是O(logn),所以不能用O(n)得方式
     * 1 先使用二分法找到pivot
     * 2 然后根据targe和初始位置得数的大小关系来决定二分查找的区间。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }

        if (nums[0] == target) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        //寻找pivot
        int pivot = 0;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else if (nums[mid] < nums[left]) {
                right = mid;
            }

            if (right - left <= 1) {
                pivot = left;
                break;
            }
        }

        if (target == nums[pivot]) {
            return pivot;
        }

        //in order.
        if (nums[0] < nums[nums.length - 1]) {
            left = 0;
            right = nums.length - 1;
        } else {
            if (target > nums[0]) {
                left = 0;
                right = pivot;
            } else {
                left = pivot + 1;
                right = nums.length - 1;
            }
        }

        //使用二分法进行查找
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
