//
// Created by ljt on 2026/9/13.
//


#include <iostream>
#include <map>
#include <string>
#include <set>
#include <queue>
#include "tree_utils.h"
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
     * 判断一棵树是否为平衡二叉树
     * 解题思路：这个问题很简单，简单的递归就可以了
     * @param root
     * @return
     */
    bool isBalanced(TreeNode *root) {
        bool res = true;
        isBalancedInternal(root, res);
        return res;
    }

    int isBalancedInternal(TreeNode *root, bool &res) {
        if (root == nullptr) {
            return 0;
        }
        if (!res) {
            // 提前停止递归，因为已经发现这棵树不是一颗平衡二叉树了
            return 0;
        }
        int leftDepth = isBalancedInternal(root->left, res);
        int rightDepth = isBalancedInternal(root->right, res);
        if (abs(leftDepth - rightDepth) > 1) {
            res = false;
        }
        return max(leftDepth, rightDepth) + 1;
    }
};


int main() {
    Solution s;
    std::vector<int> tree = {3, 9, 20, NULL_NUM, NULL_NUM, 15, 7};
    TreeNode *node = initTreeFromTraverseResult(tree);
    auto res = s.isBalanced(node);
    printf("res: %d\n", res); // true

    tree = {1, 2, 2, 3, 3,NULL_NUM,NULL_NUM, 4, 4};
    node = initTreeFromTraverseResult(tree);
    res = s.isBalanced(node);
    printf("res: %d\n", res); // false

    res = s.isBalanced(nullptr);
    printf("res: %d\n", res); // true
}
