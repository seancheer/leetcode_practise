package org.seancheer.dynamic_programming;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The answer is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * <p>
 * Input: s = "0"
 * Output: 0
 * Explanation: There is no character that is mapped to a number starting with 0.
 * The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
 * Hence, there are no valid ways to decode this since all digits need to be mapped.
 * Example 4:
 * <p>
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 * @author: seancheer
 * @date: 2021/7/15
 **/
public class _91DecodeWays {
    public static void main(String[] args) {
        _91DecodeWays obj = new _91DecodeWays();
        String s = "12";
        int res = obj.numDecodings(s);
        System.out.println("res:" + res);  //2

        s = "226";
        res = obj.numDecodings(s);
        System.out.println("res:" + res);  //3

        s = "06";
        res = obj.numDecodings(s);
        System.out.println("res:" + res);  //0

        s = "9";
        res = obj.numDecodings(s);
        System.out.println("res:" + res);  //1

        s = "6032";
        res = obj.numDecodings(s);
        System.out.println("res:" + res);  //0

    }


    /**
     * 解题思路：动态规划的思路
     * 用dp[i]表示长度为i的字符串解码的个数；
     * 对于字符串i的位置，有两种选择：
     * 1 s.charAt(i)作为一个单独字符解码，那么此时dp[i] = dp[i - 1]，直接继承长度为i - 1的字符串的解码总数。注意，检测0的问题；
     * 2 s.substring(i - 1,i + 1)两个字符作为一个整体来解码，当这两个整体没有leading 0的问题并且大小在26的范围之类的时候，那么，
     *   dp[i] = dp[i - 2]，因为这道题计算的是解码总数，因此dp[i] = dp[i - 1] + dp[i - 2]
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (null == s || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; ++i) {
            //避免出现一堆0的情况
            if (s.charAt(i) > '0') {
                dp[i] = dp[i - 1];
            }
            if (s.charAt(i - 1) > '0' && s.charAt(i - 1) <= '2') {
                int tmp = Integer.parseInt(s.substring(i - 1, i + 1));
                //说明可以把i-1,i这段字符串作为一个整体
                if (tmp <= 26) {
                    //避免数组越界的问题
                    if (i >= 2) {
                        dp[i] += dp[i - 2];
                    }else{
                        dp[i] += 1;
                    }
                }
            }
        }
        return dp[len - 1];
    }
}
