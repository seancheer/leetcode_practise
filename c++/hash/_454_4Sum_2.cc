//
// Created by ljt on 2026/6/10.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
using namespace std;

class Solution {
public:
    /**
     * 解题思路：四个数加起来等于0的组合个数
     * 解法：首先计算前两个数组的所有可能的和的个数，然后开始O(n^2)遍历后两个数组的和，一旦发现在第一个map中出现了-(a+b)，那么将结果+1，该解法的时间复杂度为
     * O(n^2)，可以将O(n^4)的时间复杂度降下来，该解法本质上也是一种暴力解法，只是这个暴力解法看起来聪明一些
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    int fourSumCount(vector<int> &nums1, vector<int> &nums2, vector<int> &nums3, vector<int> &nums4) {
        if (nums1.empty() || nums2.empty() || nums3.empty() || nums4.empty()) {
            return 0;
        }

        int result = 0;
        std::map<int, int> sumMap;
        for (auto &item: nums1) {
            for (auto &item2: nums2) {
                sumMap[item + item2]++;
            }
        }

        // 接下来遍历第二组数组，开始计算等于0的个数
        for (auto &item: nums3) {
            for (auto &item2: nums4) {
                int sum = -(item + item2);
                result += sumMap[sum];
            }
        }
        return result;
    }
};

int main() {
    Solution s;
    std::vector<int> nums1 = {1, 2};
    std::vector<int> nums2 = {-2, -1};
    std::vector<int> nums3 = {-1, 2};
    std::vector<int> nums4 = {0, 2};
    auto result = s.fourSumCount(nums1, nums2, nums3, nums4);
    printf("%d\n", result); // 2

    nums1 = {0};
    nums2 = {0};
    nums3 = {0};
    nums4 = {0};
    result = s.fourSumCount(nums1, nums2, nums3, nums4);
    printf("%d\n", result); // 1
}
