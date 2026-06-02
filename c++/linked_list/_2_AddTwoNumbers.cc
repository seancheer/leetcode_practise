//
// Created by ljt on 2026/6/2.
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
     * 解题思路：链表的数字加法，这道题没什么好说的，按照直觉加法就行，注意处理进位问题以及最后链表遍历完成之后最末尾的进位
     * @param l1
     * @param l2
     * @return
     */
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        if (nullptr == l1 || nullptr == l2) {
            if (nullptr != l1) {
                return l1;
            } else if (nullptr != l2) {
                return l2;
            } else {
                return nullptr;
            }
        }

        ListNode result(0);
        ListNode *cur = &result;
        bool isCarry = false;
        auto addNumberFunc = [&](ListNode *first, ListNode *second)-> int {
            int sum = 0;
            if (first != nullptr) {
                sum += first->val;
            }
            if (second != nullptr) {
                sum += second->val;
            }
            if (isCarry) {
                sum += 1;
            }

            if (sum >= 10) {
                isCarry = true;
                return sum - 10;
            } else {
                isCarry = false;
                return sum;
            }
        };
        while (nullptr != l1 || nullptr != l2) {
            int sum = addNumberFunc(l1, l2);
            cur->next = new ListNode(sum);
            cur = cur->next;
            if (nullptr != l1) {
                l1 = l1->next;
            }
            if (nullptr != l2) {
                l2 = l2->next;
            }
        }
        // 注意处理最后一个位置的进位
        if (isCarry) {
            cur->next = new ListNode(1);
        }
        return result.next;
    }
};

int main() {
    Solution s;
    std::vector<int> nums = {2, 4, 3};
    std::vector<int> nums2 = {5, 6, 4};
    ListNode *head = genLinkedList(nums, nullptr);
    ListNode *head2 = genLinkedList(nums2, nullptr);
    ListNode *result = s.addTwoNumbers(head, head2);
    printLinkedList(result); // 7,0,8

    nums = {0};
    nums2 = {0};
    head = genLinkedList(nums, nullptr);
    head2 = genLinkedList(nums2, nullptr);
    result = s.addTwoNumbers(head, head2);
    printLinkedList(result); // 0

    nums = {9, 9, 9, 9, 9, 9, 9};
    nums2 = {9, 9, 9, 9};
    head = genLinkedList(nums, nullptr);
    head2 = genLinkedList(nums2, nullptr);
    result = s.addTwoNumbers(head, head2);
    printLinkedList(result); // 8,9,9,9,0,0,0,1
}
