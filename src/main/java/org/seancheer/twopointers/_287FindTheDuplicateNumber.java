package org.seancheer.twopointers;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 * <p>
 * Input: nums = [1,1]
 * Output: 1
 * Example 4:
 * <p>
 * Input: nums = [1,1,2]
 * Output: 1
 *
 * @author: seancheer
 * @date: 2021/7/20
 **/
public class _287FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        _287FindTheDuplicateNumber obj = new _287FindTheDuplicateNumber();
        int res = obj.findDuplicate(nums);
        System.out.println("res:" + res); //2

        nums = new int[]{3, 1, 3, 4, 2};
        res = obj.findDuplicate(nums);
        System.out.println("res:" + res); //3

        nums = new int[]{1, 1};
        res = obj.findDuplicate(nums);
        System.out.println("res:" + res); //1

        nums = new int[]{1, 1, 2};
        res = obj.findDuplicate(nums);
        System.out.println("res:" + res);//1
    }

    /**
     * 解题思路：注意，题目有个限定条件，数字的范围都在[1,n]，这就给使用常数空间提供了办法。
     * 将对应数字i放在其对应的位置i - 1上，判断要交换的值
     * 是否相等，如果相等，则发现了最终的答案，如果不相等，一直进行交换，直到对应位置交换上了正确的值。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; ++i) {
            int j = nums[i] - 1;
            if (i == j) {
                continue;
            }

            if (nums[i] == nums[j]) {
                return nums[i];
            }

            //直到把i位置的值换到正确的值，才能继续往前走
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            --i;
        }
        return -1;
    }
}
