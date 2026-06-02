//
// Created by ljt on 2026/8/26.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
using namespace std;

class Solution {
public:
    /**
     * 经典的接雨水问题，双指针解法
     * 解题思路：两个指针分别从两端遍历，为两边分别维护一个最大值，记为leftMax和rightMax，比较两端的最大值，哪个小就移动哪边的指针，同时计算当前的储水量，
     * 计算方式为，sum += (curVal - leftMax/rightMax)
     * @param height
     * @return
     */
    int trap(vector<int> &height) {
        if (height.size() <= 2) {
            return 0;
        }
        int leftMax = height[0];
        int rightMax = height[height.size() - 1];
        int i = 0, j = height.size() - 1;
        int sum = 0;
        while (i < j) {
            leftMax = max(leftMax, height[i]);
            rightMax = max(rightMax, height[j]);
            if (leftMax <= rightMax) {
                sum += leftMax - height[i];
                i++;
            } else {
                sum += rightMax - height[j];
                j--;
            }
        }
        return sum;
    }
};


int main() {
    Solution s;
    std::vector<int> height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    auto result = s.trap(height);
    printf("%d\n", result); // 6

    height = {4, 2, 0, 3, 2, 5};
    result = s.trap(height);
    printf("%d\n", result); // 9

    height = {1, 4, 4, 5, 1, 9, 0, 9, 2, 2, 1, 5, 8, 8, 6, 0, 3};
    result = s.trap(height);
    printf("%d\n", result); // 38

    height = {0, 1, 0, 0, 4, 4, 8, 9, 2, 8, 7, 5, 3, 3, 0, 1, 8, 9, 5, 7, 9, 0, 8, 3, 0};
    result = s.trap(height);
    printf("%d\n", result); // 60

    height = {2, 0, 2};
    result = s.trap(height);
    printf("%d\n", result); // 2
}
