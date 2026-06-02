//
// Created by ljt on 2026/8/26.
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
     * 查找二叉树中任意两个节点之间的公共祖先
     * 解题思路：遍历二叉树，分别记录查找到p和q的路径，找到p和q的路径有且只有一条，找到之后再找从头开始找最后一个相同的父节点即可，这道题的每个节点的值都是不一样的，因此只需要简单的比较下
     * 数字是否相同即可
     * 该解法不是最优的，还需要想一个最优的解决方式
     * 解法2：遍历整颗二叉树，分别记录左子树和右子树是否查到了目标节点p和q，如果两个都查找到了，那么意味着当前节点就是其公共祖先，为了保证只保留最后一个公共祖先，那么在赋值给结果的时候必须注意
     * 如果赋值过了，就不能再赋值了，且要立刻停止递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q) {
        if (root == nullptr) {
            return nullptr;
        }

        // 首先查找目标节点，将遍历过的节点都放在vector中
        std::vector<TreeNode *> path1, path2;
        // 题目承诺一定能找到p和q，因此这里就不检查返回值了
        findTarget(root, p, path1);
        findTarget(root, q, path2);

        // 接下来遍历两条路径，直到找到第一个不相等的节点
        TreeNode *res = nullptr;
        int i = 0;
        while (i < path1.size() && i < path2.size()) {
            if (path1[i] == path2[i]) {
                res = path1[i];
                i++;
                continue;
            }
            break;
        }
        return res;
    }

    bool findTarget(TreeNode *root, TreeNode *target, std::vector<TreeNode *> &pathVec) {
        if (root == nullptr) {
            return false;
        }
        pathVec.push_back(root);
        if (root == target) {
            return true;
        }
        if (findTarget(root->left, target, pathVec)) {
            return true;
        }
        if (findTarget(root->right, target, pathVec)) {
            return true;
        }
        // 当前节点没找到对应的节点，说明不在该子树下，需要将该节点pop出去
        pathVec.pop_back();
        return false;
    }

    /**
     * 查找二叉树中任意两个节点之间的公共祖先
    *  解题思路：解法2：也是递归的解法，该解法更加巧妙，完全不需要依赖记录足迹的方式
    *  1. 如果当前节点为p或者q，或者为null，那么直接返回当前节点，表示找到了目标节点或者到了叶子节点
    *  2. 接下来分别对左右子树进行遍历，如果发现在左子树中和右子树中都找到了目标节点，那么返回当前节点，当前节点即为最终结果；如果只在一边的子树中找到了，但是在另外的子树中没找到，那么
    *  返回找到目标节点的子树，如果说都没找到那么直接返回null即可
    *  该解法比较巧妙，需要认真思考下
     * @param root
     * @param p
     * @param q
     * @return
     */
    TreeNode *lowestCommonAncestor2(TreeNode *root, TreeNode *p, TreeNode *q) {
        if (root == nullptr || root == p || root == q) {
            return root;
        }

        TreeNode *left = lowestCommonAncestor2(root->left, p, q);
        TreeNode *right = lowestCommonAncestor2(root->right, p, q);
        if (left != nullptr && right != nullptr) {
            // 当前节点即为最近公共祖先，之所以递归往上的祖先不会被返回是因为上面祖先的另外一个子树必然找不到目标节点，也就是必然为null，而为null的时候本解法只返回非null的节点，也就巧妙的保证了
            // 下面的这个root能一直被返回到最上层
            // 如果p和q都在同一个子树里，那么一开始的if判断可以保证一定会返回更上层的p或者q，也能达到最近公共祖先的目的
            return root;
        }
        return left != nullptr ? left : right;
    }
};

int main() {
    // 该题就不写本地测试了，直接在线上提交测试，测试地址：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
    return 0;
}

