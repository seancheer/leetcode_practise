package org.seancheer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
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
 * Input: root = [1,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 * @author: seancheer
 * @date: 2021/8/27
 **/
public class _297SerializeAndDeserializeBinaryTree extends TreeParent {
    public static void main(String[] args) {
        String rootStr = "[1,2,3,null,null,4,5]";
        TreeNode root = genTreeFromLevelOrder(rootStr);
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode ans = deser.deserialize(ser.serialize(root));
        System.out.println(String.format("origin:%s  after:%s",
                toStringTreeLevelOrder(root), toStringTreeLevelOrder(ans)));

        rootStr = "[]";
        root = genTreeFromLevelOrder(rootStr);
        ser = new Codec();
        deser = new Codec();
        ans = deser.deserialize(ser.serialize(root));
        System.out.println(String.format("origin:%s  after:%s",
                toStringTreeLevelOrder(root), toStringTreeLevelOrder(ans)));

        rootStr = "[1]";
        root = genTreeFromLevelOrder(rootStr);
        ser = new Codec();
        deser = new Codec();
        ans = deser.deserialize(ser.serialize(root));
        System.out.println(String.format("origin:%s  after:%s",
                toStringTreeLevelOrder(root), toStringTreeLevelOrder(ans)));

        rootStr = "[1,2]";
        root = genTreeFromLevelOrder(rootStr);
        ser = new Codec();
        deser = new Codec();
        ans = deser.deserialize(ser.serialize(root));
        System.out.println(String.format("origin:%s  after:%s",
                toStringTreeLevelOrder(root), toStringTreeLevelOrder(ans)));
    }


    /**
     * 解题思路：为了恢复到二叉树原来的样子，需要我们注意的是序列化的过程中必须记录树的结构，否则的话，会导致反序列化出来的
     * 结构不一样，就按照leetcode采用的方式来序列化和反序列化吧，使用层次遍历的方式。
     */
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (null == root) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            sb.append(root.val).append(",");
            while (!queue.isEmpty()) {
                int sz = queue.size();
                int i = 0;
                while (i < sz) {
                    TreeNode cur = queue.remove();
                    if (null == cur.left) {
                        sb.append("null,");
                    } else {
                        queue.add(cur.left);
                        sb.append(cur.left.val).append(",");
                    }

                    if (null == cur.right) {
                        //注意，如果是null，那么不添加到队列里面，因为null是终点，否则就无限循环了
                        sb.append("null,");
                    } else {
                        queue.add(cur.right);
                        sb.append(cur.right.val).append(",");
                    }
                    ++i;
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        /**
         * 按照层次遍历的方式把所有的节点都恢复出来
         * @param data
         * @return
         */
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (null == data || data.isEmpty()) {
                return null;
            }
            String[] datas = data.split(",");
            Queue<TreeNode> nodes = new LinkedList<>();
            int i = 0;
            TreeNode root = new TreeNode(Integer.parseInt(datas[i++]));
            nodes.add(root);
            while (i < datas.length) {
                int j = 0;
                int sz = nodes.size();
                while (j < sz && i < datas.length) {
                    TreeNode parent = nodes.remove();
                    String left = datas[i++];
                    String right = datas[i++];
                    if (!"null".equalsIgnoreCase(left)) {
                        TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                        parent.left = leftNode;
                        nodes.add(leftNode);
                    }

                    if (!"null".equalsIgnoreCase(right)) {
                        TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                        parent.right = rightNode;
                        nodes.add(rightNode);
                    }
                    ++j;
                }
            }
            return root;
        }
    }
}
