package org.seancheer.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total to reach or exceed 100 wins.
 * <p>
 * What if we change the game so that players cannot re-use integers?
 * <p>
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 * <p>
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise, return false. Assume both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 * Output: false
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 * Example 2:
 * <p>
 * Input: maxChoosableInteger = 10, desiredTotal = 0
 * Output: true
 * Example 3:
 * <p>
 * Input: maxChoosableInteger = 10, desiredTotal = 1
 * Output: true
 * <p>
 * 每一个人选择一个数，谁先让池子里面的和达到目标值，谁就获胜
 * Constraints:
 * <p>
 * 1 <= maxChoosableInteger <= 20
 * 0 <= desiredTotal <= 300
 *
 * @author: seancheer
 * @date: 2021/7/15
 **/
public class _464CanIWin {
    public static void main(String[] args) {
        _464CanIWin obj = new _464CanIWin();
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        var res = obj.canIWin(maxChoosableInteger, desiredTotal);
        var res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //false

        maxChoosableInteger = 10;
        desiredTotal = 0;
        res = obj.canIWin(maxChoosableInteger, desiredTotal);
        res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //true

        maxChoosableInteger = 10;
        desiredTotal = 1;
        res = obj.canIWin(maxChoosableInteger, desiredTotal);
        res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //true

        maxChoosableInteger = 20;
        desiredTotal = 300;
        res = obj.canIWin(maxChoosableInteger, desiredTotal);
        res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //false

        maxChoosableInteger = 10;
        desiredTotal = 40;
        res = obj.canIWin(maxChoosableInteger, desiredTotal);
        res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //false

        maxChoosableInteger = 4;
        desiredTotal = 6;
        res = obj.canIWin(maxChoosableInteger, desiredTotal);
        res2 = obj.canIWin2(maxChoosableInteger, desiredTotal);
        System.out.println("res:" + res + "    res2:" + res2);  //true

    }

    /**
     * 解题思路：这道题其实不是传统的动态规划问题，更像是带memo的回溯法。
     * 没有取巧的办法，只能暴力去尝试选择每一个数字，然后挨个验证最后的结果，为了保证每一个玩家都是最优的结果，所以必须按照
     * 回溯法的思路，去挨个尝试，为了避免TLE的问题，使用map来记录状态
     * <p>
     * 主要注意的是：为什么回溯法在这里是可以成功的呢？为什么不分别记录两个玩家选择的状态呢？
     * 这是因为我们关注的是第一个玩家，因此递归调用helper的最上层返回的结果，就是第一个玩家的结果，这里有个小小的trick，可以观察
     * 下面的代码注释！
     * <p>
     * 这个解法最新的执行结果是超时，不可取！需要进行状态压缩操作，请看解法2！
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }

        //如果maxChoosableInteger的和小于desiredTotal，那么必须不会满足要求
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) {
            return false;
        }
        //因为maxChoosableInteger的最大值为20-
        boolean[] visited = new boolean[maxChoosableInteger + 1];
        Map<String, Boolean> memo = new HashMap<>();
        return helper(maxChoosableInteger, desiredTotal, memo, visited);
    }

    /**
     * 通过回溯的方式来求解，同时将memo设置为成员变量，保证每一个用例都可以用到。
     *
     * @return
     */
    private boolean helper(int maxChoosableInteger, int desiredTotal, Map<String, Boolean> memo, boolean[] visited) {
        //到我的时候，发现total已经小于等于0了，说明当前的玩家已经输了
        if (desiredTotal <= 0) {
            return false;
        }

        String key = Arrays.toString(visited);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        for (int num = 1; num <= maxChoosableInteger; ++num) {
            if (!visited[num]) {
                visited[num] = true;
                //说明下一个玩家不可能赢了，当前玩家胜利
                if (!helper(maxChoosableInteger, desiredTotal - num, memo, visited)) {
                    //这里需要注意的是,使用上面的key，需要保持一致。
                    memo.put(key, true);
                    //这里记得恢复状态
                    visited[num] = false;
                    //当前有必胜的选择，因此这里不继续递归了，直接返回
                    return true;
                }
                //说明这条路行不通，只能尝试下一个数字
                visited[num] = false;
            }
        }
        memo.put(key, false);
        return false;
    }



    /**
     * 解题思路：其实是解法1的升级版，采用状态压缩的方式，具体的压缩方式是使用位运算来存储在memo里面，由于
     * maxChoosableInteger最大值为20，所以完全可以用int（32位）来进行状态压缩操作。这样子可以解决TLE的问题，
     * 用空间换时间
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }

        //如果maxChoosableInteger的和小于desiredTotal，那么必须不会满足要求
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) {
            return false;
        }
        //因为maxChoosableInteger的最大值为20
        Boolean[] memo = new Boolean[1 << maxChoosableInteger];
        return helper2(maxChoosableInteger, desiredTotal, 0, memo);
    }

    private boolean helper2(int maxChoosableInteger, int desiredTotal, int state, Boolean[] memo) {
        if (desiredTotal <= 0){
            return false;
        }

        if (memo[state] != null){
            return memo[state];
        }

        for(int i = 0;i < maxChoosableInteger;++i){
            //说明这个值没有用过
            if ((state & (1 << i)) == 0){
                if (!helper2(maxChoosableInteger, desiredTotal - (i + 1), state | (1 << i), memo)){
                    memo[state] = true;
                    return true;
                }
            }
        }
        memo[state] = false;
        return false;
    }
}

