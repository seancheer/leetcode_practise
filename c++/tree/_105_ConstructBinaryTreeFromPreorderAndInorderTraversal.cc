//
// Created by ljt on 2026/9/9.
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
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    /**
     * 从中序遍历和前序遍历中构建二叉树：前序遍历和后序遍历的数组都保证数字一定是唯一不重复的
     * 解题思路：采用递归的思路，首先在前序遍历的第一个位置就是根节点，然后遍历中序遍历数组，假设根节点在中序遍历中的index为i，那么[start,i]就是左子树，
     * [i, end]就是右子树，然后分别对左子树和右子树进行相同逻辑的递归，这样子就完整的构建出来了唯一的二叉树，递归的终止条件就是只剩下一个节点
     * 需要注意的是，这套递归能正确运行的前提是必须从根节点开始，因此对于左右子树，也必须从根节点开始递归
     * 1. 左子树根节点：这个很简单，其实就是本次递归中，前序遍历中root节点的下一个位置
     * 2. 右子树根节点：这个就有点难度了，线索还是在前序遍历中，为了找到右子树的根节点，那么必须找到左子树的长度，左子树的长度可以从中序遍历中获取，假设长度为n，那么右子树根节点在前序遍历中的位置就是root+n+1
     * 我一开始的思路是对的，但是却忘记考虑了【递归必须从根节点开始】这一重要前提，导致写出的代码的有问题
     * @param root
     * @param p
     * @param q
     * @return
     */
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
        if (preorder.empty() || inorder.empty()) {
            return nullptr;
        }

        std::map<int, int> m;
        //加快速度，由于二叉树的每个数字都是不一样的，因此可以快速查找根节点在中序遍历中的index
        for (int i = 0; i < inorder.size(); ++i) {
            m[inorder[i]] = i;
        }
        return buildTreeInternal(preorder, inorder, m, 0, 0, inorder.size() - 1);
    }

    TreeNode *buildTreeInternal(vector<int> &preorder, vector<int> &inorder, std::map<int, int> &m, int rootIdx,
                                int leftIdx,
                                int rightIdx) {
        if (leftIdx > rightIdx) {
            return nullptr;
        }
        int val = preorder[rootIdx];
        int inorderIdx = m[val];
        TreeNode *node = new TreeNode(val);
        // 左子树的根节点肯定是rootIdx+1，中序遍历中的边界是[left,inorderIdx - 1]
        node->left = buildTreeInternal(preorder, inorder, m, rootIdx + 1, leftIdx, inorderIdx - 1);
        // 右子树根节点的位置在rootIdx + (inorderIdx - left) + 1，中序遍历的边界是[inorderIdx + 1, right]
        // 下面的这个写法是考虑了边界情况的，非常优美的解决了各种corner case，当在边界的时候，leftIdx会大于rightIdx，这样子就能按照预期返回nullptr
        node->right = buildTreeInternal(preorder, inorder, m, rootIdx + (inorderIdx - leftIdx) + 1, inorderIdx + 1,
                                        rightIdx);
        return node;
    }
};

int main() {
    Solution s;
    std::vector<int> preorder = {3, 9, 20, 15, 7};
    std::vector<int> inorder = {9, 3, 15, 20, 7};
    auto res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); // [3,9,20,null,null,15,7]


    preorder = {-1};
    inorder = {-1};
    res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); //[-1]

    preorder = {1, 2};
    inorder = {2, 1};
    res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); //[1,2]

    preorder = {1, 2};
    inorder = {1, 2};
    res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); //[1,null,2]

    preorder = {1, 2, 3};
    inorder = {3, 2, 1};
    res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); //[1,2,null,3]


    preorder = {3, 2, 1, 4};
    inorder = {1, 2, 3, 4};
    res = s.buildTree(preorder, inorder);
    printTreeByLevelOrderTranversal(res); //[3,2,4,1]
    return 0;
}
