package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
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
 * int val = ...; // Value to remove
 * int[] expectedNums = [...]; // The expected answer with correct length.
 * // It is sorted with no values equaling val.
 * <p>
 * int k = removeElement(nums, val); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * sort(nums, 0, k); // Sort the first k elements of nums
 * for (int i = 0; i < actualLength; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 * <p>
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
 * Note that the five elements can be returned in any order.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * 返回的前k个数字只要是对的就行，不需要一定是按照顺序返回的
 * @author: seancheer
 * @date: 2021/8/28
 **/
public class _27RemoveElement extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _27RemoveElement();
        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;
        var sz = obj.removeElement(nums, val);
        System.out.println(String.format("res: size[%d]  content:%s", sz, toStringArr(nums, sz)));

        nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        val = 2;
        sz = obj.removeElement(nums, val);
        System.out.println(String.format("res: size[%d]  content:%s", sz, toStringArr(nums, sz)));
    }

    /**
     * 解题思路：使用两个指针，slow和fast，均从头开始，fast碰到目标值跳过去，然后将fast的值
     * 直接赋值给slow，这样子最后的结果就是目标答案
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            while (fast < nums.length && nums[fast] == val) {
                ++fast;
            }
            if (fast < nums.length) {
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }
}
