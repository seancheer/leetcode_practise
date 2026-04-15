package org.seancheer.tree;

/**
 *Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 * @author: seancheer
 * @date: 2021/8/22
 **/
public class _114FlattenBinaryTreeToLinkedList extends TreeParent {
    public static void main(String[] args) {
        var obj = new _114FlattenBinaryTreeToLinkedList();
        String rootStr = "[1,2,5,3,4,null,6]";
        TreeNode treeRoot = genTreeFromLevelOrder(rootStr);
        obj.flatten(treeRoot);
        System.out.println("res:" + printTreeByRight(treeRoot) ); // [1,null,2,null,3,null,4,null,5,null,6]

        rootStr = "[]";
        treeRoot = genTreeFromLevelOrder(rootStr);
        obj.flatten(treeRoot);
        System.out.println("res:" + printTreeByRight(treeRoot) ); // []

        rootStr = "[0]";
        treeRoot = genTreeFromLevelOrder(rootStr);
        obj.flatten(treeRoot);
        System.out.println("res:" + printTreeByRight(treeRoot) ); // [0]

    }

    /**
     * 解题思路：按照递归的方式处理即可，不需要额外的空间
     * @param root
     */
    public void flatten(TreeNode root) {
        prevNode = null;
        flattenInternal(root);
    }

    private TreeNode prevNode = null;

    /**
     * 题目要求按照前序遍历的方式转换为链表，所以直接按照前序遍历的方式来访问
     * @param node
     */
    private void flattenInternal(TreeNode node){
        if (null != prevNode){
            prevNode.left = null;
            prevNode.right = node;
        }

        if (null == node){
            return;
        }

        //因为后面会把left指针置为null，所以这里必须先提前保存起来
        TreeNode left = node.left;
        TreeNode right = node.right;
        prevNode = node;
        flattenInternal(left);
        flattenInternal(right);
    }


    /**
     * 因为树将会转换为链表，用right连接起来，所以这里按照right的方式把整棵树打印出来
     * @param root
     * @return
     */
    private static String printTreeByRight(TreeNode root){
        if (null == root){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(null != root) {
            sb.append(root.val).append(", ");
            assert (null == root.left);
            root = root.right;
        }

        String res = sb.substring(0, sb.length() - 2);
        return res + "]";
    }
}
