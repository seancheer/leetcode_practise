package org.seancheer.tree;

/**
 *Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Example 3:
 *
 * Input: root = [1,2], targetSum = 0
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _112PathSum extends TreeParent {
    public static void main(String[] args) {
        var obj = new _112PathSum();
        String root = "[5,4,8,11,null,13,4,7,2,null,null,null,1]";
        int targetSum = 22;
        var res = obj.hasPathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //true


        root = "[1,2,3]";
        targetSum = 5;
        res = obj.hasPathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //false

        root = "[1,2]";
        targetSum = 0;
        res = obj.hasPathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //false

        root = "[1,2]";
        targetSum = 1;
        res = obj.hasPathSum(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res); //false

    }

    /**
     * 直接遍历找到答案即可，easy级别的题目
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root){
            return false;
        }

        return hasPathSumInternal(root, targetSum, 0);
    }

    /**
     * 递归遍历判断即可，注意必须到leaf节点，所以必须判断对应的节点是不是leaf节点
     * @param root
     * @param targetSum
     * @param cur
     * @return
     */
    private boolean hasPathSumInternal(TreeNode root, int targetSum, int cur){
        if(null == root){
            return false;
        }

        cur += root.val;
        if(null == root.left && null == root.right && cur == targetSum){
            return true;
        }

        return hasPathSumInternal(root.left, targetSum, cur) || hasPathSumInternal(root.right, targetSum, cur);
    }
}
