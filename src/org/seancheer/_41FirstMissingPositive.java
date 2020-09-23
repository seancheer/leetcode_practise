package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * 41. First Missing Positive
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class _41FirstMissingPositive extends LeetCodeParent {

    /**
     * main func
     * @param args
     */
    public static void main(String[] args){
        _41FirstMissingPositive obj = new _41FirstMissingPositive();
        int[] nums = new int[]{1};
        var res = obj.firstMissingPositive(nums);
        System.out.println("res: " + res);

        nums = new int[]{2147483647,2147483646,2147483645,3,2,1,-1,0,-2147483648};
        res = obj.firstMissingPositive(nums);
        System.out.println("res: " + res);

        nums = new int[]{1,2,0};
        res = obj.firstMissingPositive(nums);
        System.out.println("res: " + res);

        nums = new int[]{3,4,-1,1};
        res = obj.firstMissingPositive(nums);
        System.out.println("res: " + res);

        nums = new int[]{7,8,9,11,12};
        res = obj.firstMissingPositive(nums);
        System.out.println("res: " + res);
    }

    /**
     * 本题的难点在于要求时间复杂度为O(n)，空间复杂度要求常数，也就是说， 基本就是要求在数组本身上面进行修改。
     * 解题思路：
     * 将数组中的所有正数，放在nums[i] - 1的位置上，然后再从数组前面开始遍历，发现第一个nums[i] != i + 1（也就是对应位置
     * 上的值不等于该位置的值），就是我们需要的答案。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (null == nums || nums.length == 0){
            return 1;
        }

        int len = nums.length;
        for(int i = 0;i < len;++i){
            //注意，这里需要一直交换，一直到该位置上到达正确的值，只交换一次该位置上可能就是错误的值
            //注意：这里最好不要判断nums[i] - 1 >= 0，该题有个用例，nums[i]为Integer的最小值，这种情况下可能会导致
            //整数直接翻转
            while(nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]){
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            }
        }

        for(int i = 0;i < len;++i){
            if (nums[i] != i + 1){
                return i + 1;
            }
        }

        //这种情况是为了考虑该数组一直都是递增的正数
        return len + 1;
    }
}
