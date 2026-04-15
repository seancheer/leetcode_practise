package org.seancheer;

import java.util.Arrays;

/**
 *Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 * @author: seancheer
 * @date: 2021/8/22
 **/
public class _274HIndex {
    public static void main(String[] args) {
        var obj = new _274HIndex();
        int[] nums = new int[]{3,0,6,1,5};
        var res = obj.hIndex(nums);
        System.out.println("res:" + res); //3

        nums = new int[]{1,3,1};
        res = obj.hIndex(nums);
        System.out.println("res:" + res); //1
    }

    /**
     * 解题思路：总共由len篇论文，然后至少有n篇被引用了n次，其余len - n篇被引用不超过n次，注意如果有多个答案，返回最大的
     * 首先进行排序，然后开始遍历查找最后一个还满足条件的就是目标答案
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if (null == citations || citations.length == 0){
            return 0;
        }

        int len = citations.length;
        Arrays.sort(citations);
        int count = 0;
        for(int i = len - 1;i >= 0;--i){
            ++count;
            if (count > citations[i]){
                return count - 1;
            }
        }
        return count;
    }
}
