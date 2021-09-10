package org.seancheer.linkedlist;

import org.seancheer.linkedlist.LinkedListParent;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 题目要求一次遍历就搞定这个问题
 *
 * @author: seancheer
 * @date: 2021/8/13
 **/
public class _19RemoveNthNodeFromEndOfList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _19RemoveNthNodeFromEndOfList();
        String l1 = "[1,2,3,4,5]";
        ListNode head = convertArrToListNode(converStrTo1DemArr(l1));
        int n = 2;
        var res = obj.removeNthFromEnd(head, n);
        printListNode(res); //[1,2,3,5]

        l1 = "[1]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        n = 1;
        res = obj.removeNthFromEnd(head, n);
        printListNode(res); //[]

        l1 = "[1,2]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        n = 1;
        res = obj.removeNthFromEnd(head, n);
        printListNode(res); //[1]

    }

    /**
     * 解题思路：删除倒数第n个节点，需要一次性的遍历完成目标。
     * 1 使用两个指针first和second，第一个first先走n步，然后两个一起走，当first为null的时候，second就在要删除的那个节点位置，
     *   因为删除要连接前一个节点，所以我们再代码实现的时候让second指向要删除节点的前一个节点比较好
     * 2 删除即可
     * 增加一个dummyNode，可以统一代码逻辑，避免一些头部被删除的特殊处理
     * n一定是合法的，并且从1开始
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(null == head || n <= 0){
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode first = dummyNode;
        int i = 0;
        while(first.next != null && i < n){
            first = first.next;
            ++i;
        }

        ListNode second = dummyNode;
        while(first.next != null){
            first = first.next;
            second = second.next;
        }

        ListNode oldNext = second.next.next;
        second.next = oldNext;
        return dummyNode.next;
    }
}
