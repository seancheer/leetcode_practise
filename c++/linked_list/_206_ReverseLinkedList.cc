//
// Created by ljt on 2026/6/4.
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
     * 解题思路：直接原地进行翻转，维护一个cur指针，然后不断的将cur.next指向prev0
     * @param head
     * @return
     */
    ListNode *reverseList(ListNode *head) {
        if (nullptr == head || nullptr == head->next) {
            return head;
        }
        ListNode *prev = head;
        ListNode *cur = head->next;
        head->next = nullptr;
        while (cur != nullptr) {
            ListNode *newCur = cur->next;
            cur->next = prev;
            prev = cur;
            cur = newCur;
        }
        return prev;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 2, 3, 4, 5};
    ListNode *head = genLinkedList(nums, nullptr);
    auto result = s.reverseList(head);
    printLinkedList(result);
    freeNonCycleLinkedList(head);

    nums = {1, 2};
    head = genLinkedList(nums, nullptr);
    result = s.reverseList(head);
    printLinkedList(result);
    freeNonCycleLinkedList(head);
}
