package org.seancheer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 * <p>
 * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 *
 * @author: seancheer
 * @date: 2021/8/18
 **/
public class _99RecoverBinarySearchTree extends TreeParent {
    public static void main(String[] args) throws Exception {
        var obj = new _99RecoverBinarySearchTree();
        TreeNode root = new TreeNode(1);
        TreeNode child = new TreeNode(3);
        root.left = child;
        child.right = new TreeNode(2);

        TreeNode root2 = (TreeNode) root.clone();
        obj.recoverTree(root);
        obj.recoverTree2(root2);
        System.out.println("res:" + toStringTreeLevelOrder(root) + "   res2:" + toStringTreeLevelOrder(root2));


        root = new TreeNode(3);
        child = new TreeNode(1);
        root.left = child;
        TreeNode rightChild = new TreeNode(4);
        root.right = rightChild;
        rightChild.left = new TreeNode(2);
        root2 = (TreeNode) root.clone();
        obj.recoverTree(root);
        obj.recoverTree2(root2);
        System.out.println("res:" + toStringTreeLevelOrder(root) + "   res2:" + toStringTreeLevelOrder(root2));
    }

    /**
     * 解题思路：需要O(n)的空间复杂度，我们按照中序遍历的方式将整棵树的结果放入到数组中，
     * 因为是中序遍历，所以结果一定是递增的，所以问题就变成了查找非递增的段，找到之后直接交换即可
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (null == root) {
            return;
        }

        List<TreeNode> allNodes = new ArrayList<>();
        tranverseInOrder(root, allNodes);
        //接下来直接查找非递增段
        int firstPos = -1, secondPos = -1;
        for (int i = 1; i < allNodes.size(); ++i) {
            if (firstPos == -1 && allNodes.get(i).val <= allNodes.get(i - 1).val) {
                //注意是i- 1
                firstPos = i - 1;
                continue;
            }

            if (secondPos == -1 && allNodes.get(i).val <= allNodes.get(i - 1).val) {
                //注意是i
                secondPos = i;
                break;
            }
        }

        //注意，可能存在这种特殊情况，也就是中序遍历非递增顺序正好是相邻的，比如[1,3,2,4]，这个时候就需要交换3和2
        if (secondPos == -1) {
            secondPos = firstPos + 1;
        }
        //交换之后，就满足要求了
        int tmp = allNodes.get(firstPos).val;
        allNodes.get(firstPos).val = allNodes.get(secondPos).val;
        allNodes.get(secondPos).val = tmp;
    }

    private void tranverseInOrder(TreeNode node, List<TreeNode> allNodes) {
        if (null == node) {
            return;
        }

        tranverseInOrder(node.left, allNodes);
        allNodes.add(node);
        tranverseInOrder(node.right, allNodes);
    }


    //对应第一个firstPos
    private TreeNode firstNode;
    //对应第二个secondPos
    private TreeNode secondNode;
    //记录中序遍历的时候，前一个节点，默认最小的值
    private TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);

    /**
     * 解题思路：根据解法1的思路，为了满足题目只使用O(1)空间复杂度的要求，这里直接在中序遍历的时候就记录要修改的node
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        if (null == root) {
            return;
        }
        firstNode = secondNode = null;
        recoverTree2Internal(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        prevNode = new TreeNode(Integer.MIN_VALUE);
        secondNode.val = tmp;
    }

    private void recoverTree2Internal(TreeNode node){
        if (null == node){
            return;
        }

        recoverTree2Internal(node.left);
        //当前节点，这里不能等于，因为用例里面真的有Integer.MIN_VALUE这样的用例
        if (null == firstNode && prevNode.val > node.val){
            //我们找到了第一个不合规的节点
            firstNode = prevNode;
        }

        //注意，虽然我们找到了第二个不合规的节点，但是不能直接return，下面这个逻辑可以处理类似于[1,3,2,4]，如果直接return的话，
        //[1,3,2,4,6,5]的情况就处理不了了
        if(null != firstNode && prevNode.val > node.val){
            //第二个不合规的节点，记录下来
            secondNode = node;
        }

        prevNode = node;
        recoverTree2Internal(node.right);
    }

}
