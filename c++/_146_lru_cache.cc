//
// Created by ljt on 2026/6/10.
//
#include <iostream>
#include <map>
#include <string>

struct DoubleList {
    int key, val;
    DoubleList *next;
    DoubleList *prev;

    DoubleList(int key, int value) : key(key), val(value), next(nullptr), prev(nullptr) {
    }
};


/**
 * 解题思路：map+双端链表可实现该问题，因为get和put必须在O(1)的时间复杂度里实现，所以必须借助map，但是因为还存在lru算法，而双端链表则是为了给key进行排序的
 */
class LRUCache {
public:
    std::map<int, DoubleList *> cache;
    DoubleList *head;
    DoubleList *tail;
    int capacity;

    LRUCache(int capacity) {
        if (capacity <= 0) {
            throw std::runtime_error("invalid capacity");
        }
        this->capacity = capacity;
        this->head = this->tail = nullptr;
    }

    // get 找到当前节点的时候，将当前节点移动到双端链表的头部，如果本身就是头部，则无需做任何事情
    int get(int key) {
        auto result = get_internal(key);
        if (result == nullptr) {
            return -1;
        }
        return result->val;
    }

    DoubleList *get_internal(int key) {
        auto result = cache.find(key);
        if (result == cache.end()) {
            return nullptr;;
        }

        DoubleList *node = result->second;
        if (node->prev == nullptr) {
            return result->second;
        }
        // 移动指针到链表头部
        DoubleList *next = node->next;
        DoubleList *prev = node->prev;
        prev->next = node->next;
        if (next != nullptr) {
            next->prev = prev;
        } else {
            // 当年节点是最后一个节点，这里需要更新tail
            tail = prev;
        }

        // 接下来将当前节点和head节点关联起来
        head->prev = node;
        node->next = head;
        node->prev = nullptr;
        head = node;
        return node;
    }

    // put 将当前节点加入到链表的头部，如果当前head为空，则直接加入即可，如果超过capacity，则将链表末尾的值删除
    void put(int key, int value) {
        auto result = get_internal(key);
        // 找到了目标节点，且get函数会自动将目标节点移动到链表头部，因此这里无需处理任何事情
        if (result != nullptr) {
            // 更新最新的值
            result->val = value;
            return;
        }
        DoubleList *node = new DoubleList(key, value);
        cache[key] = node;
        // 将该节点放在链表头部
        if (head == nullptr) {
            head = node;
            tail = node;
        } else {
            node->next = head;
            head->prev = node;
            node->prev = nullptr;
            head = node;
        }

        if (cache.size() <= capacity) {
            return;
        }
        // 如果当前cache的容量超过capacity，那么移除掉末尾的值
        DoubleList *prev = tail->prev;
        prev->next = nullptr;
        this->cache.erase(tail->key);
        // 释放tail指针
        delete tail;
        // 更新新的tail
        tail = prev;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */

int main() {
    LRUCache lRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    printf("%d -- ", lRUCache.get(1)); // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    printf("%d -- ", lRUCache.get(2)); // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    printf("%d -- ", lRUCache.get(1)); // return -1 (not found)
    printf("%d -- ", lRUCache.get(3)); // return 3
    printf("%d", lRUCache.get(4)); // return 4
    printf("\n");
}
