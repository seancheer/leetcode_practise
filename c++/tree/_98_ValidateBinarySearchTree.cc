//
// Created by ljt on 2026/8/25.
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
     * 遍历一棵树是否为合法的二叉树，二叉树的合法定义是左子树一定比当前节点小，右子树一定比当前节点大
     * 解题思路：要判断一颗二叉树是否为合法的二叉树，其实需要考虑如下因素：
     * 对于左子树，那么左孩子的值必须比父亲小即可，对于右孩子，其值必须落在祖父和父亲之间，对于右子树，那么右孩子的值必须比父亲大即可，但是对于左孩子，
     * 其值也必须落在父亲和祖父之间，递归判断这个逻辑即可，但是有个约束给忘记了，以左子树为例，左子树的左孩子会一直小下去，但是也要注意，其值再小不能上一个作为右孩子的祖先（不然的话这个节点就应该
     * 挂在上一个作为右孩子的祖先的左子树上！！！！！）这个很重要
     * 综上，解法如下：无需中序遍历，遍历整棵二叉树，当往左孩子走的时候，那么意味着当前节点的值就是左子树的最大值，如果往右孩子走的话，那么当前值就是右子树的最小值，这样子就能实现上面的区间判断了
     *
     * 解法2：对该二叉树进行中序遍历，只要能够保证遍历出来的数据是递增的，那就可以确定一定是一个合法的二叉树
     * @param root
     * @return
     */
    bool isValidBST(TreeNode *root) {
        // 这里有个取巧的点，就是二叉树节点的数值范围是int32，这里使用int64是为了处理最左节点和最右节点（这两个节点可能完全没有下界和上界）
        return isValidBSTInternal(root, INT64_MIN, INT64_MAX);
    }

    bool isValidBSTInternal(TreeNode *root, int64_t min, int64_t max) {
        if (root == nullptr) {
            return true;
        }

        int64_t curVal = root->val;
        if (curVal <= min || curVal >= max) {
            return false;
        }
        // 当前节点就是左子树的最大值，右子树的最小值，违反这个约定就意味着不满足二叉搜索树
        return isValidBSTInternal(root->left, min, curVal) && isValidBSTInternal(root->right, curVal, max);
    }

    /**
     * 解法2，中序遍历的方式，完全可以一边遍历二叉树一边比较结果，而不是先把结果存起来再进行比较
     * @param root
     * @return
     */
    bool isValidBST2(TreeNode *root) {
        if (root == nullptr) {
            return true;
        }
        bool result = true;
        std::vector<int> lastVal;
        isValidBST2Internal(root, &lastVal, result);
        return result;
    }


    void isValidBST2Internal(TreeNode *root, std::vector<int> *lastVal, bool &result) {
        if (!result) {
            return;
        }
        if (root == nullptr) {
            return;
        }
        isValidBST2Internal(root->left, lastVal, result);
        // 此时可能是第一个节点，因此这里必须判断一下vector是否为空
        if (!lastVal->empty() && (*lastVal)[0] >= root->val) {
            result = false;
            return;
        }
        // 将上一次的值更新为当前节点的值
        if (lastVal->empty()) {
            lastVal->push_back(root->val);
        } else {
            (*lastVal)[0] = root->val;
        }
        isValidBST2Internal(root->right, lastVal, result);
    }
};

int main() {
    Solution s;
    std::vector<int> tree = {2, 1, 3};
    TreeNode *head = initTreeFromTraverseResult(tree);
    bool res = s.isValidBST(head);
    bool res2 = s.isValidBST2(head);
    printf("result: %d  res2: %d\n", res, res2); // true

    tree = {5, 1, 4,NULL_NUM,NULL_NUM, 3, 6};
    head = initTreeFromTraverseResult(tree);
    res = s.isValidBST(head);
    res2 = s.isValidBST2(head);
    printf("result: %d  res2: %d\n", res, res2); // false

    tree = {45, 42,NULL_NUM,NULL_NUM, 44, 43,NULL_NUM, 41};
    head = initTreeFromTraverseResult(tree);
    res = s.isValidBST(head);
    res2 = s.isValidBST2(head);
    printf("result: %d  res2: %d\n", res, res2); // false

    tree = {5, 4, 6,NULL_NUM,NULL_NUM, 3, 7};
    head = initTreeFromTraverseResult(tree);
    res = s.isValidBST(head);
    res2 = s.isValidBST2(head);
    printf("result: %d  res2: %d\n", res, res2); // false

    tree = {
        989, 982,NULL_NUM, 972,NULL_NUM, 947,NULL_NUM, 920,NULL_NUM, 903,NULL_NUM, 894,NULL_NUM, 881,NULL_NUM, 866,
        NULL_NUM, 864,NULL_NUM, 842,NULL_NUM, 841,NULL_NUM, 796,NULL_NUM, 726,NULL_NUM, 647,NULL_NUM, 613, 719, 593,
        NULL_NUM,NULL_NUM,NULL_NUM, 590,NULL_NUM, 558,NULL_NUM, 554,NULL_NUM, 538,NULL_NUM, 512,NULL_NUM, 504,
        NULL_NUM,
        468, 505, 467,NULL_NUM,NULL_NUM,NULL_NUM, 456,NULL_NUM, 413,NULL_NUM, 331,NULL_NUM, 330, 407, 320,NULL_NUM,
        NULL_NUM,NULL_NUM, 312,NULL_NUM, 306,NULL_NUM, 301,NULL_NUM, 274,NULL_NUM, 251,NULL_NUM, 235,NULL_NUM, 231,
        NULL_NUM, 222,NULL_NUM, 181,NULL_NUM, 93,NULL_NUM, 83,NULL_NUM, 73,NULL_NUM, 64,NULL_NUM, 62,NULL_NUM, 60,
        NULL_NUM, 28,NULL_NUM, 21,NULL_NUM, 20,NULL_NUM, -32,NULL_NUM, -52,NULL_NUM, -70,NULL_NUM, -87,NULL_NUM,
        -98,
        NULL_NUM, -102,NULL_NUM, -115,NULL_NUM, -116,NULL_NUM, -139,NULL_NUM, -183,NULL_NUM, -224,NULL_NUM, -241,
        NULL_NUM, -263,NULL_NUM, -284,NULL_NUM, -294,NULL_NUM, -296,NULL_NUM, -320,NULL_NUM, -330,NULL_NUM, -392,
        NULL_NUM, -398,NULL_NUM, -407,NULL_NUM, -431,NULL_NUM, -445,NULL_NUM, -460,NULL_NUM, -463,NULL_NUM, -492,
        NULL_NUM, -507,NULL_NUM, -518,NULL_NUM, -539,NULL_NUM, -552,NULL_NUM, -558,NULL_NUM, -559,NULL_NUM, -587,
        NULL_NUM, -673,NULL_NUM, -736,NULL_NUM, -757,NULL_NUM, -766,NULL_NUM, -767,NULL_NUM, -823,NULL_NUM, -830,
        NULL_NUM, -867,NULL_NUM, -875,NULL_NUM, -891,NULL_NUM, -905,NULL_NUM, -910,NULL_NUM, -924,NULL_NUM, -960,
        NULL_NUM, -985,NULL_NUM, -988
    };
    head = initTreeFromTraverseResult(tree);
    res = s.isValidBST(head);
    res2 = s.isValidBST2(head);
    printf("result: %d  res2: %d\n", res, res2); // true
}
