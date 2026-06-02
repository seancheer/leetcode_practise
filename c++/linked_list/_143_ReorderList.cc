//
// Created by ljt on 2026/6/7.
//


#include <iostream>
#include <map>
#include <string>
#include "linked_list_utils.h"
using namespace std;

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    /**
     * 解题思路：重排链表，将链表的第i个位置和第n-i-1个位置的数字连接起来，题目要求不能修改ListNode节点本身的值，只能修改链表本身，也就是只能改变链表的指向而不能改变节点本身的值
     * 解法1： 使用一个栈，先将所有的节点加入到栈中，然后调节链表的next指针，只遍历到链表的size/2的位置即可
     * 解法2: 还有一个空间占用复杂度O(1)的解法，这里介绍如下
     * @param head
     */
    void reorderList(ListNode *head) {
        if (nullptr == head || nullptr == head->next || nullptr == head->next->next) {
            return;
        }
        std::stack<ListNode *> st;
        ListNode *cur = head;
        while (cur != nullptr) {
            st.push(cur);
            cur = cur->next;
        }

        size_t sz = st.size();
        int i = 0;
        cur = head;
        while (i < (sz >> 1)) {
            ListNode *oldNext = cur->next;
            ListNode *top = st.top();
            cur->next = top;
            top->next = oldNext;
            cur = oldNext;
            i++;
            st.pop();
        }
        cur->next = nullptr;
    }

    /**
     * 空间复杂度为O(1)的解法, 找到链表中间的位置后，然后将后半部分的链表进行反转，然后在进行链表的合并, 注意奇数个和偶数个中间的位置是不一样的
     * @param head
     */
    void reorderList2(ListNode *head) {
        if (nullptr == head || nullptr == head->next || nullptr == head->next->next) {
            return;
        }
        ListNode *cur = head;
        int sz = 0;
        while (cur != nullptr) {
            sz++;
            cur = cur->next;
        }

        // 记录mid的开头链表
        int i = 0;
        cur = head;
        while (i++ < (sz >> 1)) {
            cur = cur->next;
        }
        ListNode *prev = cur;
        cur = cur->next;
        prev->next = nullptr;
        // 接下来反转后半部分链表
        while (cur != nullptr) {
            ListNode *oldNext = cur->next;
            cur->next = prev;
            prev = cur;
            cur = oldNext;
        }
        ListNode *mid = prev;
        cur = head;
        // 接下来对前半部分和后半部分的链表进行合并
        i = 0;
        while (++i < (sz >> 1)) {
            ListNode *oldNext = cur->next;
            ListNode *oldNext2 = mid->next;
            cur->next = mid;
            // 避免奇数个链表节点的时候将后半部分的链表破坏
            mid->next = oldNext;
            cur = oldNext;
            mid = oldNext2;
        }
        // 最后一个节点不要忘记了连接
        cur->next = mid;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 2, 3, 4};
    ListNode *head = genLinkedList(nums, nullptr);
    s.reorderList(head);
    ListNode *head2 = genLinkedList(nums, nullptr);
    s.reorderList2(head2);
    printLinkedList(head); // 1,4,2,3
    printLinkedList(head2); // 1,4,2,3

    nums = {1, 2, 3, 4, 5};
    head = genLinkedList(nums, nullptr);
    s.reorderList(head);
    head2 = genLinkedList(nums, nullptr);
    s.reorderList2(head2);
    printLinkedList(head); // 1,5,2,4,3
    printLinkedList(head2); // 1,5,2,4,3

    nums = {1, 2, 3};
    head = genLinkedList(nums, nullptr);
    s.reorderList(head);
    head2 = genLinkedList(nums, nullptr);
    s.reorderList2(head2);
    printLinkedList(head); // 1,3,2
    printLinkedList(head2); // 1,3,2
}
