package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi]. The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi are 0-indexed.
 * <p>
 * Return the maximum total sum of all requests among all permutations of nums.
 * <p>
 * Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * Output: 19
 * Explanation: One permutation of nums is [2,1,3,4,5] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * Total sum: 8 + 3 = 11.
 * A permutation with a higher total sum is [3,5,4,2,1] with the following result:
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * Total sum: 11 + 8 = 19, which is the best that you can do.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5,6], requests = [[0,1]]
 * Output: 11
 * Explanation: A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
 * Output: 47
 * Explanation: A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].
 *
 * @author: seancheer
 * @date: 2021/3/10
 **/
public class _1589MaxSumObtainedOfAnyPerm extends LeetCodeParent {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        String str = "[[1,3],[0,1]]";
        int[][] requests = convertStrTo2DemArr(str);
        var obj = new _1589MaxSumObtainedOfAnyPerm();
        int res = obj.maxSumRangeQuery(nums, requests);
        System.out.println("res:" + res);  //19

        nums = new int[]{1, 2, 3, 4, 5, 6};
        str = "[[0,1]]";
        requests = convertStrTo2DemArr(str);
        obj = new _1589MaxSumObtainedOfAnyPerm();
        res = obj.maxSumRangeQuery(nums, requests);
        System.out.println("res:" + res);  //11

        nums = new int[]{1, 2, 3, 4, 5, 10};
        str = "[[0,2],[1,3],[1,1]]";
        requests = convertStrTo2DemArr(str);
        obj = new _1589MaxSumObtainedOfAnyPerm();
        res = obj.maxSumRangeQuery(nums, requests);
        System.out.println("res:" + res);   //47
    }

    /**
     * 解题思路：这道题的关键其实是查找重叠部分最多的区间，然后按照该区间个数选择nums中最大，
     * 然后重叠部分次多的区间选nums中次大的，以此类推。
     * 最终的结果就是需要的答案。
     * 当然，这个问题的关键就在于怎么查找重叠部分了，这里采用的sweep line算法，根据该算法可以
     * 很快的查找到重叠的部分。
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        if (null == nums || nums.length == 0 || null == requests || requests.length == 0) {
            return 0;
        }

        final int mod = 1000000000 + 7;
        int[] sum = new int[nums.length];
        for (int[] item : requests) {
            //说明进入一个新的区间，那么+1
            ++sum[item[0]];
            //结束时间表示一个区间结束，所以-1
            if (item[1] < nums.length - 1) {
                --sum[item[1] + 1];
            }
        }

        //从头到尾相加，相当于是统计每个区间出现的次数。
        for (int i = 1; i < nums.length; ++i) {
            sum[i] += sum[i - 1];
        }

        Arrays.sort(nums);
        Arrays.sort(sum);
        long res = 0;
        //出现次数最多的选择nums中最大的值。
        for (int i = nums.length - 1; i >= 0; --i) {
            res += (((long) nums[i] * (long) sum[i]) % mod);
            res %= mod;
        }

        return (int) res;
    }
}
