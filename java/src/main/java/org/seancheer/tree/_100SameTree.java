package org.seancheer.tree;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 * <p>
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both trees is in the range [0, 100].
 * -104 <= Node.val <= 104
 *
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _100SameTree extends TreeParent {

    public static void main(String[] args) {
        var obj = new _100SameTree();
        String p = "[1,2,3]";
        String q = "[1,2,3]";
        var res = obj.isSameTree(genTreeFromLevelOrder(p), genTreeFromLevelOrder(q));
        System.out.println("res:" + res); //true

        p = "[1,2]";
        q = "[1,null, 2]";
        res = obj.isSameTree(genTreeFromLevelOrder(p), genTreeFromLevelOrder(q));
        System.out.println("res:" + res); //false

        p = "[1,2,1]";
        q = "[1,1,2]";
        res = obj.isSameTree(genTreeFromLevelOrder(p), genTreeFromLevelOrder(q));
        System.out.println("res:" + res); //false
    }

    /**
     * 解题思路：直接通过中序遍历的方式，
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeInternal(p, q);
    }

    private boolean isSameTreeInternal(TreeNode p, TreeNode q) {
        if (null == p && q == null) {
            return true;
        } else if (p == null || q == null) {
            return  false;
        }

        //短路判断，只要任何一个条件成立，就返回false，说明两棵树的结构不同
        if (!isSameTreeInternal(p.left, q.left) || p.val != q.val || !isSameTreeInternal(p.right, q.right)){
            return false;
        }
        return true;
    }
}
