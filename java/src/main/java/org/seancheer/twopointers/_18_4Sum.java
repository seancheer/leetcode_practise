package org.seancheer.twopointers;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 18. 4Sum
 * <p>
 * Given an array nums of n integers and an integer target, are there elements a, b, c,
 * and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 */
public class _18_4Sum extends LeetCodeParent {
    public static void main(String[] args) {
        _18_4Sum obj = new _18_4Sum();
        var res = obj.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        sortRes(res);
        printRes(res);

        res = obj.fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0);
        sortRes(res);
        printRes(res);
    }

    /**
     * 解题思路：
     * 其实是类似于{@link _15_3Sum}的思路，3sum是第一个值固定，前后夹击后两个值，4sum是前两个值固定，前后夹击后两个值，本质上
     * 都是一样的
     * 1 排序
     * 2 循环前两个数的和，为了加快速度，后两个数采取前后夹击的方式
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Set<String> s = new HashSet<>();
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                int start = j + 1;
                int end = len - 1;
                while (start < end) {
                    int tmp = nums[i] + nums[j] + nums[start] + nums[end];
                    if (tmp == target) {
                        List<Integer> t = Arrays.asList(nums[i], nums[j], nums[start], nums[end]);
                        //去除掉重复的key
                        if (s.add(constructKey(t))) {
                            res.add(t);
                        }
                        ++start;
                        --end;
                    } else if (tmp > target) {
                        --end;
                    } else {
                        ++start;
                    }
                }

            }
        }

        return res;
    }

    /**
     * 用来进行去重用的
     *
     * @param keys
     * @return
     */
    private String constructKey(List<Integer> keys) {
        StringBuilder sb = new StringBuilder();
        for (Integer k : keys) {
            sb.append(k);
            sb.append("_");
        }
        return sb.toString();
    }

    private static void sortRes(List<List<Integer>> res) {
        res.sort((left, right) -> {
            int len = left.size();
            for (int i = 0; i < len; ++i) {
                int t = left.get(i).compareTo(right.get(i));
                if (t < 0) {
                    return -1;
                } else if (t > 0) {
                    return 1;
                }
            }
            return 0;
        });
    }
}
