package org.seancheer.tree;

import org.seancheer.linkedlist.LinkedListParent;
import org.seancheer.linkedlist.LinkedListParent.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [0]
 * Output: [0]
 * Example 4:
 * <p>
 * Input: head = [1,3]
 * Output: [3,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in head is in the range [0, 2 * 104].
 * -105 <= Node.val <= 105
 *
 * @author: seancheer
 * @date: 2021/8/24
 **/
public class _109ConvertSortedListToBST extends TreeParent {

    public static void main(String[] args) {
        var obj = new _109ConvertSortedListToBST();
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        ListNode node = LinkedListParent.convertArrToListNode(nums);
        var res = obj.sortedListToBST(node);
        var res2 = obj.sortedListToBST2(node);
        System.out.println("res:" + toStringTreeLevelOrder(res) + "   res2:" + toStringTreeLevelOrder(res2)); //[0,-3,9,-10,null,5]  [0,-10,5,null,-3,null,9]

        nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        node = LinkedListParent.convertArrToListNode(nums);
        res = obj.sortedListToBST(node);
        res2 = obj.sortedListToBST2(node);
        System.out.println("res:" + toStringTreeLevelOrder(res) + "   res2:" + toStringTreeLevelOrder(res2)); //[1,3]


        nums = new int[]{1, 3};
        node = LinkedListParent.convertArrToListNode(nums);
        res = obj.sortedListToBST(node);
        res2 = obj.sortedListToBST2(node);
        System.out.println("res:" + toStringTreeLevelOrder(res) + "   res2:" + toStringTreeLevelOrder(res2)); //[1,3]

        nums = new int[]{1};
        node = LinkedListParent.convertArrToListNode(nums);
        res = obj.sortedListToBST(node);
        res2 = obj.sortedListToBST2(node);
        System.out.println("res:" + toStringTreeLevelOrder(res) + "   res2:" + toStringTreeLevelOrder(res2)); //[1]

        nums = new int[]{};
        node = LinkedListParent.convertArrToListNode(nums);
        res = obj.sortedListToBST(node);
        res2 = obj.sortedListToBST2(node);
        System.out.println("res:" + toStringTreeLevelOrder(res) + "   res2:" + toStringTreeLevelOrder(res2)); //[1]
    }

    /**
     * 解题思路：简单的做法，把所有的ListNode都放在数组里面，这样子题目就转换成了{@link _108ConvertSortedArrToBST}，可是没有
     * 效率更高的办法么？且看解法2
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode cur = head;
        List<ListNode> nodes = new ArrayList<>();
        while (null != cur) {
            nodes.add(cur);
            cur = cur.next;
        }

        return sortedListToBSTInternal(nodes, 0, nodes.size() - 1);
    }

    private TreeNode sortedListToBSTInternal(List<ListNode> nodes, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nodes.get(mid).val);
        root.left = sortedListToBSTInternal(nodes, left, mid - 1);
        root.right = sortedListToBSTInternal(nodes, mid + 1, right);
        return root;
    }


    /**
     * 解题思路：其实思路本质上是一样的，就是利用中序遍历来把这棵树给构造出来，但是如何在O(n)的时间复杂度内构造出来呢？
     * 1 我们维护一个ListNode指针，该指针从头开始遍历
     * 2 数组是排好序的，那么我们按照中序遍历的方式构造Tree（每构造一个节点，ListNode的指针就往前摞动一个，这样子我们就可以边摞动ListNode，边构造Tree，
     * 最后，当我们访问到ListNode的末尾时，树也就构造出来了
     * <p>
     * 为什么构造出来的就一定是平衡二叉树呢？因为左右孩子的构造方式都是一样的，即尽量把树的左右孩子都构造满，比如1，2，3，那么先构造1，
     * 然后1作为左孩子，构造2，在把3作为右孩子来构造，按照这种方式构造出来的二叉树，必然是平衡的
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (null == head) {
            return null;
        }
        int len = 0;
        //首先计算长度
        curListNode = head;
        while (null != curListNode) {
            ++len;
            curListNode = curListNode.next;
        }

        //将成员变量重置为head，因为接下来要开始边遍历ListNode边构造Tree了
        curListNode = head;
        return sortedListToBST2Internal(0, len - 1);
    }

    //该指针负责遍历链表
    private ListNode curListNode;

    public TreeNode sortedListToBST2Internal(int start, int end) {
        if (start > end) {
            return null;
        }
        //首先计算长度
        int mid = (start + end) >> 1;
        TreeNode left = sortedListToBST2Internal(start, mid - 1);
        //不一样的地方就在这里，我们每次用链表的指向来构造Tree，而不是像以前那样直接从Array里面取值构造
        TreeNode root = new TreeNode(curListNode.val);
        root.left = left;

        //移动链表指针，处理中序遍历的下一个数字
        curListNode = curListNode.next;
        TreeNode right = sortedListToBST2Internal(mid + 1, end);
        root.right = right;
        return root;
    }
}
