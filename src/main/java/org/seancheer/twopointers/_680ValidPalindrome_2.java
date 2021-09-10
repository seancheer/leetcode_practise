package org.seancheer.twopointers;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aba"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 * <p>
 * Input: s = "abc"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 *
 * @author: seancheer
 * @date: 2021/8/15
 **/
public class _680ValidPalindrome_2 {
    public static void main(String[] args) {
        var obj = new _680ValidPalindrome_2();
        String s = "aba";
        var res = obj.validPalindrome(s);
        System.out.println("res:" + res);//true

        s = "abca";
        res = obj.validPalindrome(s);
        System.out.println("res:" + res);//true

        s = "abc";
        res = obj.validPalindrome(s);
        System.out.println("res:" + res);//false

        s = "cuucu";
        res = obj.validPalindrome(s);
        System.out.println("res:" + res);//true
    }

    /**
     * 解题思路：要求最多删除一个就达到回文的要求，怎么快速判断出来呢？
     * 双指针，一个从头开始，一个从尾部开始，如果相等，直接跳过，不相等，那么有两种选择，s[i + 1] s[j]和s[i] s[j - 1]，
     * 两条路中有任意一条路是通的，就返回true即可
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (null == s || s.length() <= 1) {
            return true;
        }
        int len = s.length();
        int i = 0, j = len - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            ++i;
            --j;
        }

        return isPalindrom(s, i + 1, j) || isPalindrom(s, i, j - 1);
    }

    private boolean isPalindrom(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            ++left;
            --right;
        }
        return left >= right;
    }
}
