//
// Created by ljt on 2026/6/2.
//

#ifndef HELLOWORLDPROJECT_LINKED_LIST_UTILS_H
#define HELLOWORLDPROJECT_LINKED_LIST_UTILS_H
#include <iostream>
#include <map>
#include <string>

struct ListNode {
    int val;
    ListNode *next;

    ListNode(int x) : val(x), next(NULL) {
    }
};

class Node {
public:
    int val;
    Node *next;
    Node *random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

/**
 * genLinkedList 根据数组生成一份列表
 * @param arr
 * @return
 */
ListNode *genLinkedList(std::vector<int> &arr, std::map<int, ListNode *> *m = nullptr) {
    if (arr.empty()) {
        return nullptr;
    }
    ListNode *head = new ListNode(arr[0]);
    if (m != nullptr) {
        (*m)[arr[0]] = head;
    }
    ListNode *cur = head;
    for (int i = 1; i < arr.size(); i++) {
        cur->next = new ListNode(arr[i]);
        cur = cur->next;
        if (m != nullptr) {
            (*m)[arr[i]] = cur;
        }
    }
    return head;
}

// freeNonCycleLinkedList 释放无环链表
void freeNonCycleLinkedList(ListNode *head) {
    while (head != nullptr) {
        ListNode *cur = head;
        head = head->next;
        delete cur;
    }
}

// freeCycleLinkedList 释放有环链表
void freeCycleLinkedList(std::map<int, ListNode *> &m) {
    for (auto &item: m) {
        if (item.second == nullptr) {
            continue;
        }
        delete item.second;
    }
}

//  printLinkedList 打印链表
void printLinkedList(ListNode *head) {
    if (head == nullptr) {
        printf("<EMPTY>\n");
        return;
    }
    while (head != nullptr) {
        printf("%d  ", head->val);
        head = head->next;
    }
    printf("\n");
}

// getTailOfLinkedList 获取链表的最后一个节点
ListNode *getTailOfLinkedList(ListNode *head) {
    if (head == nullptr) {
        return head;
    }
    while (head->next != nullptr) {
        head = head->next;
    }
    return head;
}

#endif //HELLOWORLDPROJECT_LINKED_LIST_UTILS_H
