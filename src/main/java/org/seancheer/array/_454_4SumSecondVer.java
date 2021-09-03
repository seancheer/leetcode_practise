package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 454. 4Sum II
 * <p>
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class _454_4SumSecondVer extends LeetCodeParent {
    public static void main(String[] args) {
        _454_4SumSecondVer obj = new _454_4SumSecondVer();
        int[] a = new int[]{1, 2};
        int[] b = new int[]{-2, -1};
        int[] c = new int[]{-1, 2};
        int[] d = new int[]{0, 2};
        var res = obj.fourSumCount(a, b, c, d);
        System.out.println("res : " + res);
    }

    /**
     * 查找结果为0的个数
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (null == A || null == B || null == C || null == D) {
            return 0;
        }

        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        List<int[]> arrList = new ArrayList<>();
        arrList.add(A);
        arrList.add(B);
        arrList.add(C);
        arrList.add(D);
        res = 0;
        fourSumInternal(arrList, 0, 0);
        return res;
    }

    private static int res = 0;

    /**
     * 递归进行处理，估计会超时
     * 果然超时了。QAQ
     * @param arrList
     * @param pos
     * @param sum
     */
    private void fourSumInternal(List<int[]> arrList, int pos, int sum) {
        if (pos == arrList.size()) {
            if (0 == sum) {
                ++res;
            }

            return;
        }

        int tmp = sum;
        int[] curArr = arrList.get(pos);
        for (int i = 0; i < curArr.length; ++i) {
            tmp += curArr[i];
            fourSumInternal(arrList, pos + 1, tmp);
            tmp -= curArr[i];
        }
    }

}
