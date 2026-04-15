package org.seancheer.tree;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 5
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 19
 *
 * @author: seancheer
 * @date: 2021/8/18
 **/
public class _96UniqueBinarySearchTrees extends TreeParent {

    public static void main(String[] args) {
        var obj = new _96UniqueBinarySearchTrees();
        int n = 3;
        var res = obj.numTrees(n);
        System.out.println("res:" + res); //5

        n = 1;
        res = obj.numTrees(n);
        System.out.println("res:" + res); //1

        n = 5;
        res = obj.numTrees(n);
        System.out.println("res:" + res); //42
    }

    /**
     * 求解1...n有多少个唯一的二叉树结构
     * 这是一个卡特兰数，递推公式为：
     * dp[i] = dp[0] * dp[i - 1] + dp[1] * dp[i - 2] + dp[2] * dp[i - 3] + ...  + dp[i - 1] * dp[0];
     * 详细解释：
     * 对于任意一个序列：[1,2,3,4,5,6,7]，所有unique二叉树的个数为：以1为root，以2为root，以3为root...以7为root
     * 的二叉树的总和，假设，对于以3为root的二叉树，那么其左子树为[1,2]，右子树为[4,5,6,7]，注意，该问题转变为了
     * 求解左子树和右子树总个数的问题，求解出来后，两者相乘即为最终的结构。其中[4,5,6,7]的结构数其实和[1,2,3,4]
     * 的结构数是一样的，因此以3为root的二叉树数量为: dp[2] * dp[4]。
     * 以此类推，这就是一个标准的卡特兰数
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] += (dp[j - 1] * dp[i - j]);
            }
        }
        return dp[n];
    }
}
