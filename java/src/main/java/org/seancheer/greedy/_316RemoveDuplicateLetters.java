package org.seancheer.greedy;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure
 * your result is the smallest in lexicographical order among all possible results.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 * <p>
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 *
 * @author: seancheer
 * @date: 2021/7/20
 **/
public class _316RemoveDuplicateLetters {
    public static void main(String[] args) {
        _316RemoveDuplicateLetters obj = new _316RemoveDuplicateLetters();
        String s = "bcabc";
        var res = obj.removeDuplicateLetters(s);
        var res2 = obj.removeDuplicateLetters2(s);
        System.out.println("res:" + res + "  res2:" + res2); //abc

        s = "cbacdcbc";
        res = obj.removeDuplicateLetters(s);
        res2 = obj.removeDuplicateLetters2(s);
        System.out.println("res:" + res + "  res2:" + res2); //acdb

        s = "bbcaac";
        res = obj.removeDuplicateLetters(s);
        res2 = obj.removeDuplicateLetters2(s);
        System.out.println("res:" + res + "  res2:" + res2); //bac

        s = "abacb";
        res = obj.removeDuplicateLetters(s);
        res2 = obj.removeDuplicateLetters2(s);
        System.out.println("res:" + res + "  res2:" + res2); //abc

        s = "ayxyxz";
        res = obj.removeDuplicateLetters(s);
        res2 = obj.removeDuplicateLetters2(s);
        System.out.println("res:" + res + "  res2:" + res2); //axyz
    }

    /**
     * 解题思路：贪心算法
     * 首先统计字符出现的次数，然后开始遍历字符串，直到找到某个字符最后出现的位置，停下来，假设位置为i；
     * 对于[0,i]，由于i位置的字符最后一次出现，所以就意味着在i之前必须保留一个字符，显而易见的是我们选择i之前最小的字符即可；
     * 同时，既然选择了该字符，就意味着其他相同的字符必须被清理掉，清理完成之后，再从最小字符的下一个位置继续开始递归；
     * 最终就能找到合适的结果，这是一种贪心算法
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        if (null == s || s.isEmpty()) {
            return "";
        }

        final int letterSum = 26;
        int[] countArr = new int[letterSum];
        int len = s.length();
        //统计各个字符出现的次数
        for (int i = 0; i < len; ++i) {
            ++countArr[s.charAt(i) - 'a'];
        }

        int smallest = 0;
        for (int i = 0; i < len; ++i) {
            int idx = s.charAt(i) - 'a';
            //注意这里不是小于等于，如果等于的话会导致诸如'abacb'这样的用例出问题
            if (s.charAt(i) < s.charAt(smallest)) {
                smallest = i;
            }

            //idx位置的字符最后一次出现了，找到了本次的位置了，直接break出去
            if (--countArr[idx] == 0) {
                break;
            }
        }

        Character ch = s.charAt(smallest);
        //不要搞错了，从smallest位置的下一个位置开始查找
        return ch + removeDuplicateLetters(s.substring(smallest + 1).replaceAll(ch.toString(), ""));
    }

    /**
     * 解题思路：使用栈来解决该问题，每次检查栈顶的元素和当前位置字符的大小关系，如果比其大，那么push到栈顶（但是注意，还需要记录
     * 栈里面有没有该字符，如果有的话，那么不能push，我们选择直接跳过它），具体可以看代码解释，这个其实不是很好理解，就是一个比较
     * 讨巧的办法。
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters2(String s) {
        if (null == s || s.isEmpty()) {
            return "";
        }

        final int letterSum = 26;
        int[] countArr = new int[letterSum];
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char cur = s.charAt(i);
            ++countArr[cur - 'a'];
        }

        boolean[] visited = new boolean[letterSum];
        //使用StringBuilder来代替Stack，增加执行的效率
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < len;++i){
            char cur = s.charAt(i);
            int idx = cur - 'a';
            --countArr[idx];
            //为什么栈里面有，就直接跳过呢？而不是选择pop掉比他大（或者等于）的字符呢？这里有三个例子可以解释为什么：
            //1 abcabc，当碰到第二个a的时候，如果pop掉栈里面的abc，那么因为根据统计数组charCount，后面还有b和c，所以这里的pop是没有
            //意义的，因为后面的b和c还会进行，还会组成类似于abc这样的组合。
            //2 abcbd，当碰到第二个b的时候，注意，此时c是不能pop掉的，因为根据统计数组charCount，后面已经没有c了，因此，不能进行pop
            //3 abcadbc，当碰到第二个a的时候，如果把比a大（或者和a一样）的栈里面的abc pop掉，那么，我们会得到一个错误的结果
            //综上，所以这里一旦发现该位置访问过，那么就直接跳过
            if (visited[idx]){
                continue;
            }

            int stackLen = res.length() - 1;
            //因为栈里面的字符只出现过一次，所以将visited置为false，避免下一次直接给跳过了
            while(stackLen >= 0 && res.charAt(stackLen) >= cur && countArr[res.charAt(stackLen) - 'a'] != 0){
                visited[res.charAt(stackLen) - 'a'] = false;
                //删除掉栈顶比当前字符大或者等于的值
                res.deleteCharAt(stackLen);
                --stackLen;
            }

            visited[idx] = true;
            res.append(cur);
        }

        return res.toString();
    }
}
