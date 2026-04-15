package org.seancheer.twopointers;

import org.w3c.dom.html.HTMLPreElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _15_3Sum {

    public static void main(String[] args) {
        var obj = new _15_3Sum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        var res = obj.threeSum(nums);
        var res2 = obj.threeSum2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//[[-1,-1,2],[-1,0,1]]


        nums = new int[]{0};
        res = obj.threeSum(nums);
        res2 = obj.threeSum2(nums);
        System.out.println("res:" + res + "   res2:" + res2);

        nums = new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        res = obj.threeSum(nums);
        res2 = obj.threeSum2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//[[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]]

        nums = new int[]{0,0,0};
        res = obj.threeSum(nums);
        res2 = obj.threeSum2(nums);
        System.out.println("res:" + res + "   res2:" + res2);//[[0,0,0]]

    }

    /**
     * 解题思路：使用双指针来确定前两个数，最后一个数使用二分法来查找加快速度
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (null == nums || nums.length < 3) {
            return new ArrayList<>();
        }

        //首先进行排序，后面要进行二分查找
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len - 2; ++i) {
            for (int j = i + 1; j < len - 1; ++j) {
                int target = 0 - (nums[i] + nums[j]);
                //在剩下的位置二分法查找target
                int k = Arrays.binarySearch(nums, j + 1, len, target);
                if (k > 0) {
                    List<Integer> li = Arrays.asList(nums[i], nums[j], nums[k]);
                    Collections.sort(li);
                    String key = getKey(li);
                    if (!set.contains(key)) {
                        set.add(key);
                        res.add(li);
                    }
                }
            }
        }
        return res;
    }

    private String getKey(List<Integer> key) {
        return String.valueOf(key.get(0)) + key.get(1) + key.get(2);
    }

    /**
     * 解题思路：第二种解题思路，速度会更快，看代码逻辑就能够直接理解，需要注意的是，这种解法完全不需要去重处理，因为从代码逻辑
     * 上保证了不可能出现重复的结果
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        if (null == nums || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; ++i) {
            //nums[i]没有发生变化，那么就意味着没有必要在继续以当前值为起点
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int left = i + 1;
            int right = len - 1;

            //因为排好序了，所以可以从两端开始加速查找
            while (left < right) {
                //因为排好序了，发现最小的值比target还大，那么肯定就找不到目标的target
                if (nums[left] > target) {
                    break;
                }
                int tmp = nums[left] + nums[right];
                if (tmp == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    ++left;
                    //之所以可以这么做是因为left确定了，right也就确定了，所以相同的值没有意义
                    while (left < right && nums[left] == nums[left - 1]) {
                        ++left;
                    }

                    --right;
                    while (left < right && nums[right] == nums[right + 1]) {
                        --right;
                    }
                } else if (tmp > target) {
                    //此时需要缩小right来满足要求
                    --right;
                    while (left < right && nums[right] == nums[right + 1]) {
                        --right;
                    }
                } else {
                    //此时需要增大left来满足要求
                    ++left;
                    while (left < right && nums[left] == nums[left - 1]) {
                        ++left;
                    }
                }
            }
        }
        return res;
    }
}
