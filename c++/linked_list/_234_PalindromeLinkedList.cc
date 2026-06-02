//
// Created by ljt on 2026/6/4.
//
#include <iostream>
#include <map>
#include <string>
#include <stack>
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
     * 解题思路：判断一个链表是否为回文，简单的方式是使用一个栈，然后在判断，但是题目要求O(n)的时间复杂度和O(1)的空间复杂度，这就有意思了
     * 解法1：先遍历一遍得到链表的长度，然后将前半部分的链表翻转，然后在和后半部分进行对比，注意如果是奇数个的话要跳过最中间的数，不过这个解法不是最优的
     * 解法2：使用额外的空间，将节点全部入栈，然后在进行判断
     * @param head
     * @return
     */
    bool isPalindrome(ListNode *head) {
        if (head == nullptr || head->next == nullptr) {
            return true;
        }
        int size = 0;
        ListNode *cur = head;
        while (cur != nullptr) {
            cur = cur->next;
            size++;
        }

        // 接下来翻转前半部分的链表
        ListNode *prev = head;
        cur = prev->next;
        head->next = nullptr;
        for (int i = 1; i < (size >> 1); i++) {
            ListNode *newCur = cur->next;
            cur->next = prev;
            prev = cur;
            cur = newCur;
        }
        ListNode *newHead = prev;
        // 奇数个就跳过最中间的数
        if (size % 2 != 0) {
            cur = cur->next;
        }

        while (newHead != nullptr && cur != nullptr) {
            if (newHead->val != cur->val) {
                return false;
            }
            newHead = newHead->next;
            cur = cur->next;
        }
        return true;
    }

    /**
     * 解法2：使用额外的空间，将所有的节点都放在一个栈里面，然后一边从头遍历，一边从栈中不断pop，循环的终止条件是遍历到数组的一半即可
     * @param head
     * @return
     */
    bool isPalindrome2(ListNode *head) {
        if (head == nullptr || head->next == nullptr) {
            return true;
        }
        std::stack<ListNode *> stack;
        ListNode *cur = head;
        while (cur != nullptr) {
            stack.push(cur);
            cur = cur->next;
        }

        // 接下来通过栈的方式判断回文
        cur = head;
        int sz = stack.size();
        int i = 0;
        while (i < sz) {
            if (stack.top()->val != cur->val) {
                return false;
            }
            stack.pop();
            cur = cur->next;
            ++i;
        }
        return true;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 2, 2, 1};
    ListNode *head = genLinkedList(nums, nullptr);
    auto result = s.isPalindrome(head);
    ListNode *head2 = genLinkedList(nums, nullptr);
    auto result2 = s.isPalindrome(head2);
    printf("result: %d  ---  result2: %d\n", result, result2); // 1
    freeNonCycleLinkedList(head);
    freeNonCycleLinkedList(head2);

    nums = {1, 2};
    head = genLinkedList(nums, nullptr);
    result = s.isPalindrome(head);
    head2 = genLinkedList(nums, nullptr);
    result2 = s.isPalindrome(head2);
    printf("result: %d  ---  result2: %d\n", result, result2); // 0
    freeNonCycleLinkedList(head);
    freeNonCycleLinkedList(head2);

    nums = {1, 1};
    head = genLinkedList(nums, nullptr);
    result = s.isPalindrome(head);
    head2 = genLinkedList(nums, nullptr);
    result2 = s.isPalindrome(head2);
    printf("result: %d  ---  result2: %d\n", result, result2); // 1
    freeNonCycleLinkedList(head);
    freeNonCycleLinkedList(head2);

    nums = {1, 2, 1};
    head = genLinkedList(nums, nullptr);
    result = s.isPalindrome(head);
    head2 = genLinkedList(nums, nullptr);
    result2 = s.isPalindrome(head2);
    printf("result: %d  ---  result2: %d\n", result, result2); // 1
    freeNonCycleLinkedList(head);
    freeNonCycleLinkedList(head2);
}
