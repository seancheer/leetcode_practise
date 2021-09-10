package org.seancheer.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * <p>
 * A palindrome string is a string that reads the same backward as forward.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 * <p>
 * 将字符串分割为子串，保证每个子串都是回文
 *
 * @author: seancheer
 * @date: 2021/8/3
 **/
public class _131PalindromePartitioning {
    public static void main(String[] args) {
        _131PalindromePartitioning obj = new _131PalindromePartitioning();
        String s = "aab";
        var res = obj.partition(s);
        System.out.println("res:" + res);

        s = "a";
        res = obj.partition(s);
        System.out.println("res:" + res);
    }

    //存储字符串s对应范围的子串是否为回文，加快速度
    private Map<String, Boolean> map = new HashMap<>();

    /**
     * 解题思路：回溯法，找出所有的子串，然后选择那些都是回文的？
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (null == s || s.isEmpty()) {
            return new ArrayList<>();
        }

        map.clear();
        List<List<String>> res = new ArrayList<>();
        helper(s, 0, res, new ArrayList<>());
        return res;
    }

    /**
     * 通过不断的回溯法来找出所有符合要求的子串
     *
     * @param s
     * @param pos
     * @param res
     * @param tmpRes
     */
    private void helper(String s, int pos, List<List<String>> res, List<String> tmpRes) {
        //到末尾了，说明已经把所有子串找到了
        if (pos == s.length()) {
            res.add(new ArrayList<>(tmpRes));
            return;
        }

        for (int i = pos; i < s.length(); ++i) {
            //只有在回文的前提下才进行递归，其他的都是非法的
            if (isPalindrome(s, pos, i)) {
                tmpRes.add(s.substring(pos, i + 1));
                helper(s, i + 1, res, tmpRes);
                tmpRes.remove(tmpRes.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        String key = left + String.valueOf(right);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        while (left <= right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }

        boolean res = (left > right);
        map.put(key, res);
        return res;
    }
}
