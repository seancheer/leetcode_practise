package org.seancheer.linkedlist;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _82RemoveDuplicateFromSortedList_2 extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _82RemoveDuplicateFromSortedList_2();
        String l1 = "[1,2,3,3,4,4,5]";
        var res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2,5]


        l1 = "[1,1,1,2,3]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[2,3]

        l1 = "[1,2,3,4,5]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[1,2,3,4,5]

        l1 = "[1,1]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[]

        l1 = "[1,1,2,2]";
        res = obj.deleteDuplicates(convertArrToListNode(converStrTo1DemArr(l1)));
        printListNode(res); //[]
    }


    /**
     * 解题思路：稍微复杂点，就是要把相同的全部删除掉，注意边界情况
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }

        //整一个假的head，这样子就能避免很多重复代码了
        ListNode dummyHead = new ListNode(-1);
        ListNode prevprev = dummyHead;
        prevprev.next = null;
        ListNode prev = head;
        ListNode cur = prev.next;
        int count = 1;
        while (null != cur) {
            if (prev.val == cur.val) {
                ++count;
                prev = cur;
                cur = cur.next;
                continue;
            }

            //表示上一把只出现了一次，所以需要留下来
            if (count == 1) {
                prevprev.next = prev;
                prevprev = prev;
                prevprev.next = null;
            }
            //出现了不同的数字，重置count
            count = 1;
            prev = cur;
            cur = cur.next;
        }

        //末尾最后一个
        if (count == 1) {
            prevprev.next = prev;
        }

        return dummyHead.next;
    }
}
