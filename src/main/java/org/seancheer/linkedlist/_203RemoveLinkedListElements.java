package org.seancheer.linkedlist;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 * Example 2:
 * <p>
 * Input: head = [], val = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 104].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _203RemoveLinkedListElements extends LinkedListParent {

    public static void main(String[] args) {
        var obj = new _203RemoveLinkedListElements();
        String l1 = "[1,2,6,3,4,5,6]";
        int val = 6;
        var res = obj.removeElements(convertArrToListNode(converStrTo1DemArr(l1)), val);
        printListNode(res); //[1,2,3,4,5]

        l1 = "[7,7,7,7]";
        val = 7;
        res = obj.removeElements(convertArrToListNode(converStrTo1DemArr(l1)), val);
        printListNode(res); //[]
    }

    /**
     * 解题思路：思路很直接，注意头指针和尾指针问题即可
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (null == head) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode cur = head;
        while (null != cur) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return dummyNode.next;
    }
}
