package org.seancheer.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * An n-bit gray code sequence is a sequence of 2n integers where:
 * <p>
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 16
 * <p>
 * 要求每个相邻位都只差一个，开头和末尾也是，且数字不能超过一次，必须以0开头，而且(1 << n)以内的所有数字都要出现才行。
 *
 * @author: seancheer
 * @date: 2021/8/17
 **/
public class _89GrayCode {
    public static void main(String[] args) {
        var obj = new _89GrayCode();
        int n = 2;
        var res = obj.grayCode(n);
        var res2 = obj.grayCode2(n);
        var res3 = obj.grayCode3(n); //该思路最容易理解
        System.out.println("res:" + res + "  res2:" + res2 + "  res3:" + res3);  //[0,1,3,2] [0,2,3,1]

        n = 3;
        res = obj.grayCode(n);
        res2 = obj.grayCode2(n);
        res3 = obj.grayCode3(n);
        System.out.println("res:" + res + "  res2:" + res2 + "  res3:" + res3);  //[0, 1, 3, 2, 6, 7, 5, 4]
    }

    /**
     * 解题思路：回溯法
     * 挨个尝试所有的情况，当所有数字都选择了之后，然后判断开头和结尾是否满足要求，满足要求直接返回；
     * 该方法TLE了，必须想其他办法。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        res.add(0);
        int len = (1 << n);
        boolean[] visited = new boolean[len];
        visited[0] = true;
        helper(0, visited, res);
        return res;
    }

    /**
     * 递归进行处理
     *
     * @return
     */
    private boolean helper(int idx, boolean[] visited, List<Integer> res) {
        int len = visited.length;
        int cur = idx;
        boolean allVisited = true;
        for (int i = 1; i < len; ++i) {
            if (!visited[i]) {
                allVisited = false;
                if (differ1Bit(cur, i)) {
                    visited[i] = true;
                    res.add(i);
                    if (helper(i, visited, res)) {
                        return true;
                    }
                    res.remove(res.size() - 1);
                    visited[i] = false;
                }
            }
        }

        //需要判断下开头和末尾
        if (allVisited) {
            return differ1Bit(0, res.get(res.size() - 1));
        }
        return false;
    }

    private boolean differ1Bit(int m, int n) {
        int count = 0;
        while (0 != m || 0 != n) {
            if ((m & 1) != (n & 1)) {
                ++count;
            }

            if (count > 1) {
                return false;
            }
            m >>= 1;
            n >>= 1;
        }
        return true;
    }


    /**
     * 解题思路：一个更加巧妙的回溯法
     * 每次都是在前一个的基础上只变换一位来实现的（通过亦或算法），从最低位开始，每次往前摞一位，然后再继续控制更低位的变化，
     * 因为最高位是第一次变为1，所以后面低位的变化都决定不会再重复。
     * 这个回溯法厉害的地方在于其没有多余的计算，每次n==0的时候，都是正确的解，比上面的解法效率高很多。
     * 举个例子，这种解法的步骤如下：（注意代码里面异或的都是n - 1）
     * 1 初始值为000，此时n = 1, 接下来有异或(1 << 0)之后，结果为001
     * 2 接下来在01的基础上，此时n = 2, 异或(1 << 1)之后，结果为011，011继续作为基础往下递归，此时n由2变为1（因为上面传入了n - 1)，所以再次
     * 异或(1 << 0)之后，结果为010
     * 3 在10的基础上，此时n = 3, 异或(1 << 2)之后，变为110，110继续往下递归，此时n由3变为2，因为是递归，所以先进后出，因此算法将
     * 先异或(1 << 0)，此时，110变为111， 弹出来之后，继续异或(1 << 1)，此时，变为101，而101目前的位置n=2，所以101还会异或n=1的情况，
     * 将会变为100
     * 4 在100的基础上继续执行下去。
     * <p>
     * 所以最终该算法的变化过程如下：
     * 000 -> 001 -> 011 -> 010 -> 110 -> 111 -> 101 -> 100
     * 很巧妙的每次只变化一位，达到最终的效果
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode2(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        nums = 0;
        helper2(n, res);
        return res;
    }

    private int nums = 0;

    private void helper2(int n, List<Integer> res) {
        if (n == 0) {
            res.add(nums);
            return;
        }

        helper2(n - 1, res);
        //下面也是必要的，其保证每次都只变换一位
        nums ^= (1 << (n - 1));
        helper2(n - 1, res);
    }


    /**
     * 解题思路：最容易理解的一种思路，来直接看例子：
     * 1 n = 2的时候：000 001 011 010
     * 2 n = 3的时候：110 111 101 100
     * 看到没？n = 3其实就是在n = 2的基础上添加了一个高位1，且为了满足题目要求，我们添加的顺序是倒序的，按照
     * 这种思路得到最终结果
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode3(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; ++i) {
            int sz = res.size();
            for (int j = sz - 1; j >= 0; --j) {
                //倒序将更高位变为1即可
                res.add(res.get(j) | (1 << i));
            }
        }
        return res;
    }
}
