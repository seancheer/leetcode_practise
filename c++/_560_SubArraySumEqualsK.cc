//
// Created by ljt on 2026/5/27.
//
#include <iostream>
#include <vector>
#include <map>
using namespace std;

class Solution {
public:
    /**
     * 解题思路：假设有两个位置i和j，prefix_sum为前缀和的数组，其中j > i，那么子串的subarr[i,j]的和为prefix_sum[j] - prefix_sum[i - 1], 那么如果要求出子数组和等于k的子数组数量，这道问题就变成了如下的形式
     * k = prefix_sum[j] - prefix_sum[i - 1] => prefix_sum[j] = k + prefix_sum[i - 1], 那么只需要prefix_sum[i - 1] = prefix_sum[j] - k，这个式子就可以成立，这个就很清晰了，
     * 我们维护一个map，该map的key value分别为 prefix_sum -> count，当我们遍历到了j的位置的时候，只需要检查一下prefix_sum[j] - k的和的个数，相当于就算出了子数组和等于k的个数
     * 该题巧妙的将每次根据前缀和计算子数组和的O(n^2)算法转换成了O(1)的算法
     * @param nums
     * @param k
     * @return
     */
    int subarraySum(vector<int> &nums, int k) {
        if (nums.empty()) {
            return 0;
        }

        std::map<int, int> m;
        m[0] = 1; // 这里的初始化是为了处理边界情况，即[0,j]的前缀刚好等于k
        int sum = 0;
        int result = 0;
        for (auto &val: nums) {
            sum += val;
            result += m[sum - k];
            m[sum]++;
        }
        return result;
    }
};

int main() {
    Solution s;
    std::vector<int> nums{1, 1, 1};
    int k = 2;
    auto result = s.subarraySum(nums, k); // 2
    printf("%d\n", result);

    nums = {1, 2, 3};
    k = 3;
    result = s.subarraySum(nums, k); // 2
    printf("%d\n", result);

    nums = {1, 2, 3, 4, -4, 0};
    k = 3;
    result = s.subarraySum(nums, k); // 4
    printf("%d\n", result);
    return 0;
}
