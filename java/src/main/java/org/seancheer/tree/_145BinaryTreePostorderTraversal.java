package org.seancheer.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 * Example 4:
 *
 *
 * Input: root = [1,2]
 * Output: [2,1]
 * Example 5:
 *
 *
 * Input: root = [1,null,2]
 * Output: [2,1]
 *
 *
 * Constraints:
 *
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * 系列题目：{@link _144BinaryTreePreorderTraversal}  {@link _94BinaryTreeInorderTraversal}
 * @author: seancheer
 * @date: 2021/8/23
 **/
public class _145BinaryTreePostorderTraversal extends TreeParent{

    public static void main(String[] args) {
        var obj = new _145BinaryTreePostorderTraversal();
        String tree = "[1,null,2,3]";
        TreeNode root = genTreeFromLevelOrder(tree);
        var res = obj.postorderTraversal(root);
        var res2 = obj.postorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2); //[3,2,1]

        tree = "[1,null,2]";
        root = genTreeFromLevelOrder(tree);
        res = obj.postorderTraversal(root);
        res2 = obj.postorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[2,1]

        tree = "[1]";
        root = genTreeFromLevelOrder(tree);
        res = obj.postorderTraversal(root);
        res2 = obj.postorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[1]

        tree = "[-10,9,20,null,null,15,7]";
        root = genTreeFromLevelOrder(tree);
        res = obj.postorderTraversal(root);
        res2 = obj.postorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[9, 15, 7, 20, -10]

        tree = "[1,4,3,2]";
        root = genTreeFromLevelOrder(tree);
        res = obj.postorderTraversal(root);
        res2 = obj.postorderTraversal2(root);
        System.out.println("res:" + res + "   res2:" + res2);  //[2, 4, 3, 1]
    }

    /**
     * 后续遍历递归版本
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (null == root){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        postorderTraversalInternal(root, res);
        return res;
    }

    private void postorderTraversalInternal(TreeNode root, List<Integer> res){
        if (null == root){
            return;
        }

        postorderTraversalInternal(root.left, res);
        postorderTraversalInternal(root.right, res);
        res.add(root.val);
    }

    /**
     * 后续遍历迭代版本
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (null == root){
            return new ArrayList<>();
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while(null != root){
            stack.push(root);
            root = root.left;
        }

        List<Integer> res = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (null != cur.right) {
                //放入一个假的，是为了避免对原节点的修改，当下次到这个节点的时候，其左右孩子都为null了，避免重复计算
                TreeNode dummyNode = new TreeNode(cur.val);
                stack.push(dummyNode);

                //继续找到右孩子的最左边
                cur = cur.right;
                while(null != cur){
                    stack.push(cur);
                    cur = cur.left;
                }
            }else{
                //因为左边孩子都已经打印完了，如果没有右边孩子，说明已经到头了
                res.add(cur.val);
            }
        }
        return res;
    }
}
