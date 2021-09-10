package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.（这个O(1)包含内部数据结构的时间复杂度么？）
 *
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * @author: seancheer
 * @date: 2021/7/27
 **/
public class _146LRUCache extends LeetCodeParent {

    public static void main(String[] args) {
        _146LRUCache obj = new _146LRUCache();
        LRUCache lRUCache = obj.getCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        var res = lRUCache.get(1);    // return 1
        System.out.println("res: " + res);
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        res = lRUCache.get(2);    // returns -1 (not found)
        System.out.println("res: " + res);
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        res = lRUCache.get(1);    // return -1 (not found)
        System.out.println("res: " + res);
        res = lRUCache.get(3);    // return 3
        System.out.println("res: " + res);
        res = lRUCache.get(4);    // return 4
        System.out.println("res: " + res);


        lRUCache = obj.getCache(1);
        lRUCache.put(2, 1);
        res = lRUCache.get(2);    // return 1
        System.out.println("res: " + res);
        lRUCache.put(3, 2);
        res = lRUCache.get(2);
        System.out.println("res: " + res);
        res = lRUCache.get(3);    // return -1 (not found)
        System.out.println("res: " + res);

        String[] operations = new String[]{"LRUCache","get","put","get","put","put","get","get"};
        String values = "[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]";
        testLRUCache(operations, convertStrTo2DemList(values));

        operations = new String[]{"LRUCache","put","get","put","get","get"};
        values = "[[1],[2,1],[2],[3,2],[2],[3]]";
        testLRUCache(operations, convertStrTo2DemList(values));
    }

    /**
     * 自动化测试LRUCache
     * @param operations
     * @param vals
     */
    private static void testLRUCache(String[] operations, List<List<Integer>> vals){
        System.out.println("--------------------------------------------------------------------------------------");
        _146LRUCache obj = new _146LRUCache();
        LRUCache lruCache = null;
        int i = 0;
        for(String operation : operations){
            List<Integer> val = vals.get(i);
            switch(operation){
                case "LRUCache":
                    lruCache = obj.getCache(val.get(0));
                    break;
                case "get":
                    var res = lruCache.get(val.get(0));
                    System.out.println("res:" + res);
                    break;
                case "put":
                    lruCache.put(val.get(0), val.get(1));
                    break;
                default:
                    throw new RuntimeException("Unsupported operation! operation name:" + operation);
            }
            ++i;
        }
    }

    public LRUCache getCache(int cap) {
        return new LRUCache(cap);
    }

    /**
     * 题目很简单，实现一个LRU Cache，基本思路是使用双向指针来实现，同时用map维护key-value，
     * 供快速查询使用
     */
    class LRUCache {
        private final CacheNode DUMMY = new CacheNode(true);
        private final int capacity;
        private Map<Integer, CacheNode> map;
        private int count = 0;
        private CacheNode head;
        private CacheNode tail;

        public LRUCache(int capacity) {
            map = new HashMap<Integer, CacheNode>();
            head = tail = DUMMY;
            this.capacity = capacity;
        }

        public int get(int key) {
            if(!map.containsKey(key)){
                return -1;
            }

            CacheNode curNode = map.get(key);
            int value = curNode.val;
            //刚好只有一个值，不需要更新任何东西。
            if (count == 1){
                return value;
            }

            //如果刚好get了tail，那么更新tail到前一个
            if (tail.key == key){
                tail = tail.prev;
            }
            CacheNode prev = curNode.prev;
            CacheNode next = curNode.next;
            prev.next = next;
            if (null != next) {
                next.prev = prev;
            }

            //将该key摞到最前面
            CacheNode oldHead = head.next;
            head.next = curNode;
            curNode.next = oldHead;
            if (null != oldHead){
                oldHead.prev = curNode;
            }
            curNode.prev = head;
            return value;
        }

        public void put(int key, int value) {
            //如果有该key，那么需要移动到最前面去，本质上就是一个get，然后再更新value即可
            if (get(key) != -1){
                //移动完成后，直接更新value
                CacheNode curNode = map.get(key);
                curNode.val = value;
                return;
            }
            //已经满了，去除掉尾部
            if (count == capacity) {
                assert (!tail.isDummy);
                int oldTailKey = tail.key;
                tail = tail.prev;
                tail.next = null;
                //从map中去掉该tail
                --count;
                map.remove(oldTailKey);
            }

            CacheNode oldHead = head.next;
            CacheNode cur = new CacheNode(key, value);
            head.next = cur;
            cur.next = oldHead;
            cur.prev = head;
            if (null != oldHead) {
                oldHead.prev = cur;
            }

            //更新tail
            if (tail.isDummy){
                tail = cur;
            }
            ++count;
            map.put(key, cur);
        }

        class CacheNode {
            public boolean isDummy = false;
            public CacheNode next;
            public CacheNode prev;
            public int key;
            public int val;

            public CacheNode(boolean isDummy) {
                this.isDummy = isDummy;
            }

            public CacheNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }
}
