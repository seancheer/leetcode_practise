package org.seancheer.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
 * <p>
 * Return the minimum number of patches required.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 * <p>
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 * <p>
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 * 给数组中增加数字，以达到数组中所有组合的和能够覆盖[1,n]，求所需要增加的最少的数字
 *
 * @author: seancheer
 * @date: 2021/8/2
 **/
public class _330PatchingArray {
    public static void main(String[] args) {
        _330PatchingArray obj = new _330PatchingArray();
        int[] nums = new int[]{1, 3};
        int n = 6;
        var res = obj.minPatches(nums, n);
        System.out.println("res:" + res); //1

        nums = new int[]{1, 5, 10};
        n = 20;
        res = obj.minPatches(nums, n);
        System.out.println("res:" + res); //2

        nums = new int[]{1, 2, 2};
        n = 5;
        res = obj.minPatches(nums, n);
        System.out.println("res:" + res); //0
    }

    /**
     * 解题思路：贪心算法，解法2是不可取的
     * upper表示当前所能走的上限，比如数组[2,5,10]，初始化upper为1
     * 从upper开始一直到n进行迭代，将会有两种情况：
     * 1 如果发现nums[i]< = upper，因为我们已经可以构造[1,upper)的组合了，所以来了个nums[i]，就直接让我们可以构造[1,upper + nums[i])的组合了，
     *   同时，因为nums[i]本身就存在，所以不计入res
     * 2 如果发现nums[i] > upper，那么因为是右开区间，所以我们需要新加入upper来构造这个upper，直接导致我们的组合区间扩充到[1, upper+upper]了，同时
     *   计入res
     * 每一次循环i都往前推，因为upper初始化为了1，所以是个右开区间
     * 注意upper可能会翻转，要处理该情况
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        if (null == nums) {
            return 0;
        }

        long upper = 1;
        int i = 0;
        int res = 0;
        while (upper <= n) {
            if (i < nums.length && nums[i] <= upper) {
                upper += nums[i++];
            } else {
                upper += upper;
                ++res;
            }
        }
        return res;
    }

    /**
     * 解题思路：贪心算法，记录所有的组合的和，然后挨个从[1,n]开始判断有没有在组合里面，没有的话，则说明要加入该数字，加入后更新组合集合，
     * 继续下一轮的判断。
     * 这种解法没有用到题目说的nums是递增序列，所以肯定不是最优解，应该在好好想想
     * 超时了，这个方法不可取
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches2(int[] nums, int n) {
        if (null == nums) {
            return 0;
        }

        //首先计算所有的组合的和，因为这道题只关心组合的和，而不关心具体的组合是什么，所以直接使用循环可以解决这个问题
        Set<Integer> combination = new HashSet<>();
        int len = nums.length;
        combination.add(nums[0]);
        for (int i = 1; i < len; ++i) {
            Set<Integer> newCombination = new HashSet<>();
            newCombination.add(nums[i]);
            //组合的本质是每次在旧的组合上加上新来的数字
            for (Integer item : combination) {
                newCombination.add(item + nums[i]);
            }
            combination.addAll(newCombination);
        }

        //开始检测需要增加的数字，如果没有发现的话，则意味这必须加入该数字，然后更新combination组合，否则的话，直接跳过即可，
        //res记录需要添加的数字
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            if (combination.contains(i)) {
                continue;
            }

            ++res;
            Set<Integer> newCombination = new HashSet<>();
            newCombination.add(i);
            //组合的本质是每次在旧的组合上加上新来的数字
            for (Integer item : combination) {
                newCombination.add(item + i);
            }
            combination.addAll(newCombination);
        }

        return res;
    }

    /**
     * 打印所有的组合，使用位运算的方式
     *
     * @param nums
     */
    private void printCombination(int[] nums) {
        int n = nums.length;
        int target = (1 << n);
        for (int i = 1; i < target; ++i) {
            int tmp = i;
            int j = 0;
            List<Integer> res = new ArrayList<>();
            while (tmp != 0) {
                if ((tmp & 1) != 0) {
                    res.add(nums[j]);
                }
                ++j;
                tmp >>= 1;
            }
            System.out.print(res + ", ");
        }
    }
}
