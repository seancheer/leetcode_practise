//
// Created by ljt on 2026/7/24.
//

#ifndef HELLOWORLDPROJECT_TREE_UTILS_H
#define HELLOWORLDPROJECT_TREE_UTILS_H
#include <vector>

#define NULL_NUM INT32_MAX

// TreeNode 二叉树的定义
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {
    }

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {
    }

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {
    }
};


// initTreeFromTraverseResult 从二叉树的层次遍历结果中构造一个二叉树出来
TreeNode *initTreeFromTraverseResult(std::vector<int> &val) {
    if (val.empty()) {
        return nullptr;
    }
    std::vector<TreeNode *> nodes;
    nodes.push_back(new TreeNode(val[0]));
    int nodeIdx = 0;
    // 接下来开始构造节点之间的父子关系
    for (int i = 1; i < val.size(); ++i) {
        TreeNode *curNode = nullptr;
        while (curNode == nullptr && nodeIdx < nodes.size()) {
            curNode = nodes[nodeIdx++];
        }
        if (curNode == nullptr) {
            break;
        }

        if (val[i] == NULL_NUM) {
            curNode->left = nullptr;
        } else {
            curNode->left = new TreeNode(val[i]);
            nodes.push_back(curNode->left);
        }
        i++;
        if (i >= val.size()) {
            break;
        }
        if (val[i] == NULL_NUM) {
            curNode->right = nullptr;
        } else {
            curNode->right = new TreeNode(val[i]);
            nodes.push_back(curNode->right);
        }
    }
    return nodes[0];
}
#endif //HELLOWORLDPROJECT_TREE_UTILS_H
