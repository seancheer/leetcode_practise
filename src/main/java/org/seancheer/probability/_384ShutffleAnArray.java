package org.seancheer.probability;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 * // Any permutation of [1,2,3] must be equally likely to be returned.
 * // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 5 * 104 calls in total will be made to reset and shuffle.
 * 设计算法打乱一个数组，属于概率论的范畴
 *
 * @author: seancheer
 * @date: 2021/9/1
 **/
public class _384ShutffleAnArray extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Solution obj = new Solution(nums);
        int[] res = obj.reset();
        System.out.println("res:" + Arrays.toString(res));
        res = obj.shuffle();
        System.out.println("res:" + Arrays.toString(res));
    }

    /**
     * 解题思路：
     */
    static class Solution {
        private Random random;
        private int[] originNums;

        public Solution(int[] nums) {
            //将原始的数组拷贝一份
            this.originNums = Arrays.copyOf(nums, nums.length);
            this.random = new Random();
        }

        /**
         * Resets the array to its original configuration and return it.
         * 恢复原状并返回
         */
        public int[] reset() {
            return this.originNums;
        }

        /**
         * Returns a random shuffling of the array.
         * 解题思路：如何保证每一个位置出现的概率都一样呢？
         * 我们每次都rand[0,n)随机选一个idx，然后将其与n - 1位置的数字交换
         * 然后rand[0, n - 1)随机选一个idx，将其与n - 2位置的数字交换
         * 然后rand[0, n - 2)随机选一个idx，将其与n - 3位置的数字交换
         * 以此类推，那么这个是如何保证任意一个数字在任意一个位置出现的概率都相等呢？证明如下：
         * 1 很明显，任意一个数字num出现在最后一个位置n - 1的概率为 1/n
         * 2 任意一个数字num出现在n - 2的概率为：首先n - 1选择其他数字的概率为(n - 1)/n，然后到了 n - 2位置了，选择num的概率为1 / (n - 1)，
         *   这是一个条件概率，相乘即可，得到1/n
         * 3 其他也是同理
         * 所以任意一个数字num出现在任意位置的概率为1/n
         * 请参考  https://www.cnblogs.com/1203ljh/p/4734514.html
         */
        public int[] shuffle() {
            //我们使用初始的数组位置随机生成
            int bound = originNums.length;
            int[] nums = originNums.clone();
            while (bound > 1) {
                int idx = random.nextInt(bound);
                //将已经选定好的值放在数组末尾，然后减少bound，继续循环
                int tmp = nums[idx];
                nums[idx] = nums[bound - 1];
                nums[bound - 1] = tmp;
                --bound;
            }
            return nums;
        }
    }
}
