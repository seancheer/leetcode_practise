package org.seancheer.string;

import org.seancheer.utils.LeetCodeParent;

/**
 *The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.
 *
 * For example, the saying and conversion for digit string "3322251":
 *
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 30
 * @author: seancheer
 * @date: 2021/9/17
 **/
public class _38CountAndSay extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _38CountAndSay();
        int n = 1;
        var res = obj.countAndSay(1);
        System.out.println("res:" + res); // "1"

        n = 4;
        res = obj.countAndSay(n);
        System.out.println("res:" + res); // "1211"
    }

    /**
     * 解题思路：按照题目的描述，按照递归的方式进行处理，每次都是基于上次的结果来处理的。
     * 迭代的解法也很简单，这里就不写了
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n <= 1){
            return "1";
        }

        String str = countAndSay(n - 1);
        //得到上一次的结果后，接下来进行countAndSay
        int len = str.length();
        StringBuilder res = new StringBuilder();
        int count = 1;
        int i = 1;
        for(;i < len;++i){
            if (str.charAt(i) == str.charAt(i - 1)){
                ++count;
            }else{
                res.append(count).append(str.charAt(i - 1));
                count = 1;
            }
        }
        //还有最后的字符还没有处理，所以要单独来处理
        res.append(count).append(str.charAt(i - 1));
        return res.toString();
    }
}
