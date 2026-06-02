//
// Created by ljt on 2026/5/28.
//
#include <iostream>
#include <vector>
#include <map>
using namespace std;

void printInterval(vector<vector<int> > &intervals);

class Solution {
public:
    /**
     * 解题思路：合并区间，该问题其实很简单，首先要对各个interval排序，排完序后再根据区间进行合并
     * 1 如果当前区间的左边界大于上一个区间的右边界，说明区间发生了断层，直接将当前区间加入到结果集中
     * 2 反之的话，则用当前区间的右边界更新到上一个区间的右边界，以达到扩大区间的目的
     * @param intervals
     * @return
     */
    vector<vector<int> > merge(vector<vector<int> > &intervals) {
        if (intervals.empty() || intervals[0].empty()) {
            return vector<vector<int> >{};
        }
        std::sort(intervals.begin(), intervals.end());
        std::vector<std::vector<int> > result;
        for (auto &item: intervals) {
            if (result.empty()) {
                result.emplace_back(item);
                continue;
            }

            // 和上一个判断，如果当前区间index0的位置大于上一个的index1，那么将当前的区间加入到结果集中，如果小于等于上一个的index1，那么，将上一个的index1更新为当前的index1
            auto &last = result[result.size() - 1];
            if (item[0] > last[1]) {
                result.emplace_back(item);
            } else {
                last[1] = max(last[1], item[1]); // 选取较大的右边界，这里是为了处理当前区间完全是上一个区间子集的场景
            }
        }
        return result;
    }
};

int main() {
    Solution s;
    std::vector<vector<int> > intervals{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    auto result = s.merge(intervals); // [[1,6],[8,10],[15,18]]
    printInterval(result);

    intervals = {{1, 4}, {4, 5}};
    result = s.merge(intervals); //  [[1,5]]
    printInterval(result);

    intervals = {{4, 7}, {1, 4}};
    result = s.merge(intervals); // [[1,7]]
    printInterval(result);

    intervals = {
        {2, 3},
        {4, 5},
        {6, 7},
        {8, 9},
        {1, 10}
    };;
    result = s.merge(intervals); // [[1,10]]
    printInterval(result);
    return 0;
}

void printInterval(vector<vector<int> > &intervals) {
    int i = 0, j = 0;
    for (auto &interval: intervals) {
        printf("[");
        j = 0;
        for (auto &val: interval) {
            printf("%d", val);
            if (j < interval.size() - 1) {
                printf(", ");
            }
            j++;
        }
        printf("]");
        if (i < intervals.size() - 1) {
            printf(", ");
        }
        i++;
    }
    printf("\n");
}
