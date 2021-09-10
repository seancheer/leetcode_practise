package org.seancheer.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1650. Lowest Common Ancestor of a Binary Tree III
 * Tree
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * <p>
 * Each node will have a reference to its parent node. The definition for Node is below:
 * <p>
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 * <p>
 * Example 1:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * <p>
 * -10^9 <= Node.val <= 10^9
 * <p>
 * All Node.val are unique.
 * <p>
 * p != q
 * <p>
 * p and q exist in the tree.
 * 只是多了一个parent指针，然后查找最低公共祖先，要查找的节点一定存在于树中，node节点的值都不同
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _1650LowestCommonAncestorOfBT_3 extends TreeParent {
    public static void main(String[] args) {
        var obj = new _1650LowestCommonAncestorOfBT_3();
        String str = "[3,5,1,6,2,0,8,null,null,7,4]";
        TreeNode root = genTreeFromLevelOrder(str);
        int pVal = 5;
        int qVal = 1;
        var res = obj.lowestCommonAncestorII(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        var res2 = obj.lowestCommonAncestorII2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //3

        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        root = genTreeFromLevelOrder(str);
        pVal = 5;
        qVal = 4;
        res = obj.lowestCommonAncestorII(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestorII2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2); //5

        str = "[1,2]";
        root = genTreeFromLevelOrder(str);
        pVal = 1;
        qVal = 2;
        res = obj.lowestCommonAncestorII(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        res2 = obj.lowestCommonAncestorII2(root,
                findNodeByVal(root, pVal), findNodeByVal(root, qVal));
        System.out.println("res:" + res + "  res2:" + res2);//1
    }


    /**
     * 解题思路：root是多余的，其实没必要传入
     * A和B都同时往parent遍历，用Map存储 value -> TreeNode的映射，一旦发现对方的map中出现了自己路径上的
     * 节点，直接返回，就是最终的结果
     *
     * @param root: The root of the tree
     * @param A:    node in the tree
     * @param B:    node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode A, TreeNode B) {
        if (null == root || null == A || null == B) {
            return null;
        }

        Map<Integer, TreeNode> mapA = new HashMap<>();
        Map<Integer, TreeNode> mapB = new HashMap<>();
        TreeNode first = A, second = B;
        while (null != first || null != second) {
            if (null != first) {
                if (mapB.containsKey(first.val)) {
                    return mapB.get(first.val);
                }
                mapA.put(first.val, first);
                first = first.parent;
            }

            if (null != second) {
                if (mapA.containsKey(second.val)) {
                    return mapA.get(second.val);
                }
                mapB.put(second.val, second);
                second = second.parent;
            }
        }
        return null;
    }

    /**
     * 解题思路：root是多余的，其实没必要传入
     * 其实仔细看这道题，本质上就是找两个链表的第一个公共节点，那么思路就很简单了
     * first = A second = B
     * 1 让第一个指针first沿着parent往前遍历，如果first到了根节点，那么将first = B
     * 2 同理，second指针也是，到了根节点，将second = A
     * 那么，first和second一定会有相遇的一天，相遇的位置刚好就是最小公共祖先，因为此时两者走过的路程是一样长的，可以画图理解下
     *
     * @param root: The root of the tree
     * @param A:    node in the tree
     * @param B:    node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public TreeNode lowestCommonAncestorII2(TreeNode root, TreeNode A, TreeNode B) {
        if (null == root || null == A || null == B) {
            return null;
        }

        TreeNode first = A, second = B;
        while (first != second) {
            first = (null == first) ? B : first.parent;
            second = (null == second) ? A : second.parent;
        }
        return first;
    }
}
