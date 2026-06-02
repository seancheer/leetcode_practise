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
     * 解法：非常简答的递归式解法，分别传入左孩子和右孩子，如果完全对称的话，那么左右孩子的值肯定都是一样的，注意，第三层开始是左边节点的左孩子和右边节点的右孩子对比，或者左边节点的右孩子和
     * 右边节点的左孩子对比
     * @param root
     * @return
     */
    bool isSymmetric(TreeNode *root) {
        if (root == nullptr) {
            return true;
        }
        return isSymmetricInternal(root->left, root->right);
    }

    bool isSymmetricInternal(TreeNode *left, TreeNode *right) {
        if (left == nullptr && right == nullptr) {
            return true;
        }

        // 两个节点均存在
        if (left != nullptr && right != nullptr) {
            if (left->val != right->val) {
                return false;
            }
            if (!isSymmetricInternal(left->left, right->right)) {
                // 孩子节点存在不对称的树，直接返回，后续无需再比较
                return false;
            }

            if (!isSymmetricInternal(left->right, right->left)) {
                return false;
            }
            return true;
        }
        // 左右两边的节点有一个不存在
        return false;
    }

    /**
     * 判断一个二叉树是否完全对称
     * 解法：接下来的是迭代式解法，准备两个vector，分别存储左右子树，左子树按照左孩子右孩子的方式进入到vector，右子树按照右孩子左孩子的方式进入到vector，每对比完一层，清空该层，
     * 这样子遍历完成之后，两边的数字应该都是完全一样的，其实本质上就是一个层次遍历
     * @param root
     * @return
     */
    bool isSymmetric2(TreeNode *root) {
        if (root == nullptr) {
            return true;
        }

        std::vector<TreeNode *> left, right;
        left.push_back(root->left);
        right.push_back(root->right);
        while (!left.empty() && !right.empty()) {
            if (left.size() != right.size()) {
                return false;
            }
            std::vector<TreeNode *> tmpLeft = left;
            std::vector<TreeNode *> tmpRight = right;
            left.clear();
            right.clear();

            for (int i = 0; i < tmpLeft.size(); ++i) {
                TreeNode *first = tmpLeft[i];
                TreeNode *second = tmpRight[i];
                if (first == nullptr && second == nullptr) {
                    continue;
                }
                if (first != nullptr && second != nullptr) {
                    if (first->val != second->val) {
                        return false;
                    }
                    left.push_back(first->left);
                    left.push_back(first->right);
                    right.push_back(second->right);
                    right.push_back(second->left);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {1, 2, 2, 3, 4, 4, 3};
    TreeNode *head = initTreeFromTraverseResult(tree);
    auto result = s.isSymmetric(head);
    auto result2 = s.isSymmetric2(head);
    printf("result: %d   reuslt2: %d\n", result, result2); // true

    tree = {1, 2, 2,NULL_NUM, 3,NULL_NUM, 3};
    head = initTreeFromTraverseResult(tree);
    result = s.isSymmetric(head);
    result2 = s.isSymmetric2(head);
    printf("result: %d   reuslt2: %d\n", result, result2); // false
}
