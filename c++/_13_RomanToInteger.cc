//
// Created by ljt on 2026/5/29.
//

#include <iostream>
#include <map>
#include <string>
using namespace std;

class Solution {
public:
    /**
    * 解题思路：罗马数字转int，这题很简单，查到当前数字的时候往前看一个，如果符合下面的规则，就合并，不符合就单独加，需要注意的是，罗马数字是从大到小写的，如果发现
    * 小到大的顺序，则需要减，而不是加，这点需要注意
    * Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
        以下三个是固定搭配
    * I can be placed before V (5) and X (10) to make 4 and 9.
    * X can be placed before L (50) and C (100) to make 40 and 90.
    * C can be placed before D (500) and M (1000) to make 400 and 900.
    * */
    std::map<char, int> symbol2Num = {
        {'I', 1},
        {'V', 5},
        {'X', 10},
        {'L', 50},
        {'C', 100},
        {'D', 500},
        {'M', 1000}
    };
    std::map<std::string, int> special = {
        {"IV", 4},
        {"IX", 9},
        {"XL", 40},
        {"XC", 90},
        {"CD", 400},
        {"CM", 900}
    };

    int romanToInt(string s) {
        if (s.empty()) {
            return 0;
        }
        int i = 0;
        int result = 0;
        while (i < s.length()) {
            char cur = s[i];
            switch (cur) {
                case 'I':
                case 'X':
                case 'C':
                    if (i + 1 < s.length()) {
                        std::string key = s.substr(i, 2);
                        if (special.find(key) != special.end()) {
                            result += special[key];
                            i += 2;
                            continue;
                        }
                    }
                    result += symbol2Num[cur];
                    i++;
                    break;
                default:
                    result += symbol2Num[cur];
                    i++;
                    break;
            }
        }
        return result;
    }
};

int main() {
    Solution s;

    auto result = s.romanToInt("III");
    printf("%d\n", result); // 3

    result = s.romanToInt("LVIII");
    printf("%d\n", result); // 58

    result = s.romanToInt("MCMXCIV");
    printf("%d\n", result); // 1994

    result = s.romanToInt("IX");
    printf("%d\n", result); // 9

    result = s.romanToInt("IV");
    printf("%d\n", result); // 4
}
