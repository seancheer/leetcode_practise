package org.seancheer.linkedlist;

import org.seancheer.utils.LeetCodeParent;

public class LinkedListParent extends LeetCodeParent {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }


    /**
     * 把数组转换为ListNode
     *
     * @param arr
     * @return
     */
    public static ListNode convertArrToListNode(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; ++i) {
            ListNode newNode = new ListNode(arr[i]);
            cur.next = newNode;
            cur = newNode;
        }
        return head;
    }


    /**
     * 打印ListNode
     *
     * @param node
     */
    public static void printListNode(ListNode node) {
        System.out.println(toStrListNode(node));
    }

    /**
     * 将ListNode转换为字符串
     *
     * @param node
     */
    public static String toStrListNode(ListNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (null != node) {
            sb.append(node.val);
            node = node.next;
            if (null != node) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取一个链表的末尾节点
     * @param head
     * @return
     */
    protected static ListNode getTailOfList(ListNode head){
        if (null == head || head.next == null){
            return head;
        }

        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        return cur;
    }
}
