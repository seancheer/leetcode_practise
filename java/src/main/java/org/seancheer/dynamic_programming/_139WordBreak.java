package org.seancheer.dynamic_programming;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * 注意，不一定要用光dict里面所有的字符串，比如
 * leetcod
 * [leet, cod, code]也是可以的
 *
 * @author: seancheer
 * @date: 2021/8/18
 **/
public class _139WordBreak {
    public static void main(String[] args) {
        var obj = new _139WordBreak();
        String s = "leetcode";
        List<String> dict = Arrays.asList("leet", "code");
        var res = obj.wordBreak(s, dict);
        System.out.println("res:" + res); //true

        s = "applepenapple";
        dict = Arrays.asList("apple", "pen");  //dict里面的单词可以重复使用
        res = obj.wordBreak(s, dict);
        System.out.println("res:" + res); //true

        s = "catsandog";
        dict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        res = obj.wordBreak(s, dict);
        System.out.println("res:" + res); //false

        s = "dogs";
        dict = Arrays.asList("dog", "s", "gs");
        res = obj.wordBreak(s, dict);
        System.out.println("res:" + res); //true
    }

    /**
     * 解题思路：动态规划问题；
     * 使用dp[i]表示到s的i位置，是否可以用wordDict来表示，那么：
     * for(String item : wordDict){
     * if (s.substring(i - item.size(), i).equals(item)){
     * dp[i] = dp[i - item.size()];
     * }
     * }
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (null == s || s.isEmpty()) {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i < len; ++i) {
            //当前的最大长度
            int tmpLen = i + 1;
            for (String word : wordDict) {
                //还要注意的是，如果本身是true就不要在判断了，不要再被false覆盖
                if (word.length() <= tmpLen && s.substring(tmpLen - word.length(), tmpLen).equals(word)
                        && !dp[i + 1]) {
                    //从上一个状态进行推算，因为我们用dp[i +1]表示i位置，所以下面可以直接用tmpLen - word.length()，
                    //tmpLen - word.length()是起点，对应的dp位置应该是dp[tmpLen - word.length() - 1 + 1]
                    dp[i + 1] = dp[tmpLen - word.length()];
                }
            }
        }
        return dp[len];
    }
}
