package org.seancheer.twopointers;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * 有可能包含大写，也有可能包含小写，并且注意重复字母也必须包含在内，返回最短的子串包含t的所有字母
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _76MinimumWindowSubstring {

    public static void main(String[] args) {
        var obj = new _76MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        var res = obj.minWindow(s, t);
        System.out.println("res:" + res);//BANC

        s = "a";
        t = "a";
        res = obj.minWindow(s, t);
        System.out.println("res:" + res);//a

        s = "a";
        t = "aa";
        res = obj.minWindow(s, t);
        System.out.println("res:" + res);//""

        s = "a";
        t = "b";
        res = obj.minWindow(s, t);
        System.out.println("res:" + res);//""
    }

    /**
     * 解题思路：双指针问题，首先统计t中所有字符出现的次数，用map存储，使用对于s，使用两个指针i和j，j在前面
     * 1 j首先往前推，遇到一个map中的字符就减1，如果发现map中的所有字符个数都<=0了（小于0是因为可能重复出现），停下来，说明当前的
     * 子串是一定满足要求的，记录下来
     * 2 尽可能的往前推i，遇到一个map中的字符就+1，如果发现map中有字符个数>0了，说明当前位置在往前走就不满足要求了，停下来，当前子串
     * 满足要求，记录下来，返回最短的子串即可
     * <p>
     * 这道题的关键在于如何巧妙的统计所有字符都已经出现了
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (null == s || null == t || s.length() < t.length()) {
            return "";
        }

        int m = s.length();
        int n = t.length();
        int[] countMap = new int[(int) 'z' + 1];
        //统计t中每个字符出现的次数
        for (int i = 0; i < n; ++i) {
            ++countMap[t.charAt(i)];
        }

        //使用双指针来统计
        int resLen = Integer.MAX_VALUE;
        int resStart = 0;
        int counter = n;
        int start = 0;
        for (int end = 0; end < m; ++end) {
            int idx = s.charAt(end);
            //注意这里比较巧妙的手段，只有当<=0的时候，才减counter
            if (countMap[idx] > 0) {
                --counter;
            }
            --countMap[idx];
            //接下来开始往前推start
            while (counter == 0) {
                int newLen = end - start + 1;
                if (newLen < resLen){
                    resLen = newLen;
                    resStart = start;
                }

                idx = s.charAt(start);
                ++countMap[idx];
                //这里也是，一旦发现某个字符大于0了，那么及时增加counter，待会儿end要把它减掉
                if (countMap[idx] > 0){
                    ++counter;
                }
                ++start;
            }
        }

        //事后注意检测有没有有效值
        if (resLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(resStart, resStart + resLen);
    }

}
