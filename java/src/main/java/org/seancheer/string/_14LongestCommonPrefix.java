package org.seancheer.string;

import org.seancheer.utils.LeetCodeParent;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lower-case English letters.
 *
 * @author: seancheer
 * @date: 2021/9/16
 **/
public class _14LongestCommonPrefix extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _14LongestCommonPrefix();
        String[] strs = new String[]{"flower", "flow", "flight"};
        var res = obj.longestCommonPrefix(strs);
        System.out.println("res:" + res); // fl

        strs = new String[]{"dog", "racecar", "car"};
        res = obj.longestCommonPrefix(strs);
        System.out.println("res:" + res); // ""

        strs = new String[]{"ab", "a"};
        res = obj.longestCommonPrefix(strs);
        System.out.println("res:" + res); // "a"
    }

    /**
     * 解题思路：只包含小写字母，最直观的想法是挨个遍历，目前来看，没有特别快的办法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        int minLen = strs[0].length();
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        int i = 0;
        for (; i < minLen; ++i) {
            for (int j = 0; j < strs.length; ++j) {
                if (j > 0 && strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0].substring(0, i);
    }
}
