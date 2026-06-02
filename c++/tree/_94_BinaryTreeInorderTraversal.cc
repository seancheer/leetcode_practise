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
     * 二叉树的中序遍历（左中右）
     * 解法：这道题也很简单，使用递归的方式很轻易就能得到答案
     * 解法2：采用栈+遍历的方式实现
     * @param root
     * @return
     */
    vector<int> inorderTraversal(TreeNode *root) {
        std::vector<int> result;
        this->inorderTraversalInternal(root, result);
        return result;
    }

    void inorderTraversalInternal(TreeNode *root, std::vector<int> &result) {
        if (nullptr == root) {
            return;
        }

        inorderTraversalInternal(root->left, result);
        result.push_back(root->val);
        inorderTraversalInternal(root->right, result);
    }

    /**
     * 解法2：采用栈的方式来实现, 过程如下
     * 1. 首先遍历节点的左孩子，依次将节点加入到栈中，直到没有左孩子为止
     * 2. 将栈顶的节点弹出并加入结果集中
     * 3. 如果当前节点坐在右孩子，则将右孩子加入到栈中，回到第1步
     * 4. 一直循环，直到栈为空为止
     * @param root
     * @return
     */
    vector<int> inorderTraversal2(TreeNode *root) {
        if (root == nullptr) {
            return {};
        }

        std::stack<TreeNode *> st;
        std::vector<int> result;
        TreeNode *cur = root;
        // 将左孩子依次加到stack中
        while (cur != nullptr) {
            st.push(cur);
            cur = cur->left;
        }
        while (!st.empty()) {
            TreeNode *node = st.top();
            st.pop();
            result.push_back(node->val);
            // 接下来将右子树的最左节点加入到stack中
            node = node->right;
            while (node != nullptr) {
                st.push(node);
                node = node->left;
            }
        }
        return result;
    }
};


int main() {
    Solution s;
    std::vector<int> tree = {1, NULL_NUM, 2, 3};
    TreeNode *head = initTreeFromTraverseResult(tree);
    auto result = s.inorderTraversal(head);
    auto result2 = s.inorderTraversal2(head);
    printVector({result, result2}); // [1,3,2]


    tree = {1, 2, 3, 4, 5,NULL_NUM, 8,NULL_NUM,NULL_NUM, 6, 7, 9};
    head = initTreeFromTraverseResult(tree);
    result = s.inorderTraversal(head);
    result2 = s.inorderTraversal2(head);
    printVector({result, result2}); // [4,2,6,5,7,1,3,9,8]

    tree = {1};
    head = initTreeFromTraverseResult(tree);
    result = s.inorderTraversal(head);
    result2 = s.inorderTraversal2(head);
    printVector({result, result2}); // [1]
}
