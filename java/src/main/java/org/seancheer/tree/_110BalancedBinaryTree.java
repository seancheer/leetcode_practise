package org.seancheer.tree;

/**
 *Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 *
 * Input: root = []
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 * @author: seancheer
 * @date: 2021/8/21
 **/
public class _110BalancedBinaryTree extends TreeParent{
    public static void main(String[] args) {
        var obj = new _110BalancedBinaryTree();
        String root = "[3,9,20,null,null,15,7]";
        var res = obj.isBalanced(genTreeFromLevelOrder(root));
        System.out.println("res:" + res); //true

        root = "[1,2,2,3,3,null,null,4,4]";
        res = obj.isBalanced(genTreeFromLevelOrder(root));
        System.out.println("res:" + res); //false

        root = "[]";
        res = obj.isBalanced(genTreeFromLevelOrder(root));
        System.out.println("res:" + res); //true
    }

    /**
     * 解题思路：很简单，就是判断是不是一颗合法的平衡二叉树。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (null == root){
            return true;
        }

        res = true;
        isBalancedInternal(root);
        return res;
    }

    private boolean res = true;

    /**
     * 递归计算高度
     * @param root
     * @return
     */
    private int isBalancedInternal(TreeNode root){
        if(null == root){
            return 0;
        }

        int leftHeight = 1 + isBalancedInternal(root.left);
        int rightHeight = 1 + isBalancedInternal(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1){
            res = false;
        }
        //该节点的深度按照最大值计算
        return Math.max(leftHeight, rightHeight);
    }
}
