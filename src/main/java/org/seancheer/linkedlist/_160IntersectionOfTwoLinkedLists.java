package org.seancheer.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 * <p>
 * For example, the following two linked lists begin to intersect at node c1:
 * <p>
 * <p>
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * <p>
 * Note that the linked lists must retain their original structure after the function returns.
 * <p>
 * Custom Judge:
 * <p>
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * <p>
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * Example 2:
 * <p>
 * <p>
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 * Example 3:
 * <p>
 * <p>
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 * 保证不会出现环，要求O(n)和O(1)解决
 *
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _160IntersectionOfTwoLinkedLists extends LinkedListParent {

    public static void main(String[] args) {
        //其他用例直接去对应题目里面测试，因为这个测试场景比较复杂，不好构造
        var obj = new _160IntersectionOfTwoLinkedLists();
        String l1 = "[1,2,3,4]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        var head2 = convertArrToListNode(converStrTo1DemArr(l1));
        var res = obj.getIntersectionNode(head, head2);
        var res2 = obj.getIntersectionNode2(head, head2);
        System.out.println("res:" + toStrListNode(res) + "   res2:" + toStrListNode(res2));//null
    }

    /**
     * 解题思路：比较直观的思路，把链表分别压到两个栈，然后两个栈依次弹栈，直到栈顶出现不一样的node的时候，就是最终的答案，
     * 但是这种解法的空间复杂度不是O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }

        Deque<ListNode> stackA = new ArrayDeque<>();
        Deque<ListNode> stackB = new ArrayDeque<>();
        ListNode cur = headA;
        while (null != cur) {
            stackA.push(cur);
            cur = cur.next;
        }
        cur = headB;
        while (null != cur) {
            stackB.push(cur);
            cur = cur.next;
        }

        ListNode res = null;
        while (!stackA.isEmpty() && !stackB.isEmpty() && stackA.peek() == stackB.peek()) {
            res = stackA.pop();
            stackB.pop();
        }
        return res;
    }

    /**
     * 解题思路：O(1)的空间复杂度，思路如下：
     * 1 统计两个链表的长度，记为len1和len2
     * 2 然后让两个链表从同一长度起点开始往后走，比如长度5和7，那么让第二个链表也从index1（0-indexed）开始往后走，
     * 直到第一个node相同的位置
     *
     * 温馨提示：其实不需要直到长度也能解决这个问题（当然，遍历次数是不会减少的，还是需要重复遍历部分数据）
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }

        int len1 = 0, len2 = 0;
        ListNode curA = headA;
        while (null != curA) {
            len1++;
            curA = curA.next;
        }
        ListNode curB = headB;
        while (null != curB) {
            len2++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        //哪个长，就让哪个先走
        if (len1 > len2) {
            curA = advanceNode(curA, len1 - len2);
        } else if (len1 < len2) {
            curB = advanceNode(curB, len2 - len1);
        }

        //接下来两个一起开始往后面走，直到遇到第一个node相同的节点
        while (null != curA && null != curB && curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    /**
     * 把node往前推n步
     *
     * @param node
     * @param steps
     * @return
     */
    private ListNode advanceNode(ListNode node, int steps) {
        int i = 0;
        while (i < steps) {
            node = node.next;
            ++i;
        }
        return node;
    }
}
