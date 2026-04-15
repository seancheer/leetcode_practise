package org.seancheer.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 *
 * @author: seancheer
 * @date: 2021/8/3
 **/
public class _22GenerateParentheses {
    public static void main(String[] args) {
        _22GenerateParentheses obj = new _22GenerateParentheses();
        int n = 3;
        var res = obj.generateParenthesis(n);
        System.out.println("res:" + res);

        n = 1;
        res = obj.generateParenthesis(n);
        System.out.println("res:" + res);

    }

    /**
     * 解题思路：回溯法
     * 每一次既可以选择生成(也可以选择生成)，注意(的个数必须大于等于)，否则会出现不合法的结果
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        helper(n, n, res, new StringBuilder());
        return res;
    }

    /**
     * 递归生成需要的答案，其中n表示剩余的(的数量，m表示剩余的)的数量
     *
     * @param n
     * @param m
     * @param res
     * @param sb
     */
    private void helper(int n, int m, List<String> res, StringBuilder sb) {
        if (m == 0 && n == 0) {
            res.add(sb.toString());
            return;
        }

        //invalid
        if (n > m) {
            return;
        }

        //避免n成为负值
        if (n == 0){
            sb.append(")");
            helper(n, m - 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        sb.append("(");
        helper(n - 1, m, res, sb);
        sb.deleteCharAt(sb.length() - 1);

        //避免出现非法的结果
        if (n < m) {
            sb.append(")");
            helper(n, m - 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
