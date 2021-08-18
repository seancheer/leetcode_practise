package org.seancheer.tree;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * 树的一些相关的数据结构
 *
 * @author: seancheer
 * @date: 2021/8/17
 **/
public class TreeParent extends LeetCodeParent {
    /**
     * Definition for a binary tree node.
     * 二叉树的定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        /**
         * 深拷贝
         * @return
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            TreeNode cur = new TreeNode(val);
            if (null != left){
                cur.left = (TreeNode) left.clone();
            }
            if(null != right){
                cur.right = (TreeNode) right.clone();
            }
            return cur;
        }
    }

    /**
     * 中序遍历打印多个二叉树
     *
     * @param nodeLi
     * @return
     */
    protected static String toStringTreeInOrder(List<TreeNode> nodeLi) {
        if (null == nodeLi) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int sz = nodeLi.size();
        for (int i = 0; i < sz; ++i) {
            TreeNode root = nodeLi.get(i);
            sb.append(toStringTreeInOrder(root));
            if (i < sz - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 中序遍历打印二叉树
     *
     * @param node
     * @return
     */
    protected static String toStringTreeInOrder(TreeNode node) {
        if (null == node) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        toStringTreeInOrder(node, sb);
        //去除掉后面多余的符号
        String str = sb.substring(0, sb.length() - 2);
        return str + "]";
    }

    private static void toStringTreeInOrder(TreeNode node, StringBuilder sb) {
        if (null == node) {
            return;
        }

        toStringTreeInOrder(node.left, sb);
        sb.append(node.val).append(", ");
        toStringTreeInOrder(node.right, sb);
    }


    /**
     * 层次遍历打印二叉树
     *
     * @param node
     * @return
     */
    protected static String toStringTreeLevelOrder(TreeNode node) {
        if (null == node) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            int sz = q.size();
            int i = 0;
            //每次只打一层
            while (i < sz) {
                TreeNode cur = q.remove();
                sb.append(cur.val).append(", ");
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
                ++i;
            }
        }
        //去除掉后面多余的符号
        String str = sb.substring(0, sb.length() - 2);
        return str + "]";
    }
}
