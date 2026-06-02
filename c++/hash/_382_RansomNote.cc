//
// Created by ljt on 2026/7/15.
//
#include <iostream>
#include <map>
#include <string>
#include <set>
using namespace std;

class Solution {
public:
    /**
     * magazine的字母是否能构造出来ransomNote
     * 解法：解法很简单，统计下ransomNote和magazine中每个字母出现的次数，如果可以完全匹配的话，那么就返回true，否则返回false，该问题并不关心字母分别出现的顺序
     * @param ransomNote
     * @param magazine
     * @return
     */
    bool canConstruct(string ransomNote, string magazine) {
        if (ransomNote == "" || magazine == "") {
            return false;
        }

        // 首先统计magazine的字符出现次数
        int countMap[26] = {0}; // 注意c++只有这样才能将数组里的所有值都设置为0，不然得话，值都是随机的
        for (auto &c: magazine) {
            countMap[c - 'a']++;
        }

        int countMap2[26] = {0};
        for (auto &c: ransomNote) {
            int idx = c - 'a';
            countMap2[idx]++;
            // 短路求值，发现ransomNote中存在某个字符的数量大于magazine，这个时候magazine肯定是无法构造出ransomNote的
            if (countMap2[idx] > countMap[idx]) {
                return false;
            }
        }
        return true;
    }
};

int main() {
    Solution s;
    std::string ransomNote = "a";
    std::string magazine = "b";
    auto result = s.canConstruct(ransomNote, magazine);
    printf("%d\n", result); // false

    ransomNote = "aa";
    magazine = "ab";
    result = s.canConstruct(ransomNote, magazine);
    printf("%d\n", result); // false

    ransomNote = "aa";
    magazine = "aab";
    result = s.canConstruct(ransomNote, magazine);
    printf("%d\n", result); // true

    ransomNote = "ab";
    magazine = "ba";
    result = s.canConstruct(ransomNote, magazine);
    printf("%d\n", result); // true
}
