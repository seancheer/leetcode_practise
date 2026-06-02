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
     * 解题思路：移除倒数第N个链表节点，题目要求只遍历一遍，注意，由于这里只是做题，所以就不释放ListNode所占用的内存了
     * 解法一：使用一个map存储index->ListNode的映射，然后直接删除倒数第n个即可
     * 解法二：第一个指针先跑n步，然后第二个指针从头开始两个一起跑，当第一个指针到达末尾后，第二指针就是目标位置（很精巧的解法），该解法的空间复杂度为O(1)
     * @param head
     * @param n
     * @return
     */
    ListNode *removeNthFromEnd(ListNode *head, int n) {
        if (head == nullptr) {
            return head;
        }
        std::map<int, ListNode *> m;
        ListNode *cur = head;
        int idx = 0;
        while (cur != nullptr) {
            m[idx] = cur;
            cur = cur->next;
            idx++;
        }

        // 接下来直接删除倒数第n个节点即可
        int target = m.size() - n;
        if (target <= 0) {
            return head->next;
        }
        // 将targe的前一个指向targe的下一个
        m[target - 1]->next = m[target]->next;
        return head;
    }

    /**
   * 解题思路：移除倒数第N个链表节点，题目要求只遍历一遍，注意，由于这里只是做题，所以就不释放ListNode所占用的内存了
   * 解法一：使用一个map存储index->ListNode的映射，然后直接删除倒数第n个即可
   * @param head
   * @param n
   * @return
   */
    ListNode *removeNthFromEnd2(ListNode *head, int n) {
        if (head == nullptr) {
            return head;
        }
        ListNode *fast = head;
        ListNode *slow = head;
        ListNode *before_slow = nullptr;;
        int idx = 0;
        while (fast != nullptr && idx++ < n) {
            fast = fast->next;
        }

        // 接下来开始移动slow
        while (fast != nullptr) {
            before_slow = slow;
            slow = slow->next;
            fast = fast->next;
        }

        // 直接把头部元素给删除了
        if (before_slow == nullptr) {
            return head->next;
        }
        // 跳过目标节点
        before_slow->next = slow->next;
        return head;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 2, 3, 4, 5};
    ListNode *head = genLinkedList(nums, nullptr);
    ListNode *result = s.removeNthFromEnd(head, 2);
    ListNode *head2 = genLinkedList(nums, nullptr);
    ListNode *result2 = s.removeNthFromEnd(head2, 2);
    printLinkedList(result); // 1,2,3,5 移除了4
    printLinkedList(result2); // 1,2,3,5 移除了4
    freeNonCycleLinkedList(result);
    freeNonCycleLinkedList(result2);

    nums = {};
    head = genLinkedList(nums, nullptr);
    result = s.removeNthFromEnd(head, 1);
    head2 = genLinkedList(nums, nullptr);
    result2 = s.removeNthFromEnd(head2, 2);
    printLinkedList(result); // []
    printLinkedList(result2); // []
    freeNonCycleLinkedList(result);
    freeNonCycleLinkedList(result2);

    nums = {1, 2};
    head = genLinkedList(nums, nullptr);
    head2 = genLinkedList(nums, nullptr);
    result = s.removeNthFromEnd(head, 2);
    result2 = s.removeNthFromEnd(head2, 2);
    printLinkedList(result); // [1]
    printLinkedList(result2); // [1]
    freeNonCycleLinkedList(result);
    freeNonCycleLinkedList(result2);

    nums = {1, 2, 3, 4, 5, 6};
    head = genLinkedList(nums, nullptr);
    head2 = genLinkedList(nums, nullptr);
    result = s.removeNthFromEnd(head, 6);
    result2 = s.removeNthFromEnd2(head2, 6);
    printLinkedList(result); // [2,3,4,5,6]
    printLinkedList(result2); // [2,3,4,5,6]
    freeNonCycleLinkedList(result);
    freeNonCycleLinkedList(result2);
}

