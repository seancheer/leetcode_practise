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
     * 判断s和pattern是否能对上，其中pattern中的单个字符必须和s中的单个单词一一对应，不能出现一对多，多对一或者多对多的场景，该题目和205类似
     * 解法1：维护两个map用来记录pattern和单词之间的相互映射，没遍历到一个位置的时候，首先在字典中进行查找，如果查找到且能对上，则continue，在字典中不存在的话就记录映射，如果在字典中存在，
     * 但是映射对不上的话，这个时候return false即可
     * @param pattern
     * @param s
     * @return
     */
    bool wordPattern(string pattern, string s) {
        if ((pattern != "" && s == "") || (pattern == "" && s != "")) {
            return false;
        }

        std::map<char, string> m1;
        std::map<string, char> m2;
        int j = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern[i];
            // 解析s中的下一个单词, c++啰嗦的点就在于这里解析word花费了大量的代码，在其他语言中用一个split函数就能搞定
            if (j >= s.size()) {
                return false;
            }
            int k = j + 1;
            while (k < s.size() && s[k] != ' ') {
                k++;
            }
            std::string word = s.substr(j, k - j);
            if (m1.count(c) == 0 && m2.count(word) == 0) {
                // 映射关系完全不存在
                m1[c] = word;
                m2[word] = c;
            } else if (m1.count(c) > 0 && m2.count(word) > 0) {
                // 在字典中均存在，需要检查当前的映射是否能完全对上
                if (m1[c] != word || m2[word] != c) {
                    return false;
                }
            } else {
                // 一个存在映射关系一个不存在
                return false;
            }

            // 跳过空格
            j = ++k;
        }

        // pattern用完了，但是s没用完
        if (j < s.size()) {
            return false;
        }
        return true;
    }
};


int main() {
    Solution s;
    std::string pattern = "abba";
    std::string words = "dog cat cat dog";
    auto result = s.wordPattern(pattern, words);
    printf("%d\n", result); // true

    pattern = "abba";
    words = "dog cat cat fish";
    result = s.wordPattern(pattern, words);
    printf("%d\n", result); // false

    pattern = "aaaa";
    words = "dog cat cat dog";
    result = s.wordPattern(pattern, words);
    printf("%d\n", result); // false

    pattern = "abba";
    words = "dog cat cat dog dog";
    result = s.wordPattern(pattern, words);
    printf("%d\n", result); // false

    pattern = "abb";
    words = "dog cat cat dog";
    result = s.wordPattern(pattern, words);
    printf("%d\n", result); // false
}
