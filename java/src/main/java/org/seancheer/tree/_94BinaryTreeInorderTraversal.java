package org.seancheer.tree;

import javax.swing.*;
import java.nio.channels.SelectionKey;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
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
 * Output: [2,1]
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
 * <p>
 * 要求实现迭代和递归两种方式
 *
 * 系列题目：{@link _144BinaryTreePreorderTraversal}  {@link _145BinaryTreePostorderTraversal}
 * @author: seancheer
 * @date: 2021/8/18
 **/
public class _94BinaryTreeInorderTraversal extends TreeParent {

    /**
     * 这道题用例不好构造，请直接在题目页面进行测试
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * @param args
     */
    public static void main(String[] args) {
        var obj = new _94BinaryTreeInorderTraversal();
        TreeNode root = new TreeNode(1);
        TreeNode child = new TreeNode(2);
        root.right = child;
        child.left = new TreeNode(3);
        var res = obj.inorderTraversal(root);
        var res2 = obj.inorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2); //[1,3,2]
    }

    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode node, List<Integer> res) {
        if (null == node) {
            return;
        }

        inorderTraversal(node.left, res);
        res.add(node.val);
        inorderTraversal(node.right, res);
    }


    /**
     * 迭代解法，使用栈帮助遍历，每次将最左边的孩子依次加入到栈中，然后弹栈，再处理栈顶的右孩子分支
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        //使用栈来模拟递归的调用方法
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(null != node){
            stack.push(node);
            node = node.left;
        }

        //将left全部加入后，开始pop栈
        while(!stack.isEmpty()){
            node = stack.pop();
            res.add(node.val);
            //开始遍历右边分支，将右孩子的最左边依次加入到栈中
            node = node.right;
            while(null != node){
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }
}
