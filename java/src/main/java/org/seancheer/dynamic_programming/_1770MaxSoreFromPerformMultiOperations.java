package org.seancheer.dynamic_programming;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 * <p>
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 * <p>
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 * <p>
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 *
 * @author: seancheer
 * @date: 2021/7/19
 **/
public class _1770MaxSoreFromPerformMultiOperations {
    public static void main(String[] args) {
        var obj = new _1770MaxSoreFromPerformMultiOperations();
        int[] nums = new int[]{1, 2, 3};
        int[] multipliers = new int[]{3, 2, 1};
        var res = obj.maximumScore(nums, multipliers);
        System.out.println("res: " + res);  //14

        nums = new int[]{-5, -3, -3, -2, 7, 1};
        multipliers = new int[]{-10, -5, 3, 4, 6};
        res = obj.maximumScore(nums, multipliers);
        System.out.println("res: " + res);  //102
    }

    /**
     * 解题思路：典型的动态规划问题
     * 使用带有memo功能的方式来求解，详细思路请看helper函数；
     *
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        if (null == nums || nums.length == 0 || null == multipliers || multipliers.length == 0) {
            return 0;
        }

        int n = nums.length;
        int m = multipliers.length;
        ///注意这里，必须用m，不能用n，会导致memory limit exceeded问题，之所以这里可以用m，是最多只会算到m的位置
        Integer[][] memo = new Integer[m][m];

        return helper(nums, multipliers, 0, 0, memo);
    }

    /**
     * helper函数，用来递归求解:
     * memo[left][level]表示处在nums的第left个位置且当前multiplier使用第level个数的时候的最大值，不断加深该level，递归查找
     * 目标值。
     * @param nums
     * @param multipliers
     * @param left
     * @param level
     * @return
     */
    private int helper(int[] nums, int[] multipliers, int left, int level, Integer[][] memo) {
        if (level == multipliers.length) {
            return 0;
        }

        int numsLen = nums.length;
        if (memo[left][level] != null) {
            return memo[left][level];
        }

        //使用左边的值
        int leftSum = helper(nums, multipliers, left + 1, level + 1, memo) + nums[left] * multipliers[level];
        //使用右边的值，使用numsLen - (level - left) - 1可以找到右边边界的值。
        int rightSum = helper(nums, multipliers, left, level + 1, memo) + nums[numsLen - (level - left) - 1] * multipliers[level];
        int sum = Math.max(leftSum, rightSum);
        memo[left][level] = sum;
        return sum;
    }
}
