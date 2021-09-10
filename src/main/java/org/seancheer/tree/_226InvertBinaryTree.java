package org.seancheer.tree;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * @author: seancheer
 * @date: 2021/8/26
 **/
public class _226InvertBinaryTree extends TreeParent {
    public static void main(String[] args) {
        var obj = new _226InvertBinaryTree();
        String rootStr = " [4,2,7,1,3,6,9]";
        TreeNode root = genTreeFromLevelOrder(rootStr);
        obj.invertTree(root);
        System.out.println("res:" + toStringTreeLevelOrder(root)); //[4,7,2,9,6,3,1]

        rootStr = "[2,1,3]";
        root = genTreeFromLevelOrder(rootStr);
        obj.invertTree(root);
        System.out.println("res:" + toStringTreeLevelOrder(root));

        rootStr = "[1,2]";
        root = genTreeFromLevelOrder(rootStr);
        obj.invertTree(root);
        System.out.println("res:" + toStringTreeLevelOrder(root));
    }


    /**
     * 递归解决问题即可
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
