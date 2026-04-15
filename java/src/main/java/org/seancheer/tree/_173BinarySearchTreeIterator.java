package org.seancheer.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * <p>
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * <p>
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 106
 * At most 105 calls will be made to hasNext, and next.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 *
 * @author: seancheer
 * @date: 2021/8/25
 **/
public class _173BinarySearchTreeIterator extends TreeParent {
    public static void main(String[] args) {
        String rootStr = "[7, 3, 15, null, null, 9, 20]";
        TreeNode root = genTreeFromLevelOrder(rootStr);
        BSTIterator bSTIterator = new BSTIteratorTest(root);
        bSTIterator.next();    // return 3
        bSTIterator.next();    // return 7
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 9
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 15
        bSTIterator.hasNext(); // return True
        bSTIterator.next();    // return 20
        bSTIterator.hasNext(); // return False
    }

    /**
     * 解题思路：实现一个按照中序遍历顺序进行的二叉树iterator
     * 要求O(1)的时间复杂度和O(h)的空间复杂度，其中h是树的高度
     * 其实本质上就是用迭代的方式来实现中序遍历
     * 这里的O(1)是指这棵树会被用例全部遍历完，所以整体的时间复杂度为O(n)（n为二叉树的节点总数），那么平均下来，每一个节点的时间复杂度
     * 即为O(1)
     */
    static class BSTIterator {
        Deque<TreeNode> stack = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            while (null != root) {
                stack.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode cur = stack.pop();
            TreeNode right = cur.right;
            //将rightChild的最左边依次加入到栈中
            while (null != right) {
                stack.push(right);
                right = right.left;
            }
            return cur.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }


    /**
     * 测试类，负责测试打印相关的工作
     */
    static class BSTIteratorTest extends BSTIterator {

        public BSTIteratorTest(TreeNode root) {
            super(root);
            System.out.println("-----------------------------------------------------");
        }

        public int next() {
            int val = super.next();
            System.out.print(val + "\t");
            return val;
        }

        public boolean hasNext() {
            boolean res = super.hasNext();
            System.out.print(res + "\t");
            return res;
        }
    }
}
