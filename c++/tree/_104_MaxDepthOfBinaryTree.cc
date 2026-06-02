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
     * 求解二叉树的最大深度
     * 解法：简单的解法其实就是递归，然后每次访问到叶子节点的时候更新当前的深度，只保留最终最大的值即可
     * @param root
     * @return
     */
    int maxDepth(TreeNode *root) {
        return maxDepthInternal(root);
    }

    int maxDepthInternal(TreeNode *node) {
        if (node == nullptr) {
            return 0;
        }
        return max(maxDepthInternal(node->left), maxDepthInternal(node->right)) + 1;
    }
};


int main() {
    Solution s;
    std::vector<int> tree = {3, 9, 20,NULL_NUM,NULL_NUM, 15, 7};
    TreeNode *head = initTreeFromTraverseResult(tree);
    auto result = s.maxDepth(head);
    printf("result: %d\n", result); // 3

    tree = {1, NULL_NUM, 2};
    head = initTreeFromTraverseResult(tree);
    result = s.maxDepth(head);
    printf("result: %d\n", result); // 2
}
