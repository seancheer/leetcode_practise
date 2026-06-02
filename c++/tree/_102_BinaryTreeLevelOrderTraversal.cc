//
// Created by ljt on 2026/7/24.
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
     * 二叉树的层次遍历
     * 解法：解法很简单，使用一个队列，每次一边把本层的结果放在vector中，一边将队列最前面的节点的左右孩子分别放在result中
     * @param root
     * @return
     */
    vector<vector<int> > levelOrder(TreeNode *root) {
        if (nullptr == root) {
            return std::vector<vector<int> >();
        }

        std::vector<std::vector<int> > result;
        std::queue<TreeNode *> q;
        q.push(root);
        while (!q.empty()) {
            int sz = q.size();
            int i = 0;
            std::vector<int> currentLevel; // 本层的结果
            while (i++ < sz) {
                TreeNode *node = q.front();
                q.pop();
                currentLevel.push_back(node->val);
                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }
            }
            result.push_back(currentLevel);
        }
        return result;
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {3, 9, 20, NULL_NUM,NULL_NUM, 15, 7};
    auto result = s.levelOrder(initTreeFromTraverseResult(tree));
    printDoubleVector(result);

    tree = {1};
    result = s.levelOrder(initTreeFromTraverseResult(tree));
    printDoubleVector(result);

    tree = {};
    result = s.levelOrder(initTreeFromTraverseResult(tree));
    printDoubleVector(result);
}
