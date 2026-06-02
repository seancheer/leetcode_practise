//
// Created by ljt on 2026/6/7.
//


#include <iostream>
#include <map>
#include <string>
#include "linked_list_utils.h"
using namespace std;


/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/


class Solution {
public:
    /**
     * 解题思路：对一个带有任意指针的链表进行深拷贝，拷贝完的状态必须和之前的一致， 这道题没有任何特殊的要求
     * 解法1：最朴素的解法，使用一个map，维护旧链表节点和新链表节点的映射，然后通过旧链表的节点的random指针找到新链表节点，直接指向对应的新节点即可
     * 这道题还有不用哈希表的方法
     * 解法2：对于每一个旧节点，都创建一个新节点，然后旧节点指向新节点，新节点指向旧节点的下一个；完成之后，那么新节点的random就是旧节点random的下一个，然后再将新节点提取出来，旧节点恢复原来的样子，
     * 这道题要求传入的链表不能被修改
     * @param head
     * @return
     */
    Node *copyRandomList(Node *head) {
        if (head == nullptr) {
            // 这道题必须要进行拷贝
            return nullptr;
        }
        Node newHead(-1);
        Node *newCur = &newHead;
        Node *cur = head;
        std::map<Node *, Node *> m;
        while (cur != nullptr) {
            Node *node = new Node(cur->val);
            newCur->next = node;
            m[cur] = node;
            cur = cur->next;
            newCur = newCur->next;
        }

        // 接下来处理random指针
        newCur = newHead.next;
        cur = head;
        while (cur != nullptr) {
            if (cur->random != nullptr) {
                newCur->random = m[cur->random];
            }
            newCur = newCur->next;
            cur = cur->next;
        }
        return newHead.next;
    }

    // copyRandomList2 解法2：将新节点加入到旧节点的next指针中，然后在构建random指针即可
    Node *copyRandomList2(Node *head) {
        if (head == nullptr) {
            // 这道题必须要进行拷贝
            return nullptr;
        }

        Node *cur = head;
        while (cur != nullptr) {
            Node *oldNext = cur->next;
            cur->next = new Node(cur->val);
            cur->next->next = oldNext;
            cur = oldNext;
        }
        cur = head;
        // 将新节点加入到原链表中后，再构造random指针
        while (cur != nullptr) {
            Node *random = cur->random;
            Node *newNode = cur->next;
            if (random != nullptr) {
                newNode->random = random->next;
            }
            cur = newNode->next; // 继续下一个旧链表的指针
        }

        // 最后将新旧链表分开
        cur = head;
        Node result(1);
        Node *cur2 = &result;
        while (cur != nullptr) {
            Node *newNode = cur->next;
            cur2->next = newNode;
            cur->next = newNode->next; // 将旧链表的指针恢复
            cur = newNode->next; // 移动指针到旧链表的下一个节点
            cur2 = newNode;
        }
        return result.next;
    }
};

int main() {
    Solution s;
    // 这道题构造起来比较麻烦，就不写测试了，直接在leetcode官方页面做测试
    printf("Only for debug. Not to test.\n");
}
