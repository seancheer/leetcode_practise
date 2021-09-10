package org.seancheer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _235LowestCommonAncestorOfBST extends TreeParent {
    public static void main(String[] args) {
        var obj = new _235LowestCommonAncestorOfBST();
        String str = "[6,2,8,0,4,7,9,null,null,3,5]";
        TreeNode root = genTreeFromLevelOrder(str);
        int pVal = 2;
        int qVal = 8;
        var res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        var res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));

        System.out.println("res:" + res + "   res2:" + res2); //6

        str = "[6,2,8,0,4,7,9,null,null,3,5]";
        root = genTreeFromLevelOrder(str);
        pVal = 2;
        qVal = 4;
        res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));

        System.out.println("res:" + res + "   res2:" + res2); //2

        str = "[2,1]";
        root = genTreeFromLevelOrder(str);
        pVal = 2;
        qVal = 1;
        res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));

        System.out.println("res:" + res + "   res2:" + res2); //2
    }

    /**
     * 解题思路：注意nodeValue都是不一样的
     * 首先再树里面寻找p和q，同时记录访问过的足迹，然后得到两条足迹，从足迹开头遍历，寻找最后一个相同的node即可，就是最终的答案。
     * 这个是通用的做法，但是这道题的关键点在于其是一个合法的二叉树，我们有更快速的办法，请看解法2
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || null == q || null == p) {
            assert (false);
            return null;
        }

        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        trackTree(root, p, path1);
        trackTree(root, q, path2);
        assert (!path1.isEmpty() && !path2.isEmpty());
        //查找最后一个相同的节点
        int i = 0, j = 0;
        TreeNode res = null;
        //循环出来后res记录的就是答案
        while (i < path1.size() && j < path2.size() && path1.get(i) == path2.get(j)) {
            res = path1.get(i);
            ++i;
            ++j;
        }
        return res;
    }

    /**
     * 因为题目要求的是从root节点到对应节点的路径，因此采用前序遍历的方式
     *
     * @param root
     * @param target
     * @param path
     * @return
     */
    private boolean trackTree(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (null == root) {
            return false;
        }

        path.add(root);
        if (root == target) {
            return true;
        }

        //任意一条路通都可以
        if (trackTree(root.left, target, path) || trackTree(root.right, target, path)) {
            return true;
        }
        //这条路径没有找到目标，此路不通
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * 解题思路：严格合法的二叉树，那最早的公共祖先的值肯定介于p和q之间，如果某个节点不是其最早的公共祖先，就意味着q和p
     * 必然都在该节点的同一侧。利用这个特性，我们可以很快找到最低的公共祖先，注意p和q的公共祖先可能是其自身
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || null == q || null == p) {
            assert (false);
            return null;
        }

        //大于0，说明p和q要么都大于root.val要么都小于root.val
        //如果找到了，那么乘积就等于0了
        while((p.val - root.val) * (q.val - root.val) > 0){
            root = (p.val > root.val) ? root.right : root.left;
        }
        return root;
    }
}
