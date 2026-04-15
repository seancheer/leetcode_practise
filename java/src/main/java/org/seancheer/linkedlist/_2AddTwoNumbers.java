package org.seancheer.linkedlist;

import org.seancheer.utils.LeetCodeParent;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _2AddTwoNumbers extends LinkedListParent {

    public static void main(String[] args) {
        var obj = new _2AddTwoNumbers();
        String l1 = "[2,4,3]";
        String l2 = "[5,6,4]";
        var res = obj.addTwoNumbers(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res); //[7,0,8]


        l1 = "[0]";
        l2 = "[0]";
        res = obj.addTwoNumbers(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res); //[0]

        l1 = "[9,9,9,9,9,9,9]";
        l2 = "[9,9,9,9]"; //[8,9,9,9,0,0,0,1]
        res = obj.addTwoNumbers(convertArrToListNode(converStrTo1DemArr(l1)), convertArrToListNode(converStrTo1DemArr(l2)));
        printListNode(res);
    }

    /**
     * 解题思路：就是实现一下加法的原理，注意进位和l1，l2长短以及末尾还有进1的情况即可
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1 && null == l2) {
            return null;
        }

        ListNode dummyHead = new ListNode(-1);
        ListNode prev = dummyHead;
        int carry = 0;
        while (null != l1 || null != l2) {
            int sum = carry;
            if (null != l1) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (null != l2) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            ListNode cur = new ListNode(sum);
            prev.next = cur;
            prev = cur;
        }

        //处理还有carry的情况
        if (carry > 0){
            ListNode cur = new ListNode(carry);
            prev.next = cur;
        }
        return dummyHead.next;
    }



}
