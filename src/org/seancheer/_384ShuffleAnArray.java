package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;
import java.util.Random;

/**
 * 384. Shuffle an Array
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
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
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must be equally likely to be returned. Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 * @author: seancheer
 * @date: 2021/2/7
 **/
public class _384ShuffleAnArray extends LeetCodeParent {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        _384ShuffleAnArray.Solution obj = new _384ShuffleAnArray.Solution(arr);
        int[] res = obj.shuffle();
        System.out.println("res:" + Arrays.toString(res));
    }

    static class Solution {
        private int[] source;
        private Random random = new Random();

        public Solution(int[] nums) {
            this.source = nums;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return source;
        }

        /**
         * Returns a random shuffling of the array.
         * 这个是经典的洗牌算法，具体的证明可以看这里：https://www.cnblogs.com/1203ljh/p/4734514.html
         * 该算法最后得结果是任何一个数在任意为止得概率都为1/n，满足题目要求
         */
        public int[] shuffle() {
            int[] res = source.clone();
            for(int i = res.length - 1;i > 0;--i){
                int idx = random.nextInt(i + 1);
                int tmp = res[idx];
                res[idx] = res[i];
                res[i] = tmp;
            }
            return res;
        }
    }
}
