//
// Created by ljt on 2026/7/26.
//

#include <iostream>
#include <map>
#include <string>
#include <set>
#include <queue>
#include "tree_utils.h"
#include "utils/utils.h"
using namespace std;

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    /**
     * 判断一个二叉树是否完全对称
     * 解法：
     * @param root
     * @return
     */
    bool isSymmetric(TreeNode *root) {
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {1, 2, 2, 3, 4, 4, 3};
    TreeNode *head = initTreeFromTraverseResult(tree);
    auto result = s.isSymmetric(head);
    printf("result: %d\n", result); // true

    tree = {1, 2, 2,NULL_NUM, 3,NULL_NUM, 3};
    head = initTreeFromTraverseResult(tree);
    result = s.isSymmetric(head);
    printf("result: %d\n", result); // false
}
