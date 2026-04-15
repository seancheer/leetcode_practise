package org.seancheer;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * <p>
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 16
 * Output: true
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: false
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: true
 * Follow up: Could you solve it without loops/recursion?
 *
 * @author: seancheer
 * @date: 2021/7/29
 **/
public class _342PowerOfFour {
    public static void main(String[] args) {
        _342PowerOfFour obj = new _342PowerOfFour();
        int n = 16;
        var res = obj.isPowerOfFour(n);
        var res2 = obj.isPowerOfFour2(n);
        System.out.println("res:" + res + "   res2:" + res2); //true

        n = 5;
        res = obj.isPowerOfFour(n);
        res2 = obj.isPowerOfFour2(n);
        System.out.println("res:" + res + "   res2:" + res2); //false

        n = 1;
        res = obj.isPowerOfFour(n);
        res2 = obj.isPowerOfFour2(n);
        System.out.println("res:" + res + "   res2:" + res2);//true

        n = (int) Math.pow(4, 4);
        res = obj.isPowerOfFour(n);
        res2 = obj.isPowerOfFour2(n);
        System.out.println("res:" + res + "   res2:" + res2);//true
    }

    /**
     * 解题思路：要求不使用循环或者递归来解决这个问题
     * 位运算解决该问题：只要保证n中有一个1，且1的右边有偶数个0，就是满足要求的结果
     * n的范围在int范围内，且有可能为负值，负数和0不可能为4的幂次，所以直接返回false即可
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        int zeroOfRight = 0;
        int oneCount = 0;
        //统计最右边的0
        while ((n & 1) == 0) {
            ++zeroOfRight;
            n >>= 1;
        }

        if (zeroOfRight == 0 || zeroOfRight % 2 != 0) {
            return false;
        }

        //统计是否有多余的1
        while (n != 0) {
            if (oneCount >= 1) {
                return false;
            }
            ++oneCount;
            n >>= 1;
        }
        return true;
    }

    /**
     * 更巧妙的解法，4的幂次，其实就是1的位置出现在奇数位（从右往左，0-indexed），所以对于2的幂次非4的幂次，其1出现在偶数位，
     * 而0x55555555的二进制类似于01010101...，所以和其与一下，正好又排除了1出现在偶数位的情况，所以这个方法更适合
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
        //0x55555555 is to get rid of those power of 2 but not power of 4
        //so that the single 1 bit always appears at the odd position
    }
}
