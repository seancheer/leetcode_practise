package org.seancheer.dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 * <p>
 * well-formed意思就是合法的()对，比如(), (()), ((()()))
 *
 * @author: seancheer
 * @date: 2021/8/12
 **/
public class _32LongestVAlidParentheses {
    public static void main(String[] args) {
        _32LongestVAlidParentheses obj = new _32LongestVAlidParentheses();
        String s = "(()";
        int res = obj.longestValidParentheses(s);
        int res2 = obj.longestValidParentheses2(s);
        System.out.println("res:" + res + "  res2:" + res2);  //2

        s = ")()())";
        res = obj.longestValidParentheses(s);
        res2 = obj.longestValidParentheses2(s);
        System.out.println("res:" + res + "  res2:" + res2);  //4

        s = "";
        res = obj.longestValidParentheses(s);
        res2 = obj.longestValidParentheses2(s);
        System.out.println("res:" + res + "  res2:" + res2);  //0

        s = "((()())()()()()";
        res = obj.longestValidParentheses(s);
        res2 = obj.longestValidParentheses2(s);
        System.out.println("res:" + res + "  res2:" + res2);  //14

        s = "())";
        res = obj.longestValidParentheses(s);
        res2 = obj.longestValidParentheses2(s);
        System.out.println("res:" + res + "  res2:" + res2);  //2
    }


    /**
     * 解题思路：动态规划问题，使用dp[i]表示以i位置为终点的子串所表示的合法括号的最大长度，如果s[i] == '('，那么dp[i] = 0;
     * 因为这意味这不存在以i为结尾的子串是合法的。状态转移方程如下：
     * 1 if s[i] == ')' and s[i - 1] == '(' ，那么dp[i] = d[i - 2] + 2;
     * 2 if s[i] == ')' and s[i - 1] == ')'，那么这个时候复杂点儿，首先我们需要跳过dp[i -1]的长度，查看i - 1 - dp[i - 1]位置处
     * 的字符是不是'('：
     * 2.1 如果是的话，那么刚好就能组成一对，就意味着dp[i] = dp[i - 2 - dp[i - 1]) + dp[i - 1] + 2，dp[i - 2 - dp[i - 1]]表示
     * i - 1 - dp[i - 1]位置之前合法子串的大小
     * 2.1 如果不是的话，那么dp[i] = 0，因为无法构成合法子串了
     * 整个过程注意记录最大子串即可
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (null == s || s.length() <= 1) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len];
        int res = 0;
        //这里为了处理方便，都用i+1表示s的第i个位置
        for (int i = 1; i < len; ++i) {
            char cur = s.charAt(i);
            if (cur == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[Math.max(i - 2, 0)] + 2;
                    //需要往前看了，比较复杂情况
                } else {
                    int prev = (i - 1 - dp[i - 1]);
                    if (prev >= 0) {
                        if (s.charAt(prev) == '(') {
                            //注意这里dp的位置
                            dp[i] = dp[Math.max(prev - 1, 0)] + dp[i - 1] + 2;
                        }
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }


    /**
     * 解题思路：使用栈来解决该问题，这个思路有点难想到，过程如下：
     * 1 首先使用一个栈，按照判断括号是否合法方式来进行操作，不同的地方在于，我们对于无法匹配的括号，也把他push进去
     * 2 这样子，操作完成后，如果栈是空的，那么就说明整个字符串都是合法的括号对，否则的话，继续进行下面的操作
     * 3 经过上述两步操作，我们的栈里面已经存储了所有无法匹配括号的index，而那些能够匹配的，已经经过push和pop完了，相当于是
     * 栈里面存储了所有合法括号对的边界，直接遍历栈，计算最大的子串即可
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (null == s || s.length() <= 1) {
            return 0;
        }

        //这里stack存储所有的index，而不是字符
        Deque<Integer> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            if (s.charAt(i) == '('){
                stack.push(i);
                //')'的情况
            }else{
                if (stack.isEmpty() || s.charAt(stack.peek()) == ')'){
                    stack.push(i);
                }else{
                    //合法的括号对，直接pop掉
                    stack.pop();
                }
            }
        }

        if (stack.isEmpty()){
            return len;
        }

        Integer res = 0;
        Integer end = len;
        while(!stack.isEmpty()){
            Integer start = stack.pop();
            res = Math.max(end - start - 1, res);
            end = start;
        }
        //末尾处理，因为如果字符串前缀是合法的，意味着前面的while会遗漏掉开头的前缀
        res = Math.max(end, res);
        return res;
    }
}
