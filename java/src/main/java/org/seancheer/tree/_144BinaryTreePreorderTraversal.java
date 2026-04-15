package org.seancheer.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * Example 4:
 * <p>
 * <p>
 * Input: root = [1,2]
 * Output: [1,2]
 * Example 5:
 * <p>
 * <p>
 * Input: root = [1,null,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * 系列题目：{@link _145BinaryTreePostorderTraversal}  {@link _94BinaryTreeInorderTraversal}
 * @author: seancheer
 * @date: 2021/8/23
 **/

public class _144BinaryTreePreorderTraversal extends TreeParent {

    public static void main(String[] args) {
        var obj = new _144BinaryTreePreorderTraversal();
        String tree = "[1,null,2,3]";
        TreeNode root = genTreeFromLevelOrder(tree);
        var res = obj.preorderTraversal(root);
        var res2 = obj.preorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2); //[1,2,3]

        tree = "[1,null,2]";
        root = genTreeFromLevelOrder(tree);
        res = obj.preorderTraversal(root);
        res2 = obj.preorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[1,2]

        tree = "[1]";
        root = genTreeFromLevelOrder(tree);
        res = obj.preorderTraversal(root);
        res2 = obj.preorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[1]

        tree = "[-10,9,20,null,null,15,7]";
        root = genTreeFromLevelOrder(tree);
        res = obj.preorderTraversal(root);
        res2 = obj.preorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[-10, 9, 20, 15, 7]

        tree = "[1,4,3,2]";
        root = genTreeFromLevelOrder(tree);
        res = obj.preorderTraversal(root);
        res2 = obj.preorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[1, 4, 2, 3]
    }

    /**
     * 解题思路：写两个版本，一个递归一个迭代
     * 递归版本
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (null == root){
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        preorderTraversalInternal(root, res);
        return res;
    }

    private void preorderTraversalInternal(TreeNode root, List<Integer> res){
        if (null == root){
            return;
        }

        res.add(root.val);
        preorderTraversalInternal(root.left, res);
        preorderTraversalInternal(root.right, res);
    }


    /**
     * 解题思路：写两个版本，一个递归一个迭代
     * 迭代版本
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (null == root){
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            //按照相反的顺序加入，因为要先进后出
            if (null != cur.right){
                stack.push(cur.right);
            }

            if (null != cur.left){
                stack.push(cur.left);
            }
        }
        return res;
    }
}
