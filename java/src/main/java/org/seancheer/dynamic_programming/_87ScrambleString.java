package org.seancheer.dynamic_programming;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 * <p>
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now and the result string is "rgeat" which is s2.
 * As there is one possible scenario that led s1 to be scrambled to s2, we return true.
 * Example 2:
 * <p>
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 * Example 3:
 * <p>
 * Input: s1 = "a", s2 = "a"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 and s2 consist of lower-case English letters.
 * 注意：一旦采取了一个index将字符串分割成x + y后，x和y继续分割，但是x的子串和y的子串是不可以交换位置的。
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _87ScrambleString {
    public static void main(String[] args) {
        var obj = new _87ScrambleString();
        String word1 = "great";
        String word2 = "rgeat";
        var res = obj.isScramble(word1, word2);
        System.out.println("res:" + res);  //true

        word1 = "a";
        word2 = "a";
        res = obj.isScramble(word1, word2);
        System.out.println("res:" + res);  //true

        word1 = "abcde";
        word2 = "caebd";
        res = obj.isScramble(word1, word2);
        System.out.println("res:" + res);  //false
    }


    /**
     * 解题思路：动态规划问题
     * dp[i][j][k]表示在s1中以i为起点，即子串[i, i + k)，s2以j为起点，即子串[j, j + k)，是否可以变换来满足要求。
     * 那么状态转移方程为：
     * 我们从1作为长度开始一直到k，首先为1：
     * 1 dp[i][j][1] && dp[i + 1][j + 1][k - 1]为true，说明dp[i][j][k]也为true，比如abcde和acdeb，此时很明显dp[0][0][1]是满足要求的，
     *   且dp[1][1][4]也是满足要求的，所以dp[0][0][5]也满足要求
     * 2 dp[i][j + k - 1][1] && dp[i + 1][j][k - 1]为true，说明dp[i][j][k]也为true，这个因为题目的算法可以交换子串位置，所以其目的是处理这种
     *   情况，比如abcde和eabcd，很明显dp[0][1][4]和dp[4][0][1]也为true，那么dp[i][j][k]也为true
     *
     * 最后判断一下dp[0][0][s1Len]是否为true，即为最终答案。
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 1 && s1.equals(s2)) {
            return true;
        }

        int s1Len = s1.length();
        int s2Len = s2.length();
        //初始状态
        boolean[][][] dp =new boolean[s1Len][s2Len][s1Len + 1];
        for(int i = 0;i < s1Len;++i){
            for(int j =0;j < s2Len;++j){
                dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
            }
        }
        for (int k = 2; k <= s1Len; ++k) {
            for (int i = 0; i + k <= s1Len; ++i) {
                for (int j = 0; j + k <= s2Len; ++j) {
                    dp[i][j][k] = false;
                    for(int l = 1;l < k;++l){
                        //因为子串可以颠倒，所以这里一个要正方向来，一个还要反方向来判断
                        if ((dp[i][j][l] && dp[i + l][j + l][k - l]) || (dp[i][j + k - l][l] && dp[i + l][j][k - l])){
                            dp[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][s1Len];
    }

}
