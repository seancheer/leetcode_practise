//
// Created by ljt on 2026/7/15.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
#include <stack>
using namespace std;

class Solution {
public:
    /**
     * 检查括号是否成对出现，这是一个很常见的stack题目，没什么好说的
     * @param s
     * @return
     */
    bool isValid(string s) {
        if (s.size() == 0) {
            return true;
        }

        std::stack<char> st;
        for (auto &c: s) {
            if (st.empty()) {
                st.push(c);
                continue;
            }

            char top = st.top();
            if (dict[c] == top) {
                st.pop();
            } else {
                st.push(c);
            }
        }
        return st.empty(); // 只有当栈中的数据都匹配完成后才是合法的
    }

    std::map<char, char> dict = {
        {')', '('},
        {'}', '{'},
        {']', '['}
    };
};

int main() {
    Solution s;
    auto result = s.isValid("()");
    printf("%d\n", result); // true

    result = s.isValid("()[]{}");
    printf("%d\n", result); // true

    result = s.isValid("([])");
    printf("%d\n", result); // true

    result = s.isValid("([)]");
    printf("%d\n", result); // false
}
