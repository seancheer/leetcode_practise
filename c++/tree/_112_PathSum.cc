//
// Created by ljt on 2026/8/24.
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
     * 判断二叉树的路径和是否有等于targetSum的
     * 解题思路：深度优先搜索树，查找最终的结果能等于targetSum的值
     * 需要注意的是，这道题有个陷阱，就是必须从root节点加到叶子节点，不能只加一半！！！！且如果某个节点只有一个孩子，那么就只能加该孩子，另外一个孩子不作数
     * @param root
     * @param targetSum
     * @return
     */
    bool hasPathSum(TreeNode *root, int targetSum) {
        if (root == nullptr) {
            return false;
        }
        return hasPathSumInternal(root, 0, targetSum);
    }

    bool hasPathSumInternal(TreeNode *root, int curSum, int targetSum) {
        if (root == nullptr) {
            return false;
        }
        curSum += root->val;
        // 这个判断可以保证遍历一定到了叶子结点
        if (root->left == nullptr && root->right == nullptr) {
            return curSum == targetSum;
        }

        // 如果某个孩子为空的话，那么这条路径一定会返回false
        return hasPathSumInternal(root->left, curSum, targetSum) || hasPathSumInternal(root->right, curSum, targetSum);
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {5, 4, 8, 11,NULL_NUM, 13, 4, 7, 2,NULL_NUM,NULL_NUM,NULL_NUM, 1};
    TreeNode *head = initTreeFromTraverseResult(tree);
    bool res = s.hasPathSum(head, 22);
    printf("result: %d \n", res); // true

    tree = {1, 2, 3};
    head = initTreeFromTraverseResult(tree);
    res = s.hasPathSum(head, 5);
    printf("result: %d \n", res); // false

    tree = {1, 2, 1};
    head = initTreeFromTraverseResult(tree);
    res = s.hasPathSum(head, 1);
    printf("result: %d \n", res); // false

    tree = {1, 2};
    head = initTreeFromTraverseResult(tree);
    res = s.hasPathSum(head, 1);
    printf("result: %d \n", res); // false 如果某个节点只有一个孩子，那么就只能加该孩子，另外一个孩子不作数

    res = s.hasPathSum(nullptr, 0);
    printf("result: %d \n", res); // false
}
