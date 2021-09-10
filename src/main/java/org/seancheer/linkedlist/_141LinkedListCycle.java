package org.seancheer.linkedlist;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 * <p>
 * <p>
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 *
 * @author: seancheer
 * @date: 2021/8/16
 **/
public class _141LinkedListCycle extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _141LinkedListCycle();
        String l1 = "[3,2,0,-4]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        //构造一个环
        var tail = getTailOfList(head);
        tail.next = head.next;
        var res = obj.hasCycle(head);
        System.out.println("res:" + res);//true


        l1 = "[3,2,0,-4]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.hasCycle(head);
        System.out.println("res:" + res);//false
    }

    /**
     * 解题思路：使用两个指针，一个每次走一步，一个每次走两步，如果两个相遇了，就说明一定有环，否则没有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (null == head || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (null == fast) {
                return false;
            }
            fast = fast.next;
            //两者相遇了
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
