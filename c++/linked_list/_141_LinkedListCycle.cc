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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    /**
     * 解题思路：判断一个链表是否存在环，这个很简单，一个指针走一步，一个走两步，如果相遇则证明有环，否则无环
     * @param head
     * @return
     */
    bool hasCycle(ListNode *head) {
        if (head == nullptr) {
            return false;
        }

        ListNode *first = head;
        ListNode *second = head;
        while (second != nullptr) {
            first = first->next;
            // 第2个指针走两步
            second = second->next;
            if (second != nullptr) {
                second = second->next;
            }
            if (second != nullptr && first == second) {
                return true;
            }
        }
        return false;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {3, 2, 0, -4};
    std::map<int, ListNode *> m;
    ListNode *head = genLinkedList(nums, &m);
    // 将-4指向2
    m[-4]->next = m[2];
    bool result = s.hasCycle(head);
    printf("%d\n", result);
    freeCycleLinkedList(m);
}


