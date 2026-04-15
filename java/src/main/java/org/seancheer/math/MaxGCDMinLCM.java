package org.seancheer.math;

/**
 * 自行训练题，求最大公约数和最小公倍数
 *
 * @author: seancheer
 * @date: 2021/9/5
 **/
public class MaxGCDMinLCM {
    public static void main(String[] args) {
        System.out.println("------------------------------maxGCD-------------------------");
        maxGCD();
        System.out.println("------------------------------minLCM-------------------------");
        minLCM();
    }

    /**
     * 求最大公约数
     *
     */
    private static void maxGCD() {
        int m = 15;
        int n = 25;
        //两种求解方式，第一种，递归式求解发
        int res = maxGCD1(m, n);
        int res2 = maxGCD2(m, n);
        System.out.println("res:" + res + "   res2:" + res2);

        m = 6;
        n = 7;
        res = maxGCD1(m, n);
        res2 = maxGCD2(m, n);
        System.out.println("res:" + res + "   res2:" + res2);
    }

    /**
     * 递归式求解：如果n比m大，那么第二次递归，m和n据会达到一个互换的效果
     *
     * @param m
     * @param n
     */
    private static int maxGCD1(int m, int n) {
        return n == 0 ? m : maxGCD1(n, m % n);
    }

    /**
     * 迭代式求解，除了暴力解法，该办法秉承着谁大减小谁的目的来求解
     *
     * @param m
     * @param n
     */
    private static int maxGCD2(int m, int n) {
        while (n != 0) {
            if (m > n) {
                m = m - n;
            } else {
                n = n - m;
            }
        }
        //为什么可以直接返回m呢？因为等于的情况下在else分支，所以只有n有可能减为0
        return m;
    }


    /**
     * 求最小公倍数，最小公倍数为两者相乘的结果然后除以最大公约数，很简单
     *
     */
    private static void minLCM() {
        int m = 15;
        int n = 25;
        int res = (m * n) / maxGCD1(m, n);
        System.out.println("res:" + res);

        m = 6;
        n = 7;
        res = (m * n) / maxGCD1(m, n);
        System.out.println("res:" + res);
    }
}
