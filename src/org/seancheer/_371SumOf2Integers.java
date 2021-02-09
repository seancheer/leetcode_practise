package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = -2, b = 3
 * Output: 1
 * @author: seancheer
 * @date: 2021/2/7
 **/
public class _371SumOf2Integers extends LeetCodeParent {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        _371SumOf2Integers obj = new _371SumOf2Integers();
        int res = obj.getSum(a, b);
        System.out.println("res:" + res);

        a = -2;
        b = 3;
        res = obj.getSum(a, b);
        System.out.println("res:" + res);
    }

    /**
     * 采用位运算的方式来实现，也就是计算机底层实现的方式，具体的解析可以看这里：https://www.jianshu.com/p/7bba031b11e7
     * 其实是加法的原理。
     * 首先1 不进位进行相加（本质上是亦或操作）得a，2 然后单独存储进位（本质上是与操作左移1位）的结果b，然后进行1，2操作，
     * 一直到b为0为止。
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (b == 0){
            return a;
        }

        return getSum(a ^ b, (a & b) << 1);
    }
}
