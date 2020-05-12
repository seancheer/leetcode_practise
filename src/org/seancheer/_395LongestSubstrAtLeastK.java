package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class _395LongestSubstrAtLeastK extends LeetCodeParent {
    public static void main(String[] args) {
        _395LongestSubstrAtLeastK obj = new _395LongestSubstrAtLeastK();
        String s = "ababbc";
        int k = 2;
        var res = obj.longestSubstring(s, k);
        System.out.println("res:" + res); //5


        s = "aaabbb";
        k = 3;
        res = obj.longestSubstring(s, k);
        System.out.println("res:" + res);  //6

        s = "aaabbbcbbbbbb";
        k = 3;
        res = obj.longestSubstring(s, k);
        System.out.println("res:" + res);  //6

        s = "bbaaacbd";
        k = 3;
        res = obj.longestSubstring(s, k);
        System.out.println("res:" + res);  //3
    }

    /**
     * 返回长度最长的字串，要求里面的每个字符出现次数都大于等于k
     * 注意：题目中说了只包含小写字母
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (null == s || s.isEmpty() || k <= 0) {
            return 0;
        }

        int[] charInfo = new int[26];
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            ++charInfo[s.charAt(i) - 'a'];
        }


        return longestSubstringInternal(s, k, 0, len- 1, charInfo);
    }

    public int longestSubstringInternal(String s, int k, int left, int right, int[] charInfo) {
        if (left > right){
            return 0;
        }

        int res = 0;
        int tmpLen = 0;
        int lastPos = left;
        int[] tmpInfo = new int[26];
        for (int i = left; i <= right; ++i) {
            char cur = s.charAt(i);
            if (charInfo[cur - 'a'] < k) {
                if (checkIsValid(tmpInfo, k)) {
                    res = Math.max(res, tmpLen);
                }
                else{
                    res= Math.max(res, longestSubstringInternal(s, k, lastPos, i - 1, tmpInfo));
                }
                Arrays.fill(tmpInfo, 0);
                tmpLen = 0;
                lastPos = i + 1;
            } else {
                ++tmpLen;
                ++tmpInfo[cur - 'a'];
            }
        }

        if (charInfo[s.charAt(right) - 'a'] >= k && checkIsValid(tmpInfo, k)) {
            res = Math.max(res, tmpLen);
        }

        return res;
    }

        private boolean checkIsValid(int[] tmpInfo, int k) {
        for (int item : tmpInfo) {
            if (item > 0 && item < k) {
                return false;
            }
        }

        return true;
    }
}
