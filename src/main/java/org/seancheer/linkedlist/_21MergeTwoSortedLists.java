package org.seancheer.linkedlist;

/**
 * Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [1,2,4], l2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * <p>
 * Input: l1 = [], l2 = []
 * Output: []
 * Example 3:
 * <p>
 * Input: l1 = [], l2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both l1 and l2 are sorted in non-decreasing order.
 *
 * @author: seancheer
 * @date: 2021/8/11
 **/
public class _21MergeTwoSortedLists extends LinkedListParent {

    public static void main(String[] args) {
        var obj = new _21MergeTwoSortedLists();
        String l1 = "[1,2,4]";
        String l2 = "[1,3,4]";
        var res = obj.mergeTwoLists(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res); //[1,1,2,3,4,4]

        l1 = "[]";
        l2 = "[]";
        res = obj.mergeTwoLists(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res); //[]

        l1 = "[]";
        l2 = "[1]";
        res = obj.mergeTwoLists(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res); //[1]
    }

    /**
     * 解题思路：题目比较简单，注意指针问题即可
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1 && l2 == null) {
            return null;
        } else if (null == l1) {
            return l2;
        } else if (null == l2) {
            return l1;
        }

        //接下来开始进行链接了
        ListNode dummyNode = new ListNode(-1);
        ListNode resNode = dummyNode;
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val) {
                resNode.next = l1;
                resNode = l1;
                l1 = l1.next;
            } else {
                resNode.next = l2;
                resNode = l2;
                l2 = l2.next;
            }
        }

        //剩下的部分再链接起来
        if (null != l1) {
            resNode.next = l1;
        } else {
            resNode.next = l2;
        }
        return dummyNode.next;
    }
}
