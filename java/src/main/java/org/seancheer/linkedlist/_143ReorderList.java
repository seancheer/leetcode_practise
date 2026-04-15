package org.seancheer.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _143ReorderList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _143ReorderList();
        String l1 = "[1,2,3,4]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        var head2 = convertArrToListNode(converStrTo1DemArr(l1));
        obj.reorderList(head);
        obj.reorderList2(head2);
        System.out.println("res:" + toStrListNode(head) + "    res2:" + toStrListNode(head2));//[1,4,2,3]

        l1 = "[1,2,3,4,5]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        head2 = convertArrToListNode(converStrTo1DemArr(l1));
        obj.reorderList(head);
        obj.reorderList2(head2);
        System.out.println("res:" + toStrListNode(head) + "    res2:" + toStrListNode(head2)); //[1,5,2,4,3]

        l1 = "[1,2,3,4,5,6]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        head2 = convertArrToListNode(converStrTo1DemArr(l1));
        obj.reorderList(head);
        obj.reorderList2(head2);
        System.out.println("res:" + toStrListNode(head) + "    res2:" + toStrListNode(head2)); //[1, 6, 2, 5, 3, 4]
    }


    /**
     * 解题思路：先把所有的node都压在栈里面，然后按照题目要求的方式一边弹栈一边连接起来即可
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (null == head) {
            return;
        }
        int n = 0;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (null != cur) {
            ++n;
            stack.push(cur);
            cur = cur.next;
        }

        int target = (n >> 1);
        cur = head;
        for (int i = 0; i < target; ++i) {
            ListNode next = cur.next;
            ListNode top = stack.pop();
            cur.next = top;
            top.next = next;
            cur = next;
        }
        cur.next = null;
    }

    /**
     * 解题思路：不需要额外的空间，先找到中间的位置，然后将中间位置后面的全部反转，
     * 然后再将后半部分插入到前半部分中间，就完成了目标，很好理解
     *
     * @param head
     */
    public void reorderList2(ListNode head) {
        if (null == head || head.next == null) {
            return;
        }

        //按照慢指针一次走一格，快指针一次走两格的方式找到中间的节点
        ListNode slow = head;
        ListNode fast = head;
        while (null != fast) {
            slow = slow.next;
            fast = fast.next;
            if (null == fast) {
                break;
            }
            fast = fast.next;
        }

        //翻转后半部分
        ListNode prev = slow;
        ListNode cur = slow.next;
        slow.next = null;
        while (null != cur) {
            ListNode oldNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = oldNext;
        }

        cur = head;
        ListNode halfCur = prev;
        //将后半部分插入到前半部分里面
        while (null != halfCur) {
            ListNode curNext = cur.next;
            cur.next = halfCur;
            ListNode halfCurNext = halfCur.next;
            halfCur.next = curNext;
            cur = curNext;
            halfCur = halfCurNext;
        }
        if (null != cur) {
            cur.next = null;
        }
    }
}
