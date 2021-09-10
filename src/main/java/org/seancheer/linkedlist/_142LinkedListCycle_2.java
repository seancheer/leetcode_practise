package org.seancheer.linkedlist;


/**
 *Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Notice that you should not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * Example 2:
 *
 *
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * Example 3:
 *
 *
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 * 检测链表环出现的位置
 * @author: seancheer
 * @date: 2021/8/16
 **/
public class _142LinkedListCycle_2 extends LinkedListParent {
    public static void main(String[] args) {
        var obj = new _142LinkedListCycle_2();
        String l1 = "[3,2,0,-4]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        //构造一个环
        var tail = getTailOfList(head);
        tail.next = head.next;
        var res = obj.detectCycle(head);
        System.out.println("res:" + res);//2


        l1 = "[3,2,0,-4]";
        head = convertArrToListNode(converStrTo1DemArr(l1));
        res = obj.detectCycle(head);
        System.out.println("res:" + res);//false
    }

    /**
     * 解题思路：要求O(1)的空间复杂度，且不能修改原链表，如何实现呢？
     * 同样的，使用两个指针slow和fast，一个一次走一步，一个一次走两步，相遇的时候，fast走过的长度
     * 减去slow走过的长度，一定是环的长度，得到了环的长度，再用{@link _19RemoveNthNodeFromEndOfList}的方法
     * 稍加修改下就可以得到开始的节点，修改方式如下：
     * slow = head, fast =head, n为环的长度
     * 1 fast先走n步
     * 2 然后slow和fast一起前进，每进行一步，判断一下fast.next == slow，如果相等，则slow就是环的开头（这是因为这个时候slow在环
     * 的开头，而fast在环的末尾）
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(null == head || head.next == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        int slowLen = 1, fastLen = 1;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            ++slowLen;
            fast = fast.next.next;
            fastLen += 2;
            if (slow == fast){
                break;
            }
        }
        //说明没有环存在
        if (null == fast.next || fast.next.next == null){
            return null;
        }

        //我们得到了环的长度，到这里就说明一定存在环
        int len = fastLen - slowLen;
        slow = head;
        fast = head;
        int i = 1;
        //fast先走环的长度
        while(i < len){
            fast = fast.next;
            ++i;
        }
        //总有一刻，slow在环的开头，fast在环的末尾，所以这里才这么判断
        while(slow != fast.next){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
