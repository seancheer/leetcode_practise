package org.seancheer.linkedlist;

/**
 *Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,1,2]
 * Output: [1,2]
 * Example 2:
 *
 *
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _83RemoveDuplicateFromSortedList extends LinkedListParent{
    public static void main(String[] args) {
        var obj = new _83RemoveDuplicateFromSortedList();
        String l1 = "[1,1,2]";
        var res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2]


        l1 = "[1,1,2,3,3]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2,3]

        l1 = "[1,1]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,1]

        l1 = "[1]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2,3]
    }

    /**
     * ListNode有序，然后删除重复的数字，很简单，注意边界情况即可
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head){
            return null;
        }

        ListNode cur = head.next;
        ListNode prev = head;
        prev.next = null;
        while(null != cur){
            if (cur.val == prev.val){
                cur = cur.next;
                continue;
            }

            prev.next = cur;
            prev = cur;
            cur = cur.next;
            prev.next = null;
        }
        return head;
    }
}
