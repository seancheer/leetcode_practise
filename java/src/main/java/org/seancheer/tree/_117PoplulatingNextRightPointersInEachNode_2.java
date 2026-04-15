package org.seancheer.tree;

public class _117PoplulatingNextRightPointersInEachNode_2 extends _116PoplulatingNextRightPointersInEachNode{
    /**
     * 测试方式直接去对应问题下面进行测试
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
     *
     * @param args
     */
    public static void main(String[] args) {
        var obj = new _117PoplulatingNextRightPointersInEachNode_2();
        String rootStr = "[1,2,3,4,5,null,7]";
        Node treeRoot = Node.genTreeFromLevelOrder(rootStr);
        obj.connect(treeRoot);
        System.out.println("res:" + printTreeByNext(treeRoot)); //由于不是满二叉树，实际上这道题不太好验证结果
    }

    /**
     * 解题思路：该题目依旧要求是用O(1)的空间复杂度。
     * 针对{@link _116PoplulatingNextRightPointersInEachNode}的一个小小的改进，因为可能存在某个节点的孩子为null，所以为了
     * 保证每层都用next连接上，我们必须找到一个不为null的儿子，然后连接上next
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (null == root){
            return root;
        }

        Node curLevel = root;
        while(null != curLevel) {
            Node cur = curLevel;
            Node first = cur.left;
            //先找第一个
            while (null != cur && null == first) {
                first = (null != cur.left) ? cur.left : cur.right;
                //避免跳过去right孩子
                if (null == cur.left) {
                    cur = cur.next;
                }
            }

            //因为有可能左孩子为空，所以要赋值给第一个不为空的节点
            curLevel = first;
            //找到第一个后，就开始循环处理了
            while(null != cur){
                if (null != cur.left && cur.left != first){
                    first.next = cur.left;
                    first = cur.left;
                }

                if (null != cur.right){
                    first.next = cur.right;
                    first = cur.right;
                }
                cur = cur.next;
            }
        }
        return root;
    }
}
