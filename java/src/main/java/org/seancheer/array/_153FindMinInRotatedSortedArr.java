package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 * <p>
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 * 注意，题目里面的数组保证唯一，不会出现重复值，注意，因为是对数组进行旋转，所以不可能会出现倒序的情况，因为一个有序的数组
 * 是无法旋转成为倒序的
 *
 * @author: seancheer
 * @date: 2021/9/6
 **/
public class _153FindMinInRotatedSortedArr extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _153FindMinInRotatedSortedArr();
        int[] nums = new int[]{3, 4, 5, 1, 2};
        var res = obj.findMin(nums);
        var res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2); // 1

        nums = new int[]{4, 5, 6, 0, 1, 2};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2); // 0


        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2);  // 0

        nums = new int[]{11, 13, 15, 17};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2);  // 11

        nums = new int[]{3, 1, 2};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2);  // 1

        nums = new int[]{5, 1, 2, 3, 4};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2);  // 1

        //非法的输入，有序的数组是不可能旋转成为倒序的
        nums = new int[]{5, 4, 3, 2, 1};
        res = obj.findMin(nums);
        res2 = obj.findMin2(nums);
        System.out.println("res:" + res + "    res2:" + res2);  // 1
    }

    /**
     * 解题思路：题目要求在log(n)的时间内解决问题
     * 很明显的做法就是用二分法，由于旋转后，意味着前半部分有序，后半部分也有序，
     * 同时前半部分的所有值都会大于后半部分，靠着这个特性，我们可以通过二分法的逻辑，很快的找到
     * 交界处，需要注意的是，因为正序和倒序是不满足该逻辑的，所以要额外判断
     * 这道题的关键在于代码实现问题，且看解法2，实现更加精巧，思路都是一样的
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int len = nums.length;
        //说明是有序的
        if (nums[0] < nums[len - 1]) {
            return nums[0];
            //倒序的情况
        } else if (nums[0] > nums[len - 1] && nums[len - 2] > nums[len - 1]) {
            return nums[len - 1];
        }
        //接下来处理最常见的情况
        int i = 0;
        int j = len - 1;
        int res = nums[0];
        while (i < j) {
            int mid = (i + j) >> 1;
            //因为mid有可能会是0
            if (nums[mid] >= nums[0]) {
                i = mid + 1;
                //保证了不会重复
            } else {
                //只有在这个分支才有可能出现结果
                res = Math.min(nums[mid], res);
                j = mid - 1;
            }
        }
        return Math.min(nums[i], res);
    }

    /**
     * 解题思路：题目要求在log(n)的时间内解决问题
     * 很明显的做法就是用二分法，但是这个代码更加精巧，如果发现mid比end要大，我们摞动start，否则，保持
     * end的位置不要变即可，中间我们使用短路判断，一旦发现nums[start] < nums[end]了，那么就意味着我们进入到后半部分了
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
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
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = (start + end) >> 1;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        //这种是全相等的情况
        return nums[start];
    }
}
