package org.seancheer.string;

import org.seancheer.utils.LeetCodeParent;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 * <p>
 * The algorithm for myAtoi(string s) is as follows:
 * <p>
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit charcter or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 * <p>
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "42" ("42" is read in)
 * ^
 * The parsed integer is 42.
 * Since 42 is in the range [-231, 231 - 1], the final result is 42.
 * Example 2:
 * <p>
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 * ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "   -42" ("42" is read in)
 * ^
 * The parsed integer is -42.
 * Since -42 is in the range [-231, 231 - 1], the final result is -42.
 * Example 3:
 * <p>
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 * ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
 * Example 4:
 * <p>
 * Input: s = "words and 987"
 * Output: 0
 * Explanation:
 * Step 1: "words and 987" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "words and 987" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "words and 987" (reading stops immediately because there is a non-digit 'w')
 * ^
 * The parsed integer is 0 because no digits were read.
 * Since 0 is in the range [-231, 231 - 1], the final result is 0.
 * Example 5:
 * <p>
 * Input: s = "-91283472332"
 * Output: -2147483648
 * Explanation:
 * Step 1: "-91283472332" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "-91283472332" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "-91283472332" ("91283472332" is read in)
 * ^
 * The parsed integer is -91283472332.
 * Since -91283472332 is less than the lower bound of the range [-231, 231 - 1], the final result is clamped to -231 = -2147483648.
 *
 * @author: seancheer
 * @date: 2021/9/15
 **/
public class _8StringToInteger extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _8StringToInteger();
        String s = "42";
        var res = obj.myAtoi(s);
        System.out.println("res:" + res); // 42

        s = "   -42";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // -42

        s = "4193 with words";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // 4193

        s = "words and 987";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // 0

        s = "987    12abc2";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // 987

        s = "-91283472332";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // -2147483648

        s = "-   987    12abc2";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // 0

        s = "00131204";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // 131204

        s = "-6147483648";
        res = obj.myAtoi(s);
        System.out.println("res:" + res); // -2147483648
    }

    /**
     * 解题思路：几个细节需要注意：
     * 1 开头的空格直接忽视
     * 2 如果溢出，那么选择最小值或者最大值
     * 3 数字后面如果出现了非数字字符，那么直接忽视掉，比如"987    12abc2"，结果为987
     * 4 只有开头的空格和末尾的空格才可以被忽略，其他的不可以，比如"-   987    12abc2"，结果为0
     * 5 注意开头0的问题
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }

        int len = s.length();
        int i = 0;
        long res = 0;
        boolean positive = true;
        //去掉开头的空格
        while (i < len && isWhiteSpace(s.charAt(i))) {
            ++i;
        }

        //判断符号
        if (i < len && isSymbol(s.charAt(i))) {
            if (s.charAt(i) == '-') {
                positive = false;
            }
            ++i;
        }

        //跳过所有的开头0
        while (i < len && s.charAt(i) == '0') {
            ++i;
        }

        //开始分析主体有效部分
        for (; i < len && isDigit(s.charAt(i)); ++i) {
            int val = s.charAt(i) - '0';
            res *= 10;
            res = res + val;
            //使用long可以发现是否出现了翻转，反正long精度够用
            if (res > Integer.MAX_VALUE){
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }

        return (int)(positive ? res : -res);
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private boolean isWhiteSpace(char ch) {
        return ch == ' ';
    }

    private boolean isSymbol(char ch) {
        return ch == '+' || ch == '-';
    }
}
