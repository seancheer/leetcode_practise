package org.seancheer.linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
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
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _148SortList extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _148SortList();
        String l1 = "[4,2,1,3]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        var head2 = convertArrToListNode(converStrTo1DemArr(l1));
        var res = obj.sortList(head);
        var res2 = obj.sortList2(head2);
        System.out.println("res:" + toStrListNode(res) + "   res2:" + toStrListNode(res2));

        l1 = "[-1,5,3,-100]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        head2 = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.sortList(head);
        res2 = obj.sortList2(head2);
        System.out.println("res:" + toStrListNode(res) + "   res2:" + toStrListNode(res2));
    }

    /**
     * 解题思路：简单的方式，将链表的所有节点放在ArrayList中，然后排序ArrayList，最后再将ArrayList重新串联起来
     * 但是这样的方式空间复杂度不是O(1)
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (null != cur) {
            nodes.add(cur);
            cur = cur.next;
        }

        nodes.sort(Comparator.comparingInt(item -> item.val));
        //最后再把所有的节点串联起来
        ListNode prev = nodes.get(0);
        for (int i = 1; i < nodes.size(); ++i) {
            cur = nodes.get(i);
            prev.next = cur;
            prev = cur;
            cur.next = null;
        }
        return nodes.get(0);
    }


    /**
     * 解题思路：因为是单向链表，实际上采用归并排序（mergeSort）的方式是最合适的，而且为了达到目标的O(1)的空间占用率，
     * 采用自底向上的实现方式，具体可以参考：{@link org.seancheer.sort_algorithm.SortPractise} mergeSortBottom2Up函数
     * 注意，快速排序是不适合的，因为其需要支持倒着遍历的方式，很显然，单向链表并不支持该功能
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }

        int len = 0;
        ListNode cur = head;
        while (null != cur) {
            ++len;
            cur = cur.next;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        //外层控制分割的长度
        for (int gap = 1; gap < len; gap <<= 1) {
            //接下来根据gap来分割列表，排序的话注意head可能被改变，为了始终指向第一个，所以直接用dummyNode.next即可
            cur = dummyNode.next;
            //注意更新lastTail，每次gap开始都要重新置为开头
            ListNode lastTail = dummyNode;
            while (null != cur) {
                ListNode start1 = cur;
                int i = 0;
                //第一部分
                while (null != cur && i < gap) {
                    ++i;
                    cur = cur.next;
                }
                ListNode end1 = cur;
                ListNode start2 = cur;

                //第二部分
                i = 0;
                while (null != cur && i < gap) {
                    ++i;
                    cur = cur.next;
                }
                ListNode end2 = cur;

                //接下来对两部分进行一个merge操作，注意，merge操作中必须返回最后一个节点，方便下次循环链接上链表
                lastTail = mergeList(start1, end1, start2, end2, lastTail);
            }
        }

        return dummyNode.next;
    }

    /**
     * 进行mergeSort操作，注意是左闭右开
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @param lastTail
     * @return
     */
    private ListNode mergeList(ListNode start1, ListNode end1, ListNode start2, ListNode end2, ListNode lastTail) {
        //start1必然不是null
        ListNode newHead = start1;
        if (null != start2 && start2.val < start1.val) {
            newHead = start2;
            start2 = start2.next;
        } else {
            start1 = start1.next;
        }
        //用上一次的末尾链接上本次新的头部
        lastTail.next = newHead;
        ListNode cur = newHead;
        //确定好head后，接下来开始正式mergeSort操作
        while (null != start1 && null != start2 && start1 != end1 && start2 != end2) {
            if (start1.val <= start2.val) {
                cur.next = start1;
                cur = start1;
                start1 = start1.next;
                cur.next = null;
            } else {
                cur.next = start2;
                cur = start2;
                start2 = start2.next;
                //避免末尾没有置为null
                cur.next = null;
            }
        }

        while (null != start1 && start1 != end1) {
            cur.next = start1;
            cur = start1;
            start1 = start1.next;
            cur.next = null;
        }

        while (null != start2 && start2 != end2) {
            cur.next = start2;
            cur = start2;
            start2 = start2.next;
            cur.next = null;
        }

        //cur也到了末尾
        return cur;
    }
}
