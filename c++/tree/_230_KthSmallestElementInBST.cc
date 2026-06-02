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
     * 查找二叉树中第K小的值（注意，这个第K小是从1开始index的，不是0，这个要注意），还需要注意的是这是一个标准的二叉搜索树，也就是说，该树是严格遵循左孩子小右孩子大的
     * 该题还给了一个发散性的思维，如果说此时二叉树会频繁的修改（增加和删除），这个时候应该怎么优化呢？
     * 解题思路：题本身其实并不难，只需要简单的进行左中右的中序遍历就能拿到最终的结果
     * @param root
     * @param k
     * @return
     */
    int kthSmallest(TreeNode *root, int k) {
        if (nullptr == root) {
            return -1;
        }
        int result = 0;
        int count = 0;
        kthSmallestInternal(root, result, count, k);
        return result;
    }

    void kthSmallestInternal(TreeNode *root, int &result, int &count, int k) {
        if (root == nullptr) {
            return;
        }
        kthSmallestInternal(root->left, result, count, k);
        ++count;
        if (count == k) {
            result = root->val;
            return;
        }
        kthSmallestInternal(root->right, result, count, k);
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {3, 1, 4, NULL_NUM, 2};
    TreeNode *node = initTreeFromTraverseResult(tree);
    auto res = s.kthSmallest(node, 1);
    printf("res: %d\n", res); // 1

    tree = {5, 3, 6, 2, 4, NULL_NUM, NULL_NUM, 1};
    node = initTreeFromTraverseResult(tree);
    res = s.kthSmallest(node, 3);
    printf("res: %d\n", res); // 3
}
