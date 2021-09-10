package org.seancheer.linkedlist;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * Example 4:
 * <p>
 * Input: head = []
 * Output: []
 * Explanation: The given linked list is empty (null pointer), so return null.
 * 完成深拷贝，不过链表中有一个random指向任意的节点，深拷贝完成后也需要将其指向新的链表的对应节点
 * 直接用 https://leetcode.com/problems/copy-list-with-random-pointer/ 进行线上测试
 * @author: seancheer
 * @date: 2021/8/4
 **/
public class _138CopyListWithRandomPointer {
    // Definition for a Node.
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 解题思路1：node的value有可能是一样的，所以我们用一个map，存储oldNode->newNode的映射，这样子在查找random指针的时候，可以直接
     * 找到其对应的newNode,赋值即可
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (null == head) {
            return head;
        }

        //oldNode -> newNode
        Map<Node, Node> nodeMap = new HashMap<>();
        Node newHead = new Node(head.val);
        Node cur = head.next;
        Node prev = newHead;
        nodeMap.put(head, newHead);

        while (null != cur) {
            Node newCur = new Node(cur.val);
            prev.next = newCur;
            prev = newCur;
            nodeMap.put(cur, newCur);
            cur = cur.next;
        }

        //赋值random指针
        cur = head;
        Node newCur = newHead;
        while (null != cur) {
            Node random = cur.random;
            newCur.random = random;
            if (null != random) {
                newCur.random = nodeMap.get(random);
            }
            cur = cur.next;
            newCur = newCur.next;
        }
        return newHead;
    }


    /**
     * 解题思路2：更加tricky的做法，我们把复制的node放在原有node的next指针上，这样子，在赋值random的时候，就可以直接用cur->randome->next
     * 找到新复制的节点了，最后再把两个分离开就可以了。比如：
     * 旧节点：1 -> 2 -> 3 -> 4
     * 复制后的：1 -> 1` -> 2 -> 2` -> 3 -> 3` -> 4 -> 4`
     * 那么很明显，random的位置能够直接找到，不需要在使用map了。
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (null == head) {
            return head;
        }

        Node cur = head;
        while (null != cur) {
            Node newCur = new Node(cur.val);
            newCur.next = cur.next;
            cur.next = newCur;
            //因为中间插入了复制的节点，所以这里直接使用next.next
            cur = cur.next.next;
        }

        //接下来给新节点赋值random指针
        cur = head;
        while (null != cur) {
            if (cur.random != null){
                //很明显,random.next就是复制出来的节点
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        //最后，我们把新旧链表分离开
        cur = head;
        Node newHead = cur.next;
        Node newCur = newHead;
        while(null != cur){
            cur.next = cur.next.next;
            cur = cur.next;
            if (newCur.next != null){
                newCur.next = newCur.next.next;
                newCur = newCur.next;
            }
        }
        return newHead;
    }
}
