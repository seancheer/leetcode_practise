package org.seancheer.linkedlist;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _206ReverseLinkedList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _206ReverseLinkedList();
        String l1 = "[1,2,3,4,5]";
        var res = obj.reverseList(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[5,4,3,2,1]

        l1 = " [1,2]";
        res = obj.reverseList(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[2,1]
    }

    /**
     * 翻转链表，其实直接迭代就能实现，但是注意把head的next置为null，否则链表就会是一个环
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }

        ListNode prev = head;
        ListNode cur = head.next;
        head.next = null;
        while (null != cur) {
            ListNode oldNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = oldNext;
        }
        return prev;
    }
}
