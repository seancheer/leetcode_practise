package org.seancheer.linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 *
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _24SwapNodesInPairs extends LinkedListParent {

    public static void main(String[] args) {
        var obj = new _24SwapNodesInPairs();
        String l1 = "[1,2,3,4]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        var res = obj.swapPairs(head);
        System.out.println("res:" + toStrListNode(res));//[2,1,4,3]

        l1 = "[1]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.swapPairs(head);
        System.out.println("res:" + toStrListNode(res));//[1]

        l1 = "[1,2]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.swapPairs(head);
        System.out.println("res:" + toStrListNode(res));//[2,1]

        l1 = "[1,2,3]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.swapPairs(head);
        System.out.println("res:" + toStrListNode(res));//[2,1]
    }

    /**
     * 解题思路：相邻的节点直接进行交换即可，注意指针的指向问题
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (null == head || head.next == null){
            return head;
        }
        ListNode resHead = head.next;
        ListNode cur = head;
        while(null != cur && null != cur.next){
            ListNode nextnext = cur.next.next;
            ListNode next = cur.next;
            next.next = cur;
            cur.next = nextnext;
            if (null != nextnext && nextnext.next != null){
                cur.next = nextnext.next;
            }
            cur = nextnext;
        }
        return resHead;
    }
}
