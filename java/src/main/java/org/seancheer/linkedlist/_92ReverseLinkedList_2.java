package org.seancheer.linkedlist;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _92ReverseLinkedList_2 extends LinkedListParent {

    public static void main(String[] args) {
        var obj = new _92ReverseLinkedList_2();
        String l1 = "[1,2,3,4,5]";
        int left = 2;
        int right = 4;
        var res = obj.reverseBetween(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        var res2 = obj.reverseBetween2(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        System.out.println("res:" + toStrListNode(res) + "    res2:" + toStrListNode(res2)); //[1,4,3,2,5]

        l1 = " [5]";
        left = 1;
        right = 1;
        res = obj.reverseBetween(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        res2 = obj.reverseBetween2(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        System.out.println("res:" + toStrListNode(res) + "    res2:" + toStrListNode(res2));//[5]

        l1 = " [1,2,3,4,5]";
        left = 1;
        right = 5;
        res = obj.reverseBetween(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        res2 = obj.reverseBetween2(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        System.out.println("res:" + toStrListNode(res) + "    res2:" + toStrListNode(res2)); //[5, 4, 3, 2, 1]

        l1 = " [1,2,3,4,5]";
        left = 3;
        right = 5;
        res = obj.reverseBetween(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        res2 = obj.reverseBetween2(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        System.out.println("res:" + toStrListNode(res) + "    res2:" + toStrListNode(res2)); //[1, 2, 5, 4, 3]

        l1 = " [1,2,3,4,5]";
        left = 1;
        right = 3;
        res = obj.reverseBetween(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        res2 = obj.reverseBetween2(convertArrToListNode(converStrTo1DemArr(l1)), left, right);
        System.out.println("res:" + toStrListNode(res) + "    res2:" + toStrListNode(res2)); //[3, 2, 1, 4, 5]
    }

    /**
     * 解题思路1：只是在以前的基础上翻转一个区段而已，注意边界情况即可，left和right一定是合法的
     * 弄个dummyNode方便统一left和right覆盖的范围不同而出现的可能的代码逻辑的不同
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head || left >= right) {
            return head;
        }

        int i = 1;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head;
        ListNode prev = dummyNode;
        ListNode storePrev = dummyNode;
        while (null != cur) {
            //到达目的地了，先存下来prev，后面到目的地了需要用到
            if (i == left) {
                storePrev = prev;
                prev = cur;
                cur = cur.next;
            } else if (i > left) {
                ListNode oldNext = cur.next;
                cur.next = prev;
                prev = cur;
                cur = oldNext;
                //到达目的地了，将翻转后的头尾和旧的部分连接起来
                if (i == right) {
                    ListNode leftNode = storePrev.next;
                    ListNode rightNode = prev;
                    leftNode.next = cur;
                    storePrev.next = rightNode;
                    break;
                }
            }
            //其他情况顺序往下走即可
            else {
                prev = cur;
                cur = cur.next;
            }

            ++i;
        }
        return dummyNode.next;
    }


    /**
     * 解题思路2：不需要1那样的复杂逻辑，我们保证prev不变，在目的地区段上每翻转一次，就将prev的指针指向翻转后的头，
     * 这样子就不需要翻转完目标区段后又重新和旧的链表连接起来了
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (null == head || left >= right) {
            return head;
        }

        int i = 0;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        ListNode cur = dummyNode;
        for (; i < left; ++i) {
            prev = cur;
            cur = cur.next;
        }

        //达到目的地了，接下来开始翻转
        ++i;
        ListNode oldPrev = prev;
        ListNode start = cur;
        prev = cur;
        cur = cur.next;
        //注意指针的指向调整，避免出现错误
        for (; i <= right; ++i) {
            ListNode oldNext = cur.next;
            cur.next = prev;
            oldPrev.next = cur;
            prev = cur;
            cur = oldNext;
            start.next = cur;
        }
        return dummyNode.next;
    }
}
