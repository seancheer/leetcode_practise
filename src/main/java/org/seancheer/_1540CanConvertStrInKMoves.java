package org.seancheer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Given two strings s and t, your goal is to convert s into t in k moves or less.
 *
 * During the ith (1 <= i <= k) move you can:
 *
 * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous move, and shift the character at that index i times.
 * Do nothing.
 * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Shifting a character by i means applying the shift operations i times.
 *
 * Remember that any index j can be picked at most once.
 *
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "input", t = "ouput", k = 9
 * Output: true
 * Explanation: In the 6th move, we shift 'i' 6 times to get 'o'. And in the 7th move we shift 'n' to get 'u'.
 * Example 2:
 *
 * Input: s = "abc", t = "bcd", k = 10
 * Output: false
 * Explanation: We need to shift each character in s one time to convert it into t. We can shift 'a' to 'b' during the 1st move. However, there is no way to shift the other characters in the remaining moves to obtain t from s.
 * Example 3:
 *
 * Input: s = "aab", t = "bbb", k = 27
 * Output: true
 * Explanation: In the 1st move, we shift the first 'a' 1 time to get 'b'. In the 27th move, we shift the second 'a' 27 times to get 'b'.
 * @author: seancheer
 * @date: 2021/3/2
 **/
public class _1540CanConvertStrInKMoves {
    public static void main(String[] args) {
        _1540CanConvertStrInKMoves obj = new _1540CanConvertStrInKMoves();
        String s = "input", t = "ouput";
        int k = 9;
        var res = obj.canConvertString(s, t, k);
        var res2 = obj.canConvertString2(s, t, k);
        System.out.println("res : " + res + "    res2:" + res2);

        s = "abc";
        t = "bcd";
        k = 10;
        res = obj.canConvertString(s, t, k);
        res2 = obj.canConvertString2(s, t, k);
        System.out.println("res : " + res + "    res2:" + res2);

        s = "aab";
        t = "bbb";
        k = 27;
        res = obj.canConvertString(s, t, k);
        res2 = obj.canConvertString2(s, t, k);
        System.out.println("res : " + res + "    res2:" + res2);
    }

    /**
     * 解题思路：首先记录s和t相同index中不同的字母的差值，然后对该值进行一个排序，接下来挨个遍历，
     * 比如差值分别为：2，2，2，3，3；那么一个差值就取2，第二个取2 + 26=28，第三个取2 + 26 * 2 = 54
     * 碰到3了，那么直接取3，第二个3为3 + 26 = 29，依次判断这些结果是否大于k，如果大于k，说明无法满足要求。
     * 这个解法其实可以解决问题，但是效率还是不够高，可以看解法2
     * @param s
     * @param t
     * @param k
     * @return
     */
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length() || k < 0) {
            return false;
        }

        final int charSum = 26;
        List<Integer> stepList = new ArrayList<>();
        for(int i = 0;i < s.length();++i){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sChar == tChar){
                continue;
            }

            //不要忘记z到a只需要一步，这是一个循环
            int step = tChar - sChar;
            if (sChar > tChar) {
                step += charSum;
            }
            stepList.add(step);
        }

        //说明两个字符串完全一样
        if (stepList.isEmpty()){
            return true;
        }


        Collections.sort(stepList);
        if (stepList.get(0) > k){
            return false;
        }

        int prev = stepList.get(0);
        int j = 0;
        for(int i = 1;i < stepList.size();++i){
            ++j;
            if (stepList.get(i) != prev){
                j = 0;
            }

            prev = stepList.get(i);
            int tmp = stepList.get(i) + charSum * j;
            if (tmp > k){
                return false;
            }
        }
        return true;
    }
    /**
     * 解题思路：使用一个数组，记录每一个差值出现的次数，每次进行更新的时候，都对其进行差值 + count * 26的运算，每次进行递增，
     * 如果发现递增后的结果大于k了，那么说明肯定是不满足要求的，因为此时前面的位置都用完了，也就是说，我们每遇到一个没有利用的位置，
     * 就把其分配给当前的字符，这也是一种基于贪婪的思想。
     *
     * @param s
     * @param t
     * @param k
     * @return
     */
    public boolean canConvertString2(String s, String t, int k) {
        if (s.length() != t.length() || k < 0) {
            return false;
        }
        final int charSum = 26;
        //因为两者相差不可能超过26.
        int[] countCharArr = new int[charSum +  1];
        for(int i = 0;i < s.length();++i){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sChar == tChar){
                continue;
            }

            //不要忘记z到a只需要一步，这是一个循环
            int step = tChar - sChar;
            if (sChar > tChar) {
                step += charSum;
            }

            //计算新的需要的位置
            int needStep = step + countCharArr[step] * charSum;
            if (needStep > k){
                return false;
            }

            ++countCharArr[step];
        }

        return true;
    }


}
