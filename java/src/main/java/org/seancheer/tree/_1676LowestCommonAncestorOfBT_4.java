package org.seancheer.tree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 1676. Lowest Common Ancestor of a Binary Tree IV
 * Tree
 * Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 * <p>
 * Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pias a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node xto some leaf node.
 * <p>
 * Example 1:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 * Example 2:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 * ​
 * Example 3:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 * Example 4:​
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [0,1,2,3,4,5,6,7,8]
 * Output: 3
 * Explanation: The lowest common ancestor of all the nodes is the root node.
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * <p>
 * -109 <= Node.val <= 109
 * <p>
 * All Node.val are unique.
 * <p>
 * All nodes[i] will exist in the tree.
 * <p>
 * All nodes[i] are distinct.
 * <p>
 * 这次是确定多个节点的共同祖先，且这些节点一定都在树里面，并且树里面的每个值都是不一样的
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _1676LowestCommonAncestorOfBT_4 extends TreeParent {
    public static void main(String[] args) {
        var obj = new _1676LowestCommonAncestorOfBT_4();
        String str = "[3,5,1,6,2,0,8,null,null,7,4]";
        int[] target = new int[]{4, 7};
        TreeNode root = genTreeFromLevelOrder(str);
        var res = obj.lowestCommonAncestor(root,
                findNodesByVals(root, target));
        System.out.println("res:" + res); //2


        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        target = new int[]{1};
        root = genTreeFromLevelOrder(str);
        res = obj.lowestCommonAncestor(root,
                findNodesByVals(root, target));
        System.out.println("res:" + res); //1

        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        target = new int[]{7, 6, 2, 4};
        root = genTreeFromLevelOrder(str);
        res = obj.lowestCommonAncestor(root,
                findNodesByVals(root, target));
        System.out.println("res:" + res); //5

        str = "[3,5,1,6,2,0,8,null,null,7,4]";
        target = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        root = genTreeFromLevelOrder(str);
        res = obj.lowestCommonAncestor(root,
                findNodesByVals(root, target));
        System.out.println("res:" + res); //3

    }

    /**
     * 解题思路：其实就是{@link _236LowestCommonAncestorOfBT}的一个小小的扩展，找多个，不再找一个，同时236的解法有短路效果，
     * 如果要找的目标在一条路径上，那么可以避免向后继续查找，因为同一条路径上面的node，其最近祖先一定是第一次出现的目标
     *
     * @param root
     * @param nodes
     * @return
     */
    TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        if (root == null || null == nodes || nodes.isEmpty()) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        for (TreeNode node : nodes) {
            set.add(node.val);
        }

        return lowestCommonAncestorInternal(root, set);
    }


    TreeNode lowestCommonAncestorInternal(TreeNode root, Set<Integer> nodes) {
        if (null == root || nodes.contains(root.val)) {
            //发现目标，直接返回，不需要判断其儿子，有短路效果
            return root;
        }

        TreeNode left = lowestCommonAncestorInternal(root.left, nodes);
        TreeNode right = lowestCommonAncestorInternal(root.right, nodes);

        //虽然查找多个的话可能会存在这个条件满足多次，不过没关系，因为是递归，所以返回的永远都是能够最大包含所有nodes的
        if (null != left && null != right) {
            return root;
        }

        return (null != left) ? left : right;
    }

}
