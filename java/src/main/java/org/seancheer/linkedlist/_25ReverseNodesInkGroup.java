package org.seancheer.linkedlist;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 * <p>
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 * <p>
 * Input: head = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 * <p>
 * <p>
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * 分成以k个链表为一组的方式，每组进行翻转，如果不够的话，就不要翻转剩下的那些链表了
 *
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _25ReverseNodesInkGroup extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _25ReverseNodesInkGroup();
        String l1 = "[1,2,3,4,5]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        int k = 2;
        var res = obj.reverseKGroup(head, k);
        System.out.println("res:" + toStrListNode(res));// [2,1,4,3,5]

        l1 = "[1,2,3,4,5]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        k = 3;
        res = obj.reverseKGroup(head, k);
        System.out.println("res:" + toStrListNode(res));//[3,2,1,4,5]

        l1 = "[1,2,3,4,5]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        k = 1;
        res = obj.reverseKGroup(head, k);
        System.out.println("res:" + toStrListNode(res));//[1,2,3,4,5]

        l1 = "[1]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        k = 1;
        res = obj.reverseKGroup(head, k);
        System.out.println("res:" + toStrListNode(res));//[1]
    }

    /**
     * 解题思路：思路很清晰，注意指针指向即可
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next || k <= 1) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        head = dummyNode;
        while (null != head) {
            int i = 0;
            //尝试找出下一组
            ListNode cur = head.next;
            ListNode end = cur;
            while (i < k && null != cur) {
                end = cur;
                cur = cur.next;
                ++i;
            }

            if (i < k) {
                break;
            }

            ListNode endNext = cur;
            cur = head.next;
            ListNode next = cur.next;

            //开始翻转这一段
            while (next != endNext) {
                ListNode oldNext = next.next;
                next.next = cur;
                cur = next;
                next = oldNext;
            }

            ListNode oldNext = head.next;
            //翻转完成后将其链上即可
            head.next.next = endNext;
            head.next = end;
            //前一段的最后一个节点
            head = oldNext;
        }
        return dummyNode.next;
    }
}
