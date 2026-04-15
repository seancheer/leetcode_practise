package org.seancheer.tree;

import org.seancheer.array._1TwoSum;

import java.util.HashSet;
import java.util.Set;

/**
 *Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * Example 3:
 *
 * Input: root = [2,1,3], k = 4
 * Output: true
 * Example 4:
 *
 * Input: root = [2,1,3], k = 1
 * Output: false
 * Example 5:
 *
 * Input: root = [2,1,3], k = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _653TwoSum_4_InputIsABST extends TreeParent{
    public static void main(String[] args) {
        var obj = new _653TwoSum_4_InputIsABST();
        String root = "[5,3,6,2,4,null,7]";
        int k = 9;
        var res = obj.findTarget(genTreeFromLevelOrder(root), k);
        System.out.println("res:" + res); //true

        root = "[5,3,6,2,4,null,7]";
        k = 28;
        res = obj.findTarget(genTreeFromLevelOrder(root), k);
        System.out.println("res:" + res); //false

        root = "[2,1,3]";
        k = 4;
        res = obj.findTarget(genTreeFromLevelOrder(root), k);
        System.out.println("res:" + res); //true

        root = "[2,1,3]";
        k = 1;
        res = obj.findTarget(genTreeFromLevelOrder(root), k);
        System.out.println("res:" + res); //false  必须是两个数，一个数是不可以的

        root = "[2,1,3]";
        k = 3;
        res = obj.findTarget(genTreeFromLevelOrder(root), k);
        System.out.println("res:" + res); //true
    }

    /**
     * 解题思路：
     * 1 类似于{@link _1TwoSum}，不过这个是在遍历二叉树的过程中来构建map的，
     * 2 也可以使用中序遍历将二叉树的结果存储在数组中，然后查找的过程就和{@link org.seancheer.twopointers._167TwoSum_2_InputArrIsSorted}
     * 一样了，这里采用第一种方式
     * 注意，必须是两个数，一个数是不可以的
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        if (null == root){
            return false;
        }
        return findTargetInternal(root, k, new HashSet<>());
    }

    private boolean findTargetInternal(TreeNode root, int k , Set<Integer> set){
        if (null == root){
            return false;
        }

        int val = root.val;
        if (set.contains(k - val)){
            return true;
        }
        set.add(val);
        return findTargetInternal(root.left, k ,set) || findTargetInternal(root.right, k ,set);
    }
}
