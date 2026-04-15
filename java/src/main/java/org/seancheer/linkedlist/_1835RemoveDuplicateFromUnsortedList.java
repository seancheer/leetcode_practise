package org.seancheer.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 这道题是一道订阅题，leetcode无法查看，可以从这个网站做这道题 https://practice.geeksforgeeks.org/problems/remove-duplicates-from-an-unsorted-linked-list/1
 *
 * @author: seancheer
 * @date: 2021/8/6
 **/
public class _1835RemoveDuplicateFromUnsortedList extends LinkedListParent {
    //Function to remove duplicates from unsorted linked list.

    /**
     * 解题思路：其实不难，使用一个set来存储出现过的数字，碰到重复的跳过即可
     * @param head
     * @return
     */
    public Node removeDuplicates(Node head) {
        if (null == head){
            return null;
        }

        Node dummyHead = new Node(-1);
        Node prev = dummyHead;
        Node cur = head;
        Set<Integer> set = new HashSet<>();

        while(null != cur){
            if (set.contains(cur.data)){
                cur = cur.next;
                continue;
            }

            set.add(cur.data);
            prev.next = cur;
            prev = cur;
            cur = cur.next;
            prev.next = null;
        }
        return dummyHead.next;
    }
}
