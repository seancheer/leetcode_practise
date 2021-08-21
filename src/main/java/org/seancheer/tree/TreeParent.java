package org.seancheer.tree;

import org.seancheer.utils.LeetCodeParent;

import java.util.ArrayList;
import java.util.LinkedList;
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
        TreeNode parent;

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

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public TreeNode getParent() {
            return parent;
        }

        /**
         * 深拷贝
         *
         * @return
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            TreeNode cur = new TreeNode(val);
            if (null != left) {
                cur.left = (TreeNode) left.clone();
            }
            if (null != right) {
                cur.right = (TreeNode) right.clone();
            }
            return cur;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    static class ParentTreeNode extends TreeNode {
    }


    /**
     * 某些特殊题需要使用到的类
     */
    static class Node extends TreeNode{
        public Node next;
        public Node left;
        public Node right;
        public Node parent;
        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node(Object val) {
        }

        @Override
        public void setLeft(TreeNode left) {
            this.left = (Node) left;
        }

        @Override
        public void setRight(TreeNode right) {
            this.right = (Node) right;
        }

        @Override
        public void setParent(TreeNode parent) {
            this.parent = (Node) parent;
        }

        @Override
        public Node getLeft() {
            return left;
        }

        @Override
        public Node getRight() {
            return right;
        }

        @Override
        public Node getParent() {
            return parent;
        }

        /**
         * 构造工厂
         * @param str
         * @return
         */
        public static Node genTreeFromLevelOrder(String str) {
            return (Node) TreeParent.genTreeFromLevelOrder(str, Node::new);
        }
    };

    /**
     * 根据value从树中查找该value第一次出现的node，一次性查找多个value
     *
     * @param root
     * @param vals
     * @return
     */
    protected static List<TreeNode> findNodesByVals(TreeNode root, int[] vals) {
        List<TreeNode> nodes = new ArrayList<>();
        for(int num : vals){
            nodes.add(findNodeByVal(root, num));
        }
        return nodes;
    }

    /**
     * 根据value从树中查找该value第一次出现的node
     *
     * @param root
     * @param val
     * @return
     */
    protected static TreeNode findNodeByVal(TreeNode root, int val) {
        if (null == root) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode res = findNodeByVal(root.left, val);
        if (null != res) {
            return res;
        }
        res = findNodeByVal(root.right, val);
        if (null != res) {
            return res;
        }
        return null;
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
     * 层次遍历打印二叉树，除了最后一层的null不打印之外，其他层的null都会打印出来，比如
     * 3
     * /  \
     * 1    8
     * /   \
     * 4     9
     * 结果将会是[3,1,8,null,null,4,9]，最后一层的孩子null不打印，但是1的孩子null是会打印出来的
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

        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            int sz = q.size();
            int i = 0;
            List<TreeNode> curLevel = new ArrayList<>();
            boolean isLastLevel = true;
            //每次只打一层
            while (i < sz) {
                TreeNode cur = q.remove();
                curLevel.add(cur);
                if (null != cur && (null != cur.left || null != cur.right)) {
                    isLastLevel = false;
                }
                ++i;
            }

            for (TreeNode cur : curLevel) {
                if (null != cur) {
                    sb.append(cur.val).append(", ");
                    //最后一层的儿子全都是null，所以就不加入到队列里面了
                    if (!isLastLevel) {
                        q.add(cur.left);
                        q.add(cur.right);
                    }
                } else {
                    sb.append("null").append(", ");
                }
            }
        }
        //去除掉后面多余的符号
        String str = sb.substring(0, sb.length() - 2);
        return str + "]";
    }

    /**
     * 根据层次遍历的结果把树构造出来，str的形式必须如下所示：
     * [1,null,2]
     * 对应的二叉树结构为
     * 1
     * \
     * 2
     * 默认创建TreeNode
     * @param str
     * @return
     */
    protected static TreeNode genTreeFromLevelOrder(String str) {
        return genTreeFromLevelOrder(str, TreeNode::new);
    }

    /**
     * 根据层次遍历的结果把树构造出来，str的形式必须如下所示：
     * [1,null,2]
     * 对应的二叉树结构为
     * 1
     * \
     * 2
     *
     * @param str
     * @return
     */
    protected static TreeNode genTreeFromLevelOrder(String str, TreeNodeCreator creator) {
        if (null == str || str.isEmpty()) {
            return null;
        }
        str = str.trim().replaceAll(" |\t", "");
        //去掉前后的[]
        str = str.substring(1, str.length() - 1);
        if (str.isEmpty()) {
            return null;
        }
        String[] strLi = str.split(",");
        int len = strLi.length;
        int i = 0;
        Queue<TreeNode> q = new LinkedList<>();
        if (strLi[i].equalsIgnoreCase("null")) {
            return null;
        }

        TreeNode root = creator.newInstance(Integer.parseInt(strLi[i++]));
        q.add(root);
        while (i < len) {
            int sz = q.size();
            int j = 0;
            while (j < sz) {
                TreeNode cur = q.remove();
                TreeNode left = null, right = null;
                if (i < len) {
                    String curVal = strLi[i++];
                    if (!curVal.equalsIgnoreCase("null")) {
                        left = creator.newInstance(Integer.parseInt(curVal));
                        q.add(left);
                    }
                }

                if (i < len) {
                    String curVal = strLi[i++];
                    if (!curVal.equalsIgnoreCase("null")) {
                        right = creator.newInstance(Integer.parseInt(curVal));
                        q.add(right);
                    }
                }

                cur.setLeft(left);
                cur.setRight(right);
                if (null != left) {
                    left.setParent(cur);
                }
                if (null != right) {
                    right.setParent(cur);
                }
                j++;
            }
        }
        return root;
    }

    /**
     * TreeNode的创建接口
     */
    interface TreeNodeCreator{
        TreeNode newInstance(int val);
    }

    /**
     * 测试程序
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String str = "[3,1,4,null,null,2]";
        TreeNode root = genTreeFromLevelOrder(str);
        System.out.println(toStringTreeLevelOrder(root));
    }
}
