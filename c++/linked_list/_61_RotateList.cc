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
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    /**
     * 解题思路：旋转链表，首先注意，k值可能会比链表的长度大
     * 解法：先获取链表的长度，然后将长度/k，得到的值为等效的旋转次数（该次数肯定小于链表长度），接下来长度-k为旋转后的链表头，得到该链表头后，然后调整指针就是最后的结果
     * @param head
     * @param k
     * @return
     */
    ListNode *rotateRight(ListNode *head, int k) {
        if (head == nullptr || head->next == nullptr || k == 0) {
            return head;
        }

        int sz = 0;
        ListNode *cur = head;
        while (cur != nullptr) {
            cur = cur->next;
            ++sz;
        }

        // 得到等效旋转次数
        int count = k % sz;
        if (count == 0) {
            // 旋转了个寂寞
            return head;
        }

        cur = head;
        int i = 0;
        ListNode *prev = cur;
        while (i++ < (sz - count)) {
            prev = cur;
            cur = cur->next;
        }
        prev->next = nullptr; // 这里是旋转后链表的末尾
        // 得到了旋转后的链表头部
        ListNode *result = cur;
        // 将cur移动链表末尾
        while (cur->next != nullptr) {
            cur = cur->next;
        }
        cur->next = head; // 将链表的末尾指向链表的头部
        return result;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 2, 3, 4, 5};
    ListNode *head = genLinkedList(nums);
    auto result = s.rotateRight(head, 2);
    printLinkedList(result); // 4,5,1,2,3

    nums = {0, 1, 2};
    head = genLinkedList(nums);
    result = s.rotateRight(head, 4);
    printLinkedList(result); // 2,0,1
}
