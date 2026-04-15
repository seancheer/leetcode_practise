package org.seancheer.linkedlist;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * <p>
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * <p>
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 * 使得偶数位的节点全部在奇数节点后面
 * <p>
 * Constraints:
 * <p>
 * n == number of nodes in the linked list
 * 0 <= n <= 104
 * -106 <= Node.val <= 106
 *
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _328OddEvenLinkedList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _328OddEvenLinkedList();
        String l1 = "[1,2,3,4,5]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        var res = obj.oddEvenList(head);
        System.out.println("res:" + toStrListNode(res)); //[1,3,5,2,4]


        l1 = "[2,1,3,5,6,4,7]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.oddEvenList(head);
        System.out.println("res:" + toStrListNode(res)); //[2,3,6,7,1,5,4]

        l1 = "[2,1]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.oddEvenList(head);
        System.out.println("res:" + toStrListNode(res)); //[2,1]

        l1 = "[2,1,3,4]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.oddEvenList(head);
        System.out.println("res:" + toStrListNode(res)); //[2,3,1,4]
    }

    /**
     * 解题思路：先把奇数位全部串在一起，再把偶数位全部串在一起，最后两者相连接即可，注意指针链接问题即可
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode tailOdd = odd;
        ListNode evenHead = head.next;
        ListNode even = head.next;
        while (null != odd && null != even) {
            odd.next = even.next;
            tailOdd = odd;
            odd = odd.next;

            if (null != odd) {
                tailOdd = odd;
                even.next = odd.next;
                even = even.next;
            }
        }

        tailOdd.next = evenHead;
        return head;
    }
}
