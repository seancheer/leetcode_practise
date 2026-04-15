package org.seancheer.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * <p>
 * <p>
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 * 虽然是easy级别的题目，但是解法比较有代表性，可以好好学习下。
 *
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _1TwoSum {
    public static void main(String[] args) {
        var obj = new _1TwoSum();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        var res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [0,1]

        nums = new int[]{3, 2, 4};
        target = 6;
        res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [1,2]

        nums = new int[]{3, 3};
        target = 6;
        res = obj.twoSum(nums, target);
        System.out.println("res:" + Arrays.toString(res)); // [0,1]
    }

    /**
     * 解题思路：O(n^2)的方法很简单，这里就不展示了，主要解释下低于O(n^2)的解法
     * 使用map存储nums[i] -> pos的键值对，每遇到一个新的num的时候，直接在map中查找target - num
     * ，如果找到了，那么我们就找到了目标结果，否则，将当前值和位置放入到map中
     * 加上map的时间复杂度log(n)，总的时间复杂度为nlog(n)
     * 该方法可以拓展到求中间任意一段的和为target的问题，可以参考{@link org.seancheer.tree._437PathSum_3}
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int tmp = target - nums[i];
            Integer pos = map.get(tmp);
            if (null != pos) {
                return new int[]{pos, i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
