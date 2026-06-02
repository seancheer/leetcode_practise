//
// Created by ljt on 2026/7/23.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
using namespace std;


class Solution {
public:
    /**
     * 一个数的每个位数的平方之和最终等于1的称之为快乐数，需要一直这么递归算下去，直到出现每位平方之和等于1停止或者说这么一直循环下去
     * 解法1：暴力硬算解法，使用一个map记录出现过的所有数字，一旦发现某个数字出现过那么说明进入了无限循环，这个时候就可以直接返回false了，这道题有更好的解法吗？
     * 解法2：快慢指针法，注意，判断一个数字是否为快乐数字，那么最终的结果往往要么是陷入到一个死循环中（解法1使用map的原因），一个最终得到结果1，那么这道题其实就可以转换为判断一个链表是否有环，
     * 这个时候完全可以一个指针每次算一个数，一个指针每次算两个数，如果不是快乐数的话，那么最终的结果一定会相遇，注意，快乐数不可能永远无限制的增大的，因为99999999的各位平方之和也不过是一个4位数，
     * 其他值各位平方之和只会更小
     * 解法3：该解法就不实现了，这个解法本质上是一个数学游戏，
     * @param n
     * @return
     */
    bool isHappy(int n) {
        if (n == 1) {
            return true; // 其实题目要求保证了不会出现比1小的值
        }

        std::set<int> m;
        m.insert(n);
        int num = n;
        while (true) {
            int sum = 0;
            while (num > 0) {
                int tmp = num % 10;
                sum += (tmp * tmp);
                num /= 10;
            }
            if (sum == 1) {
                // 满足条件了
                return true;
            }
            if (m.count(sum) == 1) {
                return false;
            }
            m.insert(sum);
            // 使用新值进行下一次循环
            num = sum;
        }
    }

    /**
     * 解法2：快慢指针法，一个一次只计算一步，一个一次计算2步，当两个值相等的时候，说明出现了环，该数字就肯定不是一个快乐数
     * 其实本质山是判断一个链表是否存在环
     * @param n
     * @return
     */
    bool isHappy2(int n) {
        if (n == 1) {
            return true; // 其实题目要求保证了不会出现比1小的值
        }

        int slow = this->calcHappyNum(n);
        int fast = this->calcHappyNum(this->calcHappyNum(n));
        if (slow == 1 || fast == 1) {
            return true;
        }
        while (slow != fast) {
            slow = this->calcHappyNum(slow);
            // 不同于链表的是，这里无需考虑链表末尾为null的情况
            fast = this->calcHappyNum(this->calcHappyNum(fast));
            if (slow == 1 || fast == 1) {
                return true;
            }
        }
        return false;
    }

    int calcHappyNum(int num) {
        // 可以保证快乐数的最终结果不会超过int32的最大值
        int sum = 0;
        while (num > 0) {
            int tmp = num % 10;
            sum += (tmp * tmp);
            num /= 10;
        }
        return sum;
    }
};


int main() {
    Solution s;
    auto result = s.isHappy(19);
    auto result2 = s.isHappy2(19);
    printf("%d   %d\n", result, result2); // 1 最终每个位的平方之和一定能等于1

    result = s.isHappy(2);
    result2 = s.isHappy2(2);
    printf("%d   %d\n", result, result2); // 0
}
