package org.seancheer;

/**
 * You are given an integer array nums of even length n and an integer limit. In one move, you can replace any integer from nums with another integer between 1 and limit, inclusive.
 * <p>
 * The array nums is complementary if for all indices i (0-indexed), nums[i] + nums[n - 1 - i] equals the same number. For example, the array [1,2,3,4] is complementary because for all indices i, nums[i] + nums[n - 1 - i] = 5.
 * <p>
 * Return the minimum number of moves required to make nums complementary.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,4,3], limit = 4
 * Output: 1
 * Explanation: In 1 move, you can change nums to [1,2,2,3] (underlined elements are changed).
 * nums[0] + nums[3] = 1 + 3 = 4.
 * nums[1] + nums[2] = 2 + 2 = 4.
 * nums[2] + nums[1] = 2 + 2 = 4.
 * nums[3] + nums[0] = 3 + 1 = 4.
 * Therefore, nums[i] + nums[n-1-i] = 4 for every i, so nums is complementary.
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,1], limit = 2
 * Output: 2
 * Explanation: In 2 moves, you can change nums to [2,2,2,2]. You cannot change any number to 3 since 3 > limit.
 * Example 3:
 * <p>
 * Input: nums = [1,2,1,2], limit = 2
 * Output: 0
 * Explanation: nums is already complementary.
 *
 * @author: seancheer
 * @date: 2021/3/10
 **/
public class _1674MinMoveToMakeArrComplementary {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 3};
        int limit = 4;
        var obj = new _1674MinMoveToMakeArrComplementary();
        int res = obj.minMoves(nums, limit);
        System.out.println("res:" + res);  //1

        nums = new int[]{1, 2, 2, 1};
        limit = 2;
        obj = new _1674MinMoveToMakeArrComplementary();
        res = obj.minMoves(nums, limit);
        System.out.println("res:" + res);  //2

        nums = new int[]{1, 2, 1, 2};
        limit = 2;
        obj = new _1674MinMoveToMakeArrComplementary();
        res = obj.minMoves(nums, limit);
        System.out.println("res:" + res);  //0
    }

    /**
     * 解题思路：这道题运用的是差分数组，根据题目的限制，因为nums[i] >= 1，所以两者的和最小为2，又因为limit >= nums[i]，
     * 所以两者的和最大只能到limit * 2，其实这里已经给了提示了，运用差分数组来求解。所以有时候看看题目限制还是非常有用的。
     * 假设当前为a = nums[i]和b = nums[sz - i - 1] = nums[j]，为了让a + b = T，T分为五种范围：
     * [2, min(a,b)]: 此时很明显，该范围内a和b都需要变化，需要2次操作，和为a，那么就意味着a和b都得变化，最小值不能为0
     * **************
     * [min(a,b) + 1, max(a,b))：此时只需要变大的值即可， 需要1次操作
     * [max(a,b) + 1, a + b)：此时只需要变小的值即可，需要1次操作，该区域可以和上一个合并，因此正式区域为：
     * [min(a,b) + 1, a + b)：只需要1次操作
     * **************
     * [a+b]：此时什么都不需要做，需要0次操作
     * [a + b + 1, max(a,b) + limit]：此时只需要变较小者即可，需要1次操作
     * [max(a,b) + limit + 1, limit * 2]：此时需要两个都变，需要2次操作
     *
     * @param nums
     * @param limit
     * @return
     */
    public int minMoves(int[] nums, int limit) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int len = 2 * limit + 2;
        int[] operations = new int[len];
        int half = (nums.length >> 1);
        for (int i = 0; i < half; ++i) {
            int left = nums[i];
            int right = nums[nums.length - i - 1];
            int tmp = left + right;
            //[2, min(a,b)]刚开始需要2次操作
            operations[2] += 2;
            //[min(a,b) + 1, a + b)需要1次操作，因此-1，即可达到这目的。
            operations[Math.min(left, right) + 1] -= 1;
            //a + b， 什么都不需要做，因此-1，保证该位置为0
            operations[tmp] -= 1;
            //[a + b + 1, max(a,b) + limit]也只需要一次操作，上面为0了，所以这里+1
            operations[tmp + 1] += 1;
            //[max(a,b) + limit + 1, limit * 2]需要两次操作，在+1
            operations[Math.max(left, right) + limit + 1] += 1;
        }

        //从头到尾相加，计算每个区间需要的次数，同时把最小值找到。一个典型的差分数组运用方法。
        //注意这里从2开始，因为0，1的位置都是0，会导致得到错误的结果
        int res = operations[2];
        for(int i = 3; i < len;++i){
            operations[i] += operations[i - 1];
            res = Math.min(res, operations[i]);
        }

        return res;
    }
}
