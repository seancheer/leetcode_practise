package org.seancheer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 *
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * 和1差不多，只不过这个需要记录路径而已
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _113PathSum_2 extends TreeParent{
    public static void main(String[] args) {
        var obj = new _113PathSum_2();
        String root = "[5,4,8,11,null,13,4,7,2,null,null,5,1]";
        int targetSum = 22;
        var res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //[[5,4,11,2],[5,8,4,5]]


        root = "[1,2,3]";
        targetSum = 5;
        res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //[]

        root = "[1,2]";
        targetSum = 0;
        res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //[]

        root = "[1,2]";
        targetSum = 1;
        res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //[]

    }

    /**
     * 直接遍历找到答案即可，注意必须leaf节点结尾
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res.clear();
        if (null == root){
            return res;
        }

        pathSumInternal(root, targetSum, 0, new ArrayList<>());
        return res;
    }

    /**
     * 解题思路：回溯的方式遍历查找能满足要求的结果，因为node的值可能为负值，所以没办法剪枝
     *
     * @param root
     * @param targetSum
     * @param cur
     * @param tmpRes
     */
    private void pathSumInternal(TreeNode root, int targetSum, int cur, List<Integer> tmpRes){
        if (null == root){
            return;
        }

        tmpRes.add(root.val);
        cur += root.val;
        if (cur == targetSum && root.left == null && root.right == null){
            //记录结果
            res.add(new ArrayList<>(tmpRes));
            //返回之前清理，为了回溯
            tmpRes.remove(tmpRes.size() - 1);
            return;
        }

        pathSumInternal(root.left, targetSum, cur, tmpRes);
        pathSumInternal(root.right, targetSum, cur, tmpRes);
        tmpRes.remove(tmpRes.size() - 1);

    }

    private List<List<Integer>> res = new ArrayList<>();
}
