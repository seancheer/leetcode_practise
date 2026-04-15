package org.seancheer.linkedlist;

/**
 *
 * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
 *
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 *
 *
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 * Example 3:
 *
 * Input: head = [1,2,3,4], node = 3
 * Output: [1,2,4]
 * Example 4:
 *
 * Input: head = [0,1], node = 0
 * Output: [1]
 * Example 5:
 *
 * Input: head = [-3,5,-99], node = -3
 * Output: [5,-99]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [2, 1000].
 * -1000 <= Node.val <= 1000
 * The value of each node in the list is unique.
 * The node to be deleted is in the list and is not a tail node
 * 因为入参只有一个，题目给的是要删除的那个节点，并没有给head，且删除的节点不可能是tail
 * @author: seancheer
 * @date: 2021/8/10
 **/
public class _237DeleteNodeInALinkedList extends LinkedListParent{

    public static void main(String[] args) {
        var obj = new _237DeleteNodeInALinkedList();
        String l1 = "[4,5,1,9]";
        var head = convertArrToListNode(converStrTo1DemArr(l1));
        obj.deleteNode(head.next);
        System.out.println("res:" + toStrListNode(head));//[4,5,9]
    }


    /**
     * 解题思路：因为没有给head，所以也就意味着将后面的节点的值往前“摞”，最后删除结尾的节点；
     * 所以题目也就规定了不可能删除末尾节点，也就导致了这种方法的可行性
     * @param node
     */
    public void deleteNode(ListNode node) {
        if (null == node || node.next == null){
            return;
        }

        ListNode cur = node;
        ListNode next = node.next;
        while(null != next.next){
            cur.val = next.val;
            cur = next;
            next = next.next;
        }
        cur.val = next.val;
        //删除末尾节点
        cur.next = null;
    }
}
