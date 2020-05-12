package org.seancheer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 22. Generate Parentheses
 */
public class _22GenParentheses {
    public static void main(String[] args){
        _22GenParentheses obj = new _22GenParentheses();
        List<String> res = obj.generateParenthesis(3);
        System.out.println("res:" + res);

    }

    /**
     * 给定一个数字，写出所有合法的括号的组合
     * 基本思路：
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n <= 0){
            return new ArrayList<>();
        }
        return null;

    }
}
