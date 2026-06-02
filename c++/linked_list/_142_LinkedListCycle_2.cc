//
// Created by ljt on 2026/6/8.
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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    /**
     * 解题思路：高级版的判断链表是否存在环，这个环不是尾部指向头部的，而不是尾部指向任意其中的一个链表节点，题目要求使用O(1)的空间复杂度
     * 如果使用快慢指针，当指针相遇的时候就是有环的，但是相遇的点不一定就是环开始的地方，这个判断仅用来判断是否有环，没办法判断环开始的地方
     * 解法：先使用快慢指针，判断是否存在环，如果存在的话，那么相遇的节点一定在环内，然后从相遇的地点遍历链表，当又回到相遇的节点时，那么就可以得到环的长度，
     * 接下来一个继续快慢指针，一个在头指针的位置，另外一个走环长度步，接下来两个指针一起往前，当快指针的next等于慢指针的时候，那么慢指针的位置就是最终的结果
     * @param head
     * @return
     */
    ListNode *detectCycle(ListNode *head) {
        if (head == nullptr || head->next == nullptr) {
            return nullptr;
        }

        ListNode *slow = head;
        ListNode *fast = head->next;
        while (slow != fast) {
            if (slow == nullptr || fast == nullptr) {
                return nullptr; // 不存在环
            }
            slow = slow->next;
            fast = fast->next;
            if (fast == nullptr) {
                return nullptr;
            }
            fast = fast->next;
        }

        // 存在环，接下来记住相遇的点，开始计算环的长度
        int i = 1;
        ListNode *cur = slow->next;
        while (cur != slow) {
            cur = cur->next;
            ++i;
        }
        // 得到了环的长度，接下来从头开始，快指针先走环长度步
        int cycle_len = i;
        i = 1;
        slow = head;
        fast = head;
        while (i++ < cycle_len) {
            fast = fast->next;
        }
        // 接下来开始同时往前走, 当fast的下一个位置和slow相等的时候，那么slow的位置就是环开始的位置
        while (slow != fast->next) {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {3, 2, 0, -4, -5};
    ListNode *head = genLinkedList(nums);
    ListNode *tail = getTailOfLinkedList(head);
    tail->next = head->next;
    auto result = s.detectCycle(head);
    printf("%d\n", result->val); // 2

    nums = {1, 2};
    head = genLinkedList(nums);
    tail = getTailOfLinkedList(head);
    tail->next = head;
    result = s.detectCycle(head);
    printf("%d\n", result->val); // 1
}
