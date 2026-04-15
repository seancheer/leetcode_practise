package org.seancheer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 * 注意和{@link _235LowestCommonAncestorOfBST}的区别，这是一个二叉树，而不是一个严格
 * 二叉查找树，节点之间没有大小关系，所以其的简单解法就不能在这里使用了
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _236LowestCommonAncestorOfBT extends TreeParent {
    public static void main(String[] args) {
        var obj = new _236LowestCommonAncestorOfBT();
        String str = "[3,5,1,6,2,0,8,null,null,7,4]";
        TreeNode root = genTreeFromLevelOrder(str);
        int pVal = 5;
        int qVal = 1;
        var res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        var res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //3

        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        root = genTreeFromLevelOrder(str);
        pVal = 5;
        qVal = 4;
        res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //5

        str = "[1,2]";
        root = genTreeFromLevelOrder(str);
        pVal = 1;
        qVal = 2;
        res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //1

        str = "[1,2,3,4,5]";
        root = genTreeFromLevelOrder(str);
        pVal = 4;
        qVal = 5;
        res = obj.lowestCommonAncestor(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestor2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //2
    }

    /**
     * 解题思路：注意nodeValue都是不一样的
     * 首先再树里面寻找p和q，同时记录访问过的足迹，然后得到两条足迹，从足迹开头遍历，寻找最后一个相同的node即可，就是最终的答案。
     * 解法2的思路一样，但是代码更加简洁，参考解法2
     *
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
     * 解题思路：注意nodeValue都是不一样的
     * 首先再树里面寻找p和q，同时记录访问过的足迹，然后得到两条足迹，从足迹开头遍历，寻找最后一个相同的node即可，就是最终的答案。
     * 解法2的思路一样，但是这个解法是直接遍历的过程中就确定了共同的祖先是哪个，不需要记录足迹再进行判断。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        //说明以root为起点，其左孩子和右孩子都找到了p和q，注意，p和q必然在其两边
        if(null != left && null != right){
            return root;
        }

        //有一个为null，属于p和q的公共祖先为p和q中的一个情况，此时返回的left或者right哪个不是null，哪个就是目标
        //其实下面的处理可以让root成功的返回到顶层，可以单步跟一下[1,2,3,4,5]这个用例，就明白是怎么回事了。
        return (null != left) ? left : right;
    }

}
