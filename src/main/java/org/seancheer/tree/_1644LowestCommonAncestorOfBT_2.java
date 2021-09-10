package org.seancheer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 * Example 3:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _1644LowestCommonAncestorOfBT_2 extends TreeParent {
    public static void main(String[] args) {
        var obj = new _1644LowestCommonAncestorOfBT_2();
        String str = "[3,5,1,6,2,0,8,null,null,7,4]";
        TreeNode root = genTreeFromLevelOrder(str);
        int pVal = 5;
        int qVal = 1;
        var res = obj.lowestCommonAncestor3(root,
                createNodeIfNotFound(root, pVal), createNodeIfNotFound(root, qVal));
        System.out.println("res:" + res); //3


        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        root = genTreeFromLevelOrder(str);
        pVal = 5;
        qVal = 4;
        res = obj.lowestCommonAncestor3(root,
                createNodeIfNotFound(root, pVal), createNodeIfNotFound(root, qVal));
        System.out.println("res:" + res); //5

        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        root = genTreeFromLevelOrder(str);
        pVal = 5;
        qVal = 10;
        res = obj.lowestCommonAncestor3(root,
                createNodeIfNotFound(root, pVal), createNodeIfNotFound(root, qVal));
        System.out.println("res:" + res); //null
    }

    /**
     * 解题思路：和{@link _236LowestCommonAncestorOfBT}不同的是，A和B有可能不在树中，是其的一个变种，我们对236题解法的
     * 改造如下：
     * 1 增加两个成员变量，用来标识是否找到了A和B
     * 2 将之前的前序遍历改为后序遍历，这样子就可以保证再找到了A之后，也能够判断A的子树是否有B这种情况
     * 3 最后如果都找到了，返回结果，有一个没找到，返回null
     *
     * 还有一种通用的解法，参考{@link _236LowestCommonAncestorOfBT}里面的解法，即先查找A和B，在查找的过程中记录路径，
     * 然后两条路径的最后一个相同的node就是要找的答案。这里就不写了
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        if (null == root || null == A || B == null){
            return null;
        }

        foundA = foundB = false;
        TreeNode res = lowestCommonAncestor3Internal(root, A, B);
        //有任意一个没有找到，直接返回null，这里不能直接返回res
        if (!foundA || !foundB){
            return null;
        }
        return res;
    }

    private boolean foundA;
    private boolean foundB;

    /**
     * 递归进行处理
     * @param root
     * @param A
     * @param B
     * @return
     */
    private TreeNode lowestCommonAncestor3Internal(TreeNode root, TreeNode A, TreeNode B){
        if (null == root){
            return root;
        }

        TreeNode left = lowestCommonAncestor3Internal(root.left, A, B);
        TreeNode right = lowestCommonAncestor3Internal(root.right, A, B);

        //之前这段代码是再入口的if处，该题将前序遍历改为后续遍历，这样子可以保证root是A或者B的情况下，（假设是A）能够判断A的子树是否
        //存在B这种情况
        if (root == A || root == B){
            //此举可以保证foundA一旦被置为true，就永远为true，用简单的代码保证了foundA找到了就不再变化，避免后续又变为false
            foundA = (root == A) || foundA;
            foundB = (root == B) || foundB;
            return root;
        }

        if (null != left  && null != right){
            return root;
        }
        return (null != left) ? left : right;
    }


















    /**
     * 工具函数，无需理会
     * 尝试从tree里面寻找val，如果没有找到的话，直接new一个TreeNode，这道题会用到。
     *
     * @param root
     * @param val
     * @return
     */
    private static TreeNode createNodeIfNotFound(TreeNode root, int val) {
        TreeNode res = findNodeByVal(root, val);
        if (null == res) {
            return new TreeNode(val);
        }
        return res;
    }
}
