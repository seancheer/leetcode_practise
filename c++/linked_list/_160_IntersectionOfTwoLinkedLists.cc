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
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    /**
     * 解题思路：获取两个链表的交点，注意，这道题要求O(m+n)的时间复杂度和O(1)的空间复杂度，如果不要求空间复杂度，这道题将会很简单，使用栈就可以实现
     * 解法1： 首先获取两个链表的长度，然后计算长度差，记为n，接下来长的链表先走n步，然后短的也可以走，这样子当两个节点第一次相等的时候就是最终的答案
     * 解法2：两个链表虽然各自的长度不一样，但是两个链表长度加起来肯定是固定的，那么，我们使用两个指针，第一个指针指向第一个链表，第二个指针指向第二个链表，然后同时移动两个指针，
     * 当第一个指针到末尾的时候开始遍历第二条链表，当第二个指针到末尾的时候开始遍历第一个链表，那么如果两个链表存在交点，那么当到交点的时候，两个指针走过的长度一定是一样的，如果没有交点，
     * 那么两个指针会一起到nullptr，很天才的想法
     * @param headA
     * @param headB
     * @return
     */
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        if (!(headA != nullptr && headB != nullptr)) {
            return nullptr;
        }
        // 接下来将开始计算两个链表的交点
        int sizeA = 0, sizeB = 0;
        ListNode *cur = headA;
        while (cur != nullptr) {
            cur = cur->next;
            sizeA++;
        }
        cur = headB;
        while (cur != nullptr) {
            cur = cur->next;
            sizeB++;
        }
        int gap = abs(sizeA - sizeB);
        ListNode *longList = headA;
        ListNode *shortList = headB;
        if (sizeB > sizeA) {
            longList = headB;
            shortList = headA;
        }

        // 长的先走n步
        int i = 0;
        while (i < gap) {
            longList = longList->next;
            i++;
        }

        // 接下来两个链表一起往前走
        while (longList != nullptr && shortList != nullptr) {
            if (longList == shortList) {
                return longList;
            }
            longList = longList->next;
            shortList = shortList->next;
        }
        return nullptr;
    }

    /**
     * 解法2： 两个指针一起遍历两个链表
     * @param headA
     * @param headB
     * @return
     */
    ListNode *getIntersectionNode2(ListNode *headA, ListNode *headB) {
        if (!(headA != nullptr && headB != nullptr)) {
            return nullptr;
        }
        ListNode *curA = headA;
        ListNode *curB = headB;
        while (curA != curB) {
            curA = curA != nullptr ? curA->next : headB;
            curB = curB != nullptr ? curB->next : headA;
        }
        return curA;
    }
};


int main() {
    Solution s;

    // 4,1,8,4,5       5,6,1,8,4,5
    std::vector<int> nums = {4, 1};
    std::vector<int> nums2 = {5, 6, 1};
    std::vector<int> common = {8, 4, 5};
    ListNode *head = genLinkedList(nums);
    ListNode *head2 = genLinkedList(nums2);
    ListNode *commonHead = genLinkedList(common);
    getTailOfLinkedList(head)->next = commonHead;
    getTailOfLinkedList(head2)->next = commonHead;
    auto result = s.getIntersectionNode(head, head2);
    if (result == nullptr) {
        printf("No Intersection!!!!\n");
    } else {
        printf("Intersection value: %d\n", result->val);
    }

    ListNode *result2 = s.getIntersectionNode2(head, head2);
    if (result2 == nullptr) {
        printf("No Intersection!!!!\n");
    } else {
        printf("Intersection value: %d\n", result2->val);
    }

    nums = {2, 6, 4};
    nums2 = {1, 5};
    head = genLinkedList(nums);
    head2 = genLinkedList(nums2);
    result = s.getIntersectionNode(head, head2);
    if (result == nullptr) {
        printf("No Intersection!!!!\n");
    } else {
        printf("Intersection value: %d\n", result->val);
    }

    result2 = s.getIntersectionNode2(head, head2);
    if (result2 == nullptr) {
        printf("No Intersection!!!!\n");
    } else {
        printf("Intersection value: %d\n", result2->val);
    }
}
