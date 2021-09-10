package org.seancheer.tree;

import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.List;

public class _95UniqueBinarySearchTree_2 extends TreeParent {
    public static void main(String[] args) {
        var obj = new _95UniqueBinarySearchTree_2();
        int n = 2;
        var res = obj.generateTrees(n);
        System.out.println("res: " + toStringTreeInOrder(res)); //[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

        n = 1;
        res = obj.generateTrees(n);
        System.out.println("res: " + toStringTreeInOrder(res));  //[1]
    }

    /**
     * 解题思路：递归的方式求解
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        return helper(1, n);
    }

    /**
     * 递归进行求解，注意在left > end的时候要加入null（表示为左孩子或者右孩子为空），不加的话，会导致叶子叶节点因为下面的逻辑无法创建出来：
     *  for (TreeNode leftChild : leftChilds) {
     *                 for (TreeNode rightChild : rightChilds)
     *
     * 因为两个List只要任意一个为空，所以for循环根本不可能进入。
     *
     */
    private List<TreeNode> helper(int left, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (left > end) {
            res.add(null);
            return res;
        }

        if (left == end) {
            TreeNode node = new TreeNode(left);
            res.add(node);
            return res;
        }

        //以i为root节点，分别遍历i两边的可能组合
        for (int i = left; i <= end; ++i) {
            List<TreeNode> leftChilds = helper(left, i - 1);
            List<TreeNode> rightChilds = helper(i + 1, end);


            //每一对交叉组合都是一个新的树，加入null是为了保证至少循环一次，否则叶子节点将无法运行
            for (TreeNode leftChild : leftChilds) {
                for (TreeNode rightChild : rightChilds) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftChild;
                    node.right = rightChild;
                    res.add(node);
                }
            }
        }
        return res;
    }


}
