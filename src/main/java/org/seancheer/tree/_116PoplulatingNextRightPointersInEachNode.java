package org.seancheer.tree;


import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * Follow-up:
 * <p>
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 *
 * @author: seancheer
 * @date: 2021/8/21
 **/
public class _116PoplulatingNextRightPointersInEachNode extends TreeParent {
    /**
     * 测试方式直接去对应问题下面进行测试
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
     *
     * @param args
     */
    public static void main(String[] args) {
        var obj = new _116PoplulatingNextRightPointersInEachNode();
        String rootStr = "[1,2,3,4,5,6,7]";
        Node treeRoot = Node.genTreeFromLevelOrder(rootStr);
        Node treeRoot2 = Node.genTreeFromLevelOrder(rootStr);
        Node treeRoot3 = Node.genTreeFromLevelOrder(rootStr);
        obj.connect(treeRoot);
        obj.connect2(treeRoot2);
        obj.connect3(treeRoot3);
        System.out.println("res:" + printTreeByNext(treeRoot) + "  res2:" + printTreeByNext(treeRoot2)
         + "   res3:" + printTreeByNext(treeRoot3));

        rootStr = "[]";
        treeRoot = Node.genTreeFromLevelOrder(rootStr);
        treeRoot2 = Node.genTreeFromLevelOrder(rootStr);
        obj.connect(treeRoot);
        obj.connect2(treeRoot2);
        obj.connect3(treeRoot3);
        System.out.println("res:" + printTreeByNext(treeRoot) + "  res2:" + printTreeByNext(treeRoot2)
                + "   res3:" + printTreeByNext(treeRoot3));

        rootStr = "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]";
        treeRoot = Node.genTreeFromLevelOrder(rootStr);
        treeRoot2 = Node.genTreeFromLevelOrder(rootStr);
        obj.connect(treeRoot);
        obj.connect2(treeRoot2);
        obj.connect3(treeRoot3);
        System.out.println("res:" + printTreeByNext(treeRoot) + "  res2:" + printTreeByNext(treeRoot2)
                + "   res3:" + printTreeByNext(treeRoot3));

        int[] testcase = IntStream.range(1, (int) (Math.pow(2, 8))).toArray();
        rootStr = Arrays.toString(testcase);
        treeRoot = Node.genTreeFromLevelOrder(rootStr);
        treeRoot2 = Node.genTreeFromLevelOrder(rootStr);
        obj.connect(treeRoot);
        obj.connect2(treeRoot2);
        obj.connect3(treeRoot3);
        System.out.println("res:" + printTreeByNext(treeRoot) + "  res2:" + printTreeByNext(treeRoot2)
                + "   res3:" + printTreeByNext(treeRoot3));
    }


    /**
     * 解题思路：简洁的迭代遍历办法，不需要递归，思路如下：
     * 1 使用curLevel指向当前这一层，然后将当前这一层的child全部连接起来，完成后，curLevel = curLevel.left，即指向下一层
     * 2 第二次循环的时候，因为新的一层已经全部next连接好了，所以直接推动curLevel = curLevel.next依次连接其儿子
     * 如此往复，就能达到最终的效果
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (null == root) {
            return root;
        }

        Node curLevel = root;
        while(null != curLevel){
            Node cur = curLevel;
            Node finalNode = null;
            //处理其孩子，这个while处理完成后，就可以保证孩子那一层已经全部连接好了next
            //cur.left要判断是因为cur可能会到叶子节点
            while(null != cur && null != cur.left){
                if (null != finalNode) {
                    finalNode.next = cur.left;
                }
                cur.left.next = cur.right;
                finalNode = cur.right;
                cur = cur.next;
            }
            //然后我们将pre向下摞一层，
            curLevel = curLevel.left;
        }
        return root;
    }



    /**
     * 解题思路：很明显的是一个层次遍历，但是题目有特殊要求，空间复杂度为O(1)，层次遍历必须借助队列，因此该方法不能用
     * 可以使用递归解法，且题目要求常数的空间复杂度，那么问题的难点就是如何把兄弟节点的孩子连接起来
     * 我们采用递归的方式如下：
     * 1 每到一个节点，先把其左孩子和右孩子连接起来
     * 2 开始遍历其左孩子和右孩子，我们让左孩子返回其右孩子（也就是右孙子），让右孩子返回其左孩子（也就是左孙子），然后循环式的把
     * 右孙子和左孙子连接起来（左孙子一直往右边走，右孙子往左边走）
     * 该递归方法不是很精简，请看解法3的递归方法
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (null == root) {
            return root;
        }

        connectInternal(root, true);
        return root;
    }

    private Node connectInternal(Node root, boolean isLeft) {
        if (null == root) {
            return root;
        }

        if (null != root.left && null != root.right) {
            root.left.next = root.right;
        }
        Node left = connectInternal(root.left, false);
        Node right = connectInternal(root.right, true);

        //孙子返回来了，接下来以孙子为起点，把剩余的都连接起来
        while (null != left && null != right) {
            left.next = right;
            left = left.right;
            right = right.left;
        }

        if (isLeft) {
            return root.left;
        } else {
            return root.right;
        }
    }

    /**
     * 解题思路：该方法采用前序遍历的方式，每次递归前，都保证root的孩子那一层连接上，这样子就成功避免了解法2的非兄弟节点
     * 孩子连接的问题，如下图所示：
     *           1
     *        2     3
     *     4   5   6  7
     *  8  9 10 11 12  13
     * 下面这个解法能够很好的解决上面的问题，在遍历左子树的时候，会保证2和3， 5和6连接上，这样子在处理5的孩子时，我们可以通过
     * root.next.left找到11，从而把5的孩子10和6的孩子11连接起来；然后当递归到右子树的时候，重复该逻辑即可
     * @param root
     */
    public void connect3(Node root) {
        if(root == null)
            return;

        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
    }



    /**
     * 本题专用：沿着next指针打印tree，因为是满二叉树，所以直接从左孩子开始即可
     *
     * @param root
     * @return
     */
    protected static String printTreeByNext(Node root) {
        if (null == root) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (null != root) {
            Node tmp = root;
            while (null != tmp) {
                sb.append(tmp.val).append(", ");
                tmp = tmp.next;
            }
            root = root.left;
        }

        String res = sb.substring(0, sb.length() - 2);
        return res + "]";
    }
}
