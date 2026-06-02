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
     * 求解二叉树的直径，二叉树的直径被定义为树中任意两个节点之间的最大路径，这条路径可能经过root也可能不会经过root，注意，计算最大路径的时候只计算变长
     * 解法：其实本质上就是求解任意一个节点的左右孩子高度和，遍历二叉树过程中的最大的高度和即为最终的答案
     * @param root
     * @return
     */
    int diameterOfBinaryTree(TreeNode *root) {
        int result = 0;
        diameterOfBinaryTreeInternal(root, result);
        return result;
    }

    // diameterOfBinaryTreeInternal 地轨的计算节点左右孩子的高度和
    int diameterOfBinaryTreeInternal(TreeNode *root, int &result) {
        if (root == nullptr) {
            return 0;
        }

        int leftHeight = diameterOfBinaryTreeInternal(root->left, result);
        int rightHeight = diameterOfBinaryTreeInternal(root->right, result);
        result = max(result, leftHeight + rightHeight);
        // 当前节点的height为左子树和右子树height的较大者
        return max(leftHeight, rightHeight) + 1;
    }
};


int main() {
    Solution s;
    std::vector<int> tree = {1, 2, 3, 4, 5};
    TreeNode *head = initTreeFromTraverseResult(tree);
    auto result = s.diameterOfBinaryTree(head);
    printf("result: %d\n", result); // 3 is the length of the path [4,2,1,3] or [5,2,1,3].

    tree = {1, 2};
    head = initTreeFromTraverseResult(tree);
    result = s.diameterOfBinaryTree(head);
    printf("result: %d\n", result); // 1

    tree = {1, 2, 3, 4, 5,NULL_NUM, 8,NULL_NUM,NULL_NUM, 6, 7, 9};
    head = initTreeFromTraverseResult(tree);
    result = s.diameterOfBinaryTree(head);
    printf("result: %d\n", result); // 6
}
