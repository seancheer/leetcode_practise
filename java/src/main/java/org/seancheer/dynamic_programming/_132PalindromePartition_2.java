package org.seancheer.dynamic_programming;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 0
 * Example 3:
 * <p>
 * Input: s = "ab"
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2000
 * s consists of lower-case English letters only.
 *
 * @author: seancheer
 * @date: 2021/8/3
 **/
public class _132PalindromePartition_2 {
    public static void main(String[] args) {
        _132PalindromePartition_2 obj = new _132PalindromePartition_2();
        String s = "aab";
        var res = obj.minCut(s);
        System.out.println("res:" + res); //1

        s = "a";
        res = obj.minCut(s);
        System.out.println("res:" + res);  //0

        s = "ab";
        res = obj.minCut(s);
        System.out.println("res:" + res);  //1


        s = "aaabaa";
        res = obj.minCut(s);
        System.out.println("res:" + res);  //1

        s = "abaaabaa";
        res = obj.minCut(s);
        System.out.println("res:" + res);  //1
    }

    /**
     * 解题思路：动态规划
     * 使用dp[i]表示s的子串[0,i]需要切割的刀数，接下来以s的每一个位置为center，分别向左右判断是否为回文，两边的位置记为left和right，
     * 如果是回文的话，那么很明显dp[right] = dp[left - 1] + 1，因为[left,right]已经是回文了，所以相当于再切一刀，就得到了[left,right]
     * 的回文子串。
     * 这里唯一需要注意的就是回文可能以center为中心（奇数），也有可能以center,center+1为中心（偶数），所以两个都需要判断
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (null == s || s.length() <= 1) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len];
        //dp的初值
        for (int i = 0; i < len; ++i) {
            dp[i] = i;
        }

        for (int mid = 0; mid < len; ++mid) {
            int left = mid, right = mid;
            //奇数情况
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //说明当前以mid为中心是一个回文，所以这里取最小值
                dp[right] = Math.min(dp[right], (left == 0) ? 0 : dp[left - 1] + 1);
                --left;
                ++right;
            }

            left = mid;
            right = mid + 1;
            //偶数情况
            while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)){
                dp[right] = Math.min(dp[right], (left == 0) ? 0 : dp[left - 1] + 1);
                --left;
                ++right;
            }
        }
        return dp[len - 1];
    }
}
