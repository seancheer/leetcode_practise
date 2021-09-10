package org.seancheer.tree;

/**
 * One way to serialize a binary tree is to use preorder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as '#'.
 * <p>
 * <p>
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where '#' represents a null node.
 * <p>
 * Given a string of comma-separated values preorder, return true if it is a correct preorder traversal serialization of a binary tree.
 * <p>
 * It is guaranteed that each comma-separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid.
 * <p>
 * For example, it could never contain two consecutive commas, such as "1,,3".
 * Note: You are not allowed to reconstruct the tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 * Example 2:
 * <p>
 * Input: preorder = "1,#"
 * Output: false
 * Example 3:
 * <p>
 * Input: preorder = "9,#,#,1"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 104
 * preorder consist of integers in the range [0, 100] and '#' separated by commas ','.
 * 解法2更加牛，参考解法2（采用了图论里面的出度和入度的思想）
 * @author: seancheer
 * @date: 2021/8/27
 **/
public class _331VerifyPreorderSerializationBT extends TreeParent {
    public static void main(String[] args) {
        var obj = new _331VerifyPreorderSerializationBT();
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        var res = obj.isValidSerialization(preorder);
        var res2 = obj.isValidSerialization2(preorder);
        System.out.println("res:" + res + "   res2:" + res2); //true

        preorder = "1,#";
        res = obj.isValidSerialization(preorder);
        res2 = obj.isValidSerialization2(preorder);
        System.out.println("res:" + res + "   res2:" + res2); //false

        preorder = "9,#,#,1";
        res = obj.isValidSerialization(preorder);
        res2 = obj.isValidSerialization2(preorder);
        System.out.println("res:" + res + "   res2:" + res2);//false

        //用例竟然有两位数，注意啊，还有可能有三位数，注意这两种情况
        preorder = "9,#,92,#,#";
        res = obj.isValidSerialization(preorder);
        res2 = obj.isValidSerialization2(preorder);
        System.out.println("res:" + res + "   res2:" + res2);//true
    }

    /**
     * 解题思路：不让把二叉树构造出来，必须判断字符串是否合法
     * 递归按照中序遍历的方式判断，然后使用一个成员变量的游标往前移动
     * 1 遍历完成后，如果发现数组多了，那么直接返回false
     * 2 遍历过程中，如果发现数组不够用了，那么也应该直接返回false
     * 3 只有遍历完成后，数组刚好被用完，才返回true
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        if (null == preorder || preorder.isEmpty()){
            return true;
        }

        res = true;
        i = 0;
        isValidSerializationInternal(preorder);
        //数组太多了
        if (i < preorder.length()){
            return false;
        }
        return res;
    }

    private boolean res = true;
    private int i = 0;

    /**
     * 递归进行判断，返回值有如下意义：
     * - 数字：表示有孩子
     * - #：表示无孩子
     * - x：表示数组到头了
     * @param preorder
     * @return
     */
    private char isValidSerializationInternal(String preorder){
        int len = preorder.length();
        if (i >= len){
            return 'x';
        }
        //跳过逗号
        if (preorder.charAt(i) == ','){
            ++i;
        }

        char root = preorder.charAt(i++);
        //因为可能存在两位数和三位数，所以这里必须把当前数字遍历完，不可能出现#123这样非法的数字，所以这里不考虑这个
        while(i < len && preorder.charAt(i) != ','){
            ++i;
        }

        //到达末尾了，不需要在遍历了
        if (root == '#'){
            return root;
        }
        char left = isValidSerializationInternal(preorder);
        char right = isValidSerializationInternal(preorder);

        //数组不够用了
        if ('x' == left || 'x' == right){
            res = false;
        }
        return root;
    }




    /**
     * 解题思路：采用图论里面出度和入度的思想；
     * 非叶子节点，一定会提供2个出度（即两个孩子，因为这道题如果孩子为null，那么会表示为#，所以也视为1个度），1个入度（父亲）
     * root节点只提供2个出度
     * 那么我们在遍历字符数组的时候，每遇到一个非null节点，则加2个度（即出度），减1个度（入度），遇到null节点，直接减1个入度；
     * 如果是一个合法的二叉树，那么最后的度必然为0（出度和入度相等）；其他情况说明不合法
     * @param preorder
     * @return
     */
    public boolean isValidSerialization2(String preorder) {
        if (null == preorder || preorder.isEmpty()) {
            return true;
        }
        //设置为1是因为统一算法，我们遇到root节点的时候，也要减1个度
        int sumDegree = 1;
        String[] nodes = preorder.split(",");
        for(int i= 0;i < nodes.length;++i){
            String cur = nodes[i];
            //遇到一个节点，直接减1个入度（即便是null节点）
            if (--sumDegree < 0){
                return false;
            }
            //非null节点，增加两个出度
            if (!"#".equalsIgnoreCase(cur)){
                sumDegree += 2;
            }
        }
        //合法的一定会是0
        return sumDegree == 0;
    }
}
