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
     * 解题思路：和谐数组定义为最大和最小值之间的差值仅为1，查找满足和谐数组定义的最大子序列，注意，不是子串！！！！
     * 解法1：首先使用一个map统计每个数字出现的次数，当访问到数字nums[i]的时候，就将map[nums[i]]以及map[nums[i]]和map[nums[i]+1]的值加起来，查找最大值即可，注意这里需要处理0值，
     * 0值表示没有对应的数字，此时不应该被统计在里面；注意这里只需要处理nums[i]和nums[i]+1，无需关心nums[i]-1，因为如果nums[i]-1存在于数组中的话，那么nums[i]和nums[i]+1的解法就一定能保证统计到
     * 解法2: 使用排序+双指针的解法，排完序后使用双指针维护最小值和最大值之差等于1的窗口并持续性的更新最大长度即可
     * 如果不允许
     * @param nums
     * @return
     */
    int findLHS(vector<int> &nums) {
        if (nums.size() < 2) {
            return 0;
        }

        std::map<int, int> m;
        for (auto &num: nums) {
            m[num]++;
        }

        int result = 0;
        for (auto &num: nums) {
            // 说明此时根本没有满足条件的数组
            if (m[num + 1] == 0) {
                continue;
            }
            int tmp = m[num] + m[num + 1];
            result = max(result, tmp);
        }
        return result;
    }


    /**
     * 解法2：排序+双指针的解法： 使用排序+双指针的解法，排完序后使用双指针维护最小值和最大值之差等于1的窗口并持续性的更新最大长度即可
     * @param nums
     * @return
     */
    int findLHS2(vector<int> &nums) {
        if (nums.size() < 2) {
            return 0;
        }
        int result = 0;
        std::sort(nums.begin(), nums.end());
        int i = 0, j = 1;
        while (j < nums.size()) {
            // 当前结果能够满足要求，更新最终的结果
            if (nums[j] - nums[i] == 1) {
                result = max(result, j - i + 1);
            }

            // 尽可能的拓宽右边界, 注意nums[i]和nums[j]相等的时候也要尽可能的拓宽右边界
            if (nums[j] - nums[i] <= 1) {
                j++;
            } else {
                i++; // 此时nums[j]比nums[i]的差值要大于1，这个时候收缩左边界，注意当i和j相遇的时候下一次if条件一定能够成立而拓展右边界
            }
        }
        return result;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 3, 2, 2, 5, 2, 3, 7};
    auto result = s.findLHS(nums);
    auto result2 = s.findLHS2(nums);
    printf("%d  %d\n", result, result2); // 5 [3,2,2,2,3]

    nums = {1, 2, 3, 4};
    result = s.findLHS(nums);
    result2 = s.findLHS2(nums);
    printf("%d  %d\n", result, result2); // 2 [1,2], [2,3], and [3,4],

    nums = {1, 1, 1, 1};
    result = s.findLHS(nums);
    result2 = s.findLHS2(nums);
    printf("%d  %d\n", result, result2); // 0 没有满足条件的子序列

    nums = {1, 2, 2, 1};
    result = s.findLHS(nums);
    result2 = s.findLHS2(nums);
    printf("%d  %d\n", result, result2); // 0 没有满足条件的子序列
}
