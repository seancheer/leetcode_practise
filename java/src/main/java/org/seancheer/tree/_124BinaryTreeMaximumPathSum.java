package org.seancheer.tree;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 * 查找二叉树任意路径的最大值，因为二叉树的值可能会存在负值，所以需要找最大值路径
 *
 * @author: seancheer
 * @date: 2021/8/22
 **/
public class _124BinaryTreeMaximumPathSum extends TreeParent {
    public static void main(String[] args) {
        var obj = new _124BinaryTreeMaximumPathSum();
        String rootStr = "[1,2,3]";
        TreeNode treeRoot = genTreeFromLevelOrder(rootStr);
        var res = obj.maxPathSum(treeRoot);
        System.out.println("res:" + res); //6

        obj = new _124BinaryTreeMaximumPathSum();
        rootStr = "[-10,9,20,null,null,15,7]";
        treeRoot = genTreeFromLevelOrder(rootStr);
        res = obj.maxPathSum(treeRoot);
        System.out.println("res:" + res); //42
    }

    /**
     * 解题思路：采用后序遍历的方式，每到一个节点，那么我有如下选择：
     *         3
     *      /  \
     *     1    8
     *   /   \
     *  4     9
     * 假设到节点1，三种选择如下：
     * 1 1作为路径的一环（1的父亲3不参与路径），那么我们选择左孩子的最大和 + 右孩子的最大和 + 自身value，组成一个路径，记为path1Sum
     * 2 1作为路径的一环（1的父亲3参与路径），有三种选择：
     *   2.1 选择左孩子的最大和 + 自身value，组成一个路径，记为path2Sum
     *   2.2 选择右孩子的最大和 + 自身value，组成一个路径，记为path3Sumn
     *   2.3 选择自身作为路径的一点，不计算左右孩子，记为path4Sum
     * 3 不计算自身，左孩子和右孩子单独作为路径（其实是2.3的情况）
     *   3.1 左孩子单独作为路径，记为path5Sum
     *   3.2 右孩子单独作为路径，记为path6Sum
     * 在后序遍历的过程中，我们需要把情况2返回给上一层，因为如果parent如果要用上孙子节点，就必须过儿子节点，也就是2的情况
     * 从底向上解决该问题，这本质上是动态规划的思想
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }

        res = Integer.MIN_VALUE;
        maxPathSumInternal(root);
        return res;
    }

    private int res;

    private int maxPathSumInternal(TreeNode root) {
        if (null == root) {
            //因为题目的node.val的范围在[-1000,1000]，此举是为了防止溢出
            return -5000;
        }

        int leftMax = maxPathSumInternal(root.left);
        int rightMax = maxPathSumInternal(root.right);

        //此时有三种思路，必须加上其自身，这样子root的父亲就可以选择较大的路径作为基础路径，这里自身其实已经计算了，即为root.val
        int max = Math.max(root.val, Math.max(root.val + leftMax, root.val + rightMax));
        res = Math.max(res, max);

        //还有一种情况是不经过root的parent，即root的左子树和右子树组合成一个路径，不过该路径不能作为返回值返回给root的parent
        //因为如果路径要过root的parent，那么就意味着root的左子树和右子树只能选择一个路径（不能重复经过某个节点）
        res = Math.max(res, root.val + leftMax + rightMax);

        return (int)max;
    }
}
