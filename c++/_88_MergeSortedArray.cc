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
     * 解题思路：合并有序数组，只不过这个是合并到nums1上，而不是开辟一个新的数组
     * 这道题有原地修改nums1的解法，必须好好想想，开辟一个新的数组的方法大家都会
     * 解法2：无需开辟新的空间，但是如果按照归并排序的方式把排好序的数字正向放在nums1里，肯定会把nums1里面还没有排序的数字给覆盖了，但是如果这个时候我们反向排序呢？
     * 想想，nums1大于m位置的数字都是0，我们反向排序，那么数字就会优先放在这些0的位置，而nums1的有效数字完全不会被覆盖，天才的想法！！！！
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    void merge(vector<int> &nums1, int m, vector<int> &nums2, int n) {
        int start = m;
        int i = 0;
        int j = 0;
        std::vector<int> result;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result.emplace_back(nums1[i]);
                ++i;
            } else {
                result.emplace_back(nums2[j]);
                ++j;
            }
        }

        while (i < m) {
            result.emplace_back(nums1[i]);
            ++i;
        }
        while (j < n) {
            result.emplace_back(nums2[j]);
            ++j;
        }
        // 接下来将结果赋值给nums1
        nums1 = result;
    }

    /**
    * 解题思路：该解法比较天才，无需开辟新的空间，正向排好序将数字放在nums1的位置会覆盖nums1中未排序的数字，但是如果反向排序，则不会有该问题
    * @param nums1
    * @param m
    * @param nums2
    * @param n
    */
    void merge2(vector<int> &nums1, int m, vector<int> &nums2, int n) {
        int start = m;
        int i = m - 1;
        int j = n - 1;
        int cur = nums1.size() - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[cur--] = nums1[i--];
            } else {
                nums1[cur--] = nums2[j--];
            }
        }

        while (i >= 0) {
            nums1[cur--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[cur--] = nums2[j--];
        }
    }


    void print_vector(std::initializer_list<std::vector<int> > results...) {
        int idx = 0;
        for (auto &result: results) {
            for (int i = 0; i < result.size(); ++i) {
                printf("%d", result[i]);
                if (i < result.size() - 1) {
                    printf("\t");
                }
            }

            if (idx < results.size() - 1) {
                printf(" ----------- ");
            }
            ++idx;
        }
        printf("\n");
    }
};

int main() {
    Solution s;

    std::vector<int> nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    std::vector<int> nums2 = {2, 5, 6};
    int n = 3;
    s.merge(nums1, m, nums2, n);
    std::vector<int> result = nums1;
    nums1 = {1, 2, 3, 0, 0, 0};
    nums2 = {2, 5, 6};
    s.merge2(nums1, m, nums2, n);
    s.print_vector({result, nums1}); // 1,2,2,3,5,6

    nums1 = {0};
    m = 0;
    nums2 = {1};
    n = 1;
    s.merge(nums1, m, nums2, n);
    result = nums1;
    nums1 = {0};
    nums2 = {1};
    s.merge2(nums1, m, nums2, n);
    s.print_vector({result, nums1}); // 1
}

