package org.seancheer.linkedlist;

/**
 * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
 * <p>
 * The steps of the insertion sort algorithm:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * 实现ListNode的插入排序
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _147InsertionSortList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _147InsertionSortList();
        String l1 = " [4,2,1,3]";
        var res = obj.insertionSortList(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2,3,4]

        l1 = " [-1,5,3,4,0]";
        res = obj.insertionSortList(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[-1,0,3,4,5]
    }

    /**
     * 解题思路：其实完全可以交换val而不改变节点的，还是不要作弊的好
     * 还是一如既往的问题，注意指针指向问题，因为要调整指针，插入排序的时候，记得把排好序那段的末尾的next指针置为null
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode cur = head.next;
        //注意把head.next置为null，因为要往前走了
        head.next = null;
        while (null != cur) {
            ListNode prev = dummyNode;
            ListNode start = dummyNode.next;
            while (null != start && start.val < cur.val) {
                prev = start;
                start = start.next;
            }

            ListNode oldNext = cur.next;
            ListNode tmpNode = prev.next;
            prev.next = cur;
            cur.next = tmpNode;
            cur = oldNext;
        }
        return dummyNode.next;
    }
}
