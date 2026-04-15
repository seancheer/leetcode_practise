package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 要求使用常数空间复杂度
 *
 * @author: seancheer
 * @date: 2021/8/28
 **/
public class _31NextPermutation extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _31NextPermutation();
        int[] nums = new int[]{1, 2, 3};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums));//[1,3,2]

        nums = new int[]{3, 2, 1};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums)); //[1,2,3]

        nums = new int[]{1, 1, 5};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums)); //[1,5,1]

        nums = new int[]{1};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums));//[1]

        nums = new int[]{4,5,6,7,5,7};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums));//[4,5,6,7,7,5]

        nums = new int[]{1,2,3,5,4,1};
        obj.nextPermutation(nums);
        System.out.println("res:" + Arrays.toString(nums));//[1,2,4,1,3,5]
    }

    /**
     * 解题思路：倒序查找
     * 1 从末尾开始指针i，查找第一个非递增序列的位置
     * 2 另外一个指针j从末尾开始，查找第一个比nums[i]大的位置，然后交换nums[i]和nums[j]
     * 3 最后对[i + 1, end)进行一个递增排序即可
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        //寻找非递增序列出现的位置，也有可能整个序列从末尾到开始都是递增序列（正向递减）
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }

        //说明找到了该位置，开始在(i, end)位置寻找一个刚比nums[i]大且最接近的值（因为i之后都是递增序列，nums[i] < nums[i + 1]，
        //所以，必然存在比nums[i]大的值，因为[i + 1, end)都是递减序列，所以我们只需关心比nums[i]大的值即可
        if (i >= 0){
            int k = i + 1;
            for(int j = i + 1;j < nums.length && nums[j] > nums[i];++j){
                k = j;
            }
            //找到了比nums[i]大且最接近的一个，交换
            int tmp = nums[i];
            nums[i] = nums[k];
            nums[k] = tmp;
        }

        //最后对i之后的数组进行一个从小到大的排序，这样子就能保证该组合刚好比nums这个组合大
        Arrays.sort(nums, i + 1, nums.length);
    }
}
