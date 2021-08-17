package org.seancheer.tree;

import org.seancheer.utils.LeetCodeParent;

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
    }
}
