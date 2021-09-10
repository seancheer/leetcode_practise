package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 * 注意，字符串有可能会包括英文字母，数字，特殊符号以及空格
 *
 * @author: seancheer
 * @date: 2021/9/2
 **/
public class _3LongestSubStringWithoutRepeatingChar extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _3LongestSubStringWithoutRepeatingChar();
        String str = "abcabcbb";
        var res = obj.lengthOfLongestSubstring(str);
        System.out.println("res:" + res); //3

        str = "bbbbb";
        res = obj.lengthOfLongestSubstring(str);
        System.out.println("res:" + res); //1

        str = "pwwkew";
        res = obj.lengthOfLongestSubstring(str);
        System.out.println("res:" + res); //3

        str = "";
        res = obj.lengthOfLongestSubstring(str);
        System.out.println("res:" + res); //0

        str = "bwf";
        res = obj.lengthOfLongestSubstring(str);
        System.out.println("res:" + res); //3
    }

    /**
     * 解题思路：使用一个map存储每一个字符出现的index，往后遍历，如果发现了一个重复的字符，那么将该字符从map中移除掉，记其旧的index
     * 为oldIndex，更新map中的index为当前index，同时，无重复字符串的index起始位置startIdx = Math.max(oldIndex + 1，startIdx)
     * 为什么是max呢？因为startIdx如果往小的走了，就会导致出现重复字符串，所以只能往前走
     * 这样子遍历完就确定了无重复最长子串
     * 因为s可能有字母，也有可能有数字，空格等，所以用256大小的数组来作为map，不用真正的map，可以提高效率
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        final int mapSize = 256;
        int[] map = new int[mapSize];
        Arrays.fill(map, -1);
        int startIdx = 0;
        map[s.charAt(0)] = 0;
        int res = 1;
        for (int i = 1; i < s.length(); ++i) {
            char ch = s.charAt(i);
            //大于等于0表示该字符曾经出现过，那么需要更新startIdx
            if (map[ch] >= 0) {
                startIdx = Math.max(startIdx, map[ch] + 1);
            }
            //每次记录最长的res
            res = Math.max(res, i - startIdx + 1);
            map[ch] = i;
        }
        return res;
    }
}
