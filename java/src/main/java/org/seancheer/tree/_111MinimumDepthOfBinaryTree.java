package org.seancheer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * <p>
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 105].
 * -1000 <= Node.val <= 1000
 *
 * @author: seancheer
 * @date: 2021/8/25
 **/
public class _111MinimumDepthOfBinaryTree extends TreeParent {
    public static void main(String[] args) {
        var obj = new _111MinimumDepthOfBinaryTree();
        String rootStr = "[3,9,20,null,null,15,7]";
        TreeNode root = genTreeFromLevelOrder(rootStr);
        var res = obj.minDepth(root);
        var res2 = obj.minDepth2(root);
        System.out.println("res:" + res + "   res2:" + res2); //2

        rootStr = "[2,null,3,null,4,null,5,null,6]";
        root = genTreeFromLevelOrder(rootStr);
        res = obj.minDepth(root);
        res2 = obj.minDepth2(root);
        System.out.println("res:" + res + "   res2:" + res2); //5
    }

    /**
     * 解题思路：递归的思路，遍历到叶子节点，然后统计最小的即可
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }

        res = Integer.MAX_VALUE;
        minDepthInternal(root, 1);
        return res;
    }

    private int res;

    private void minDepthInternal(TreeNode root, int depth) {
        if (null == root) {
            return;
        }

        if (null == root.left && null == root.right) {
            res = Math.min(res, depth);
            return;
        }

        minDepthInternal(root.left, 1 + depth);
        minDepthInternal(root.right, 1 + depth);
    }


    /**
     * 解题思路：层次遍历，如果发现某个节点是叶子节点，直接返回结果
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            ++res;
            int size = queue.size();
            int i = 0;
            while (i < size) {
                TreeNode cur = queue.remove();
                if (null == cur.left && null == cur.right) {
                    return res;
                }
                if (null != cur.left) {
                    queue.add(cur.left);
                }

                if (null != cur.right) {
                    queue.add(cur.right);
                }
                ++i;
            }
        }
        return res;
    }


}
