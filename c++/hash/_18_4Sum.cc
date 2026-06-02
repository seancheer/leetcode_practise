//
// Created by ljt on 2026/6/10.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
#include "utils/utils.h"
using namespace std;

class Solution {
public:
    /**
     * 解题思路：返回一个数组所有和等于target的所有可能组合
     * 解法1：相当于也是暴力解法，首先用一个map记录所有两数相加的和，其中value为所有可能的index组合，然后再通过遍历map的方式查找两个map中key相加等于target的结果，最后通过一个set进行去重；
     * 注意，在c++里面，std::vector<int>是可以作为容器的key值的
     * 该解法会超时，因此必须想一些其他的加速解法
     * @param nums
     * @param target
     * @return
     */
    vector<vector<int> > fourSum(vector<int> &nums, int target) {
        if (nums.size() < 4) {
            return {};
        }

        std::vector<std::vector<int> > result;
        std::map<int, std::vector<std::vector<int> > > sumMap;
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                int sum = nums[i] + nums[j];
                sumMap[sum].push_back({i, j});
            }
        }

        // 接下来开始计算结果并做去重处理, 去重使用set的方式进行去重
        std::set<std::vector<int> > s;
        for (auto &sum: sumMap) {
            auto another = sumMap.find(target - sum.first);
            if (another != sumMap.end()) {
                for (auto &first: sum.second) {
                    for (auto &second: another->second) {
                        // 当前存储了重复的坐标，跳过
                        if (first[0] == second[0] || first[1] == second[0] || first[0] == second[1] || first[1] ==
                            second[1]) {
                            continue;
                        }
                        // 接下来的就是正确的结果
                        std::vector<int> ans = {nums[first[0]], nums[first[1]], nums[second[0]], nums[second[1]]};
                        std::sort(ans.begin(), ans.end());
                        if (s.count(ans) == 0) {
                            result.emplace_back(ans);
                            s.insert(ans);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解法2：排序+双指针法
     * 首先可以确定的是该题的O(n^2)时间复杂度肯定是无法避免的，先对数组进行排序，维护两个指针i和j，其中j=i+1, 这个时候sum = nums[i]+nums[j], 然后在从k=j+1的位置开始寻找结果等于target - sum的组合，
     * 由于我们事先对数组进行过排序，所以如果发现当前的和比target - sum大的话，那么移动右指针，反之的话则移动左指针, 使用std::set去重即可
     * 该解法的时间复杂度为O(n^3)，已经是最优的了
     * 注意！！！！该题还考察了数组少于4和int溢出的场景，很坑爹！！！！
     * @param nums
     * @param target
     * @return
     */
    vector<vector<int> > fourSum2(vector<int> &nums, int target) {
        if (nums.size() < 4) {
            return {};
        }

        std::sort(nums.begin(), nums.end());
        std::vector<std::vector<int> > result;
        std::set<std::vector<int> > m;
        std::map<int, std::vector<std::vector<int> > > sumMap;
        for (int i = 0; i < nums.size() - 3; i++) {
            for (int j = i + 1; j < nums.size() - 2; j++) {
                long sum = nums[i] + nums[j];
                long sum2 = target - sum;
                // 接下来开始二分查找答案，注意该问题没要求去重，只要index不重复即可
                int start = j + 1;
                int end = nums.size() - 1;
                // 短路求值，已经无法找到结果了
                while (start < end) {
                    if (nums[start] + nums[end] == sum2) {
                        // 答案已经是排好序的了，无需二次排序
                        std::vector<int> item{nums[i], nums[j], nums[start], nums[end]};
                        if (m.count(item) == 0) {
                            // 去重处理
                            result.push_back(item);
                            m.insert(item);
                        }
                        start++;
                        end--;
                    } else if (nums[start] + nums[end] > sum2) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }
};


int main() {
    Solution s;
    std::vector<int> nums = {1, 0, -1, 0, -2, 2};
    int target = 0;
    auto result = s.fourSum(nums, target);
    auto result2 = s.fourSum2(nums, target);
    printDoubleVector(result); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    printDoubleVector(result2); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    nums = {2, 2, 2, 2, 2};
    target = 8;
    result = s.fourSum(nums, target);
    result2 = s.fourSum2(nums, target);
    printDoubleVector(result); // [[-4,0,1,2],[-1,-1,0,1]]
    printDoubleVector(result2); // [[-4,0,1,2],[-1,-1,0,1]]

    nums = {-1, 0, 1, 2, -1, -4};
    target = -1;
    result = s.fourSum(nums, target);
    result2 = s.fourSum2(nums, target);
    printDoubleVector(result); // [[2,2,2,2]]
    printDoubleVector(result2); // [[2,2,2,2]]

    nums = {1000000000, 1000000000, 1000000000, 1000000000}; // 测试int溢出的场景
    target = -294967296;
    result = s.fourSum(nums, target);
    result2 = s.fourSum2(nums, target);
    printDoubleVector(result); //
    printDoubleVector(result2); //
}

