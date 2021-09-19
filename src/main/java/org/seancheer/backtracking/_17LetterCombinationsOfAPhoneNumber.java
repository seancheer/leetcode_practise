package org.seancheer.backtracking;

import org.seancheer.linkedlist.LinkedListParent;
import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 * @author: seancheer
 * @date: 2021/9/16
 **/
public class _17LetterCombinationsOfAPhoneNumber extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _17LetterCombinationsOfAPhoneNumber();
        String s = "23";
        var res = obj.letterCombinations(s);
        var res2 = obj.letterCombinations2(s);
        System.out.println("res:" + res + "   res2:" + res2); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        s = "";
        res = obj.letterCombinations(s);
        res2 = obj.letterCombinations2(s);
        System.out.println("res:" + res + "   res2:" + res2);  // []

        s = "2";
        res = obj.letterCombinations(s);
        res2 = obj.letterCombinations2(s);
        System.out.println("res:" + res + "   res2:" + res2);  // ["a","b","c"]
    }


    /**
     * 解题思路：回溯法，就是一个组合的问题，递归处理即可
     * 本质上是一个DFS，且看下面的BFS解法
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (null == digits || digits.isEmpty()) {
            return new ArrayList<>();
        }

        String[] phoneMap = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        helper(digits, 0, new StringBuilder(), res, phoneMap);
        return res;
    }

    /**
     * 回溯并递归进行处理
     *
     * @param digits
     * @param idx
     * @param tmpRes
     * @param res
     * @param phoneMap
     */
    private void helper(String digits, int idx, StringBuilder tmpRes, List<String> res, String[] phoneMap) {
        if (idx == digits.length()) {
            res.add(tmpRes.toString());
            return;
        }

        char cur = digits.charAt(idx);
        String characters = phoneMap[cur - '0'];
        for (char item : characters.toCharArray()) {
            tmpRes.append(item);
            helper(digits, idx + 1, tmpRes, res, phoneMap);
            //回溯要去掉当前的位置
            tmpRes.deleteCharAt(tmpRes.length() - 1);
        }
    }


    /**
     * 解题思路：使用一个队列，运用BFS的思路来解决该问题
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        if (null == digits || digits.isEmpty()) {
            return new ArrayList<>();
        }

        LinkedList<String> res = new LinkedList<>();
        //加入一个初始值来驱动算法
        res.add("");
        int len = digits.length();
        String[] phoneMap = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < len; ++i) {
            int idx = digits.charAt(i) - '0';
            //这里是比较精巧的逻辑，因为每循环一次，res里面的字符串长度就会增加1，所以用i来作为循环的条件再合适不过
            while (res.peek().length() == i) {
                String tmp = res.remove();
                for (char ch : phoneMap[idx].toCharArray()) {
                    res.add(tmp + ch);
                }
            }
        }
        return res;
    }
}
