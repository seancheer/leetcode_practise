package org.seancheer.tree;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * 从前序遍历和中序遍历构造一棵树，根据该题目举的例子，该树不是一颗二叉树，
 * 因为大小关系不满足
 * 前序遍历和中序遍历里面的结果都是唯一的，就是没有重复数字
 *
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _105ConstructBinaryTreeFromPreorderInOrder extends TreeParent {
    public static void main(String[] args) {
        var obj = new _105ConstructBinaryTreeFromPreorderInOrder();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        var res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));


        preorder = new int[]{-1};
        inorder = new int[]{-1};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{1, 2};
        inorder = new int[]{2, 1};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{1, 2};
        inorder = new int[]{1, 2};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{1, 2, 3};
        inorder = new int[]{1, 2, 3};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{1, 2, 3};
        inorder = new int[]{1, 3, 2};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{1, 2, 3};
        inorder = new int[]{3, 2, 1};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));

        preorder = new int[]{3,2,1,4};
        inorder = new int[]{1,2,3,4};
        res = obj.buildTree(preorder, inorder);
        System.out.println("res:" + toStringTreeLevelOrder(res));
    }

    /**
     * 解题思路：
     * 从前序遍历中找到根节点，然后在中序遍历中就可以确定根节点的左子树和右子树，
     * 然后递归处理左子树和右子树即可
     * 代码逻辑可以进一步精简，这里就不处理了
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || preorder.length == 0 || null == inorder || inorder.length == 0) {
            return null;
        }


        return buildTreeInternal(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 递归处理结果
     *
     * @param preorder
     * @param preLeft
     * @param inorder
     * @param inLeft
     * @return
     */
    private TreeNode buildTreeInternal(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        //说明不存在左子树或者右子树，注意是用preLeft作为判断标准，因为preLeft是起点，同时注意游标的合法性
        if (preLeft < 0 || preLeft >= preorder.length || preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int nodeVal = preorder[preLeft];
        TreeNode node = new TreeNode(nodeVal);
        //说明此时只剩一个元素了，因此创建完成之后直接返回即可，也就是已经到叶子节点了
        //注意，我们是从前序遍历来判断这个逻辑
        if (preLeft == preRight) {
            return node;
        }

        //在中序遍历中查找根节点，从而确定左子树的范围
        //中序遍历中左子树的范围为：[inleft, i)
        int i = inLeft;
        for (; i < inRight; ++i) {
            //找到了根节点
            if (inorder[i] == nodeVal) {
                break;
            }
        }

        int len = i - inLeft;
        //这种情况下说明压根儿不存在左子树，直接判断右子树即可
        if (i == inLeft) {
            node.left = null;
            node.right = buildTreeInternal(preorder, preLeft + 1, preRight, inorder, inLeft + 1, inRight);
            return node;
        }

        //接下来确定前序遍历里面左子树的范围，注意，直接使用长度来确定
        // 前序遍历里面左子树的范围为[preLeft + 1, preleft + len]

        //接下来我们分别对左子树和右子树进行递归处理
        node.left = buildTreeInternal(preorder, preLeft + 1, preLeft + len, inorder, inLeft, i - 1);
        //右子树不需要进行查找，因为直接是向数组末尾靠近的
        node.right = buildTreeInternal(preorder, preLeft + len + 1, preRight, inorder, i + 1, inRight);
        return node;
    }
}
