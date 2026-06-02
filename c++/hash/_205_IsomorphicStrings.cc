//
// Created by ljt on 2026/7/22.
//
#include <iostream>
#include <map>
#include <string>
#include <set>
using namespace std;


class Solution {
public:
    /**
     * 检查两个字符串是否为同构字符串，同构字符串的意思是指同为ABB形式的字符串, 但是加了一些限制，first字符串中相同的字符只能被一种字符替换，比如abbab和accae，这个时候就不是同构字符串,
     * 注意，当字符完全相当的时候，那么也算是一种映射，这个时候该字符就不能映射给其他字符了
     * 注意，s中的两个不同的字符不能映射到同一个字符
     * 解法1：直接遍历法，维护一个map记录字符替换历史（防止未来二次出现），如果字符相同，那么直接下一个，如果不同，则记录当前字符是否能相互替换，如果不能则返回false，如果可以则继续遍历
     * @param s
     * @param t
     * @return
     */
    bool isIsomorphic(string s, string t) {
        if (s.length() != t.length()) {
            return false;
        }
        char *m[128]{nullptr};
        char *m2[128]{nullptr};
        for (int i = 0, j = 0; i < s.length(); i++, j++) {
            // 在映射表中从未出现过
            if (m[s[i]] == nullptr) {
                // 这种场景是s中的两个字符同时映射到了t中的同一个字符
                if (m2[t[j]] != nullptr) {
                    return false;
                }
                m[s[i]] = &t[j];
                m2[t[j]] = &s[i];
                continue;
            }
            // 映射关系对不上
            if ((*m[s[i]]) != t[j]) {
                return false;
            }
        }
        return true;
    }
};


int main() {
    Solution s;
    std::string first = "egg";
    std::string second = "add";
    auto result = s.isIsomorphic(first, second);
    printf("%d\n", result); // true egg和add都是ABB形式的字符串，因此可以通过替换的方式得到另外一个

    first = "f11";
    second = "b23";
    result = s.isIsomorphic(first, second);
    printf("%d\n", result); // false 不是同构字符串

    first = "paper";
    second = "title";
    result = s.isIsomorphic(first, second);
    printf("%d\n", result); // true

    first = "abbab";
    second = "abbad";
    result = s.isIsomorphic(first, second);
    printf("%d\n", result); // false 一个字符只能被替换一次

    first = "accae";
    second = "abbab";
    result = s.isIsomorphic(first, second);
    printf("%d\n", result); // false first中的c和e不能同时映射到b

    first = "aa";
    second = "ab";
    result = s.isIsomorphic(first, second);
    printf("%d\n", result); // false 一个字符只能被替换一次
}
