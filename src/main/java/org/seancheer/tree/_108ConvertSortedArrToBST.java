package org.seancheer.tree;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * <p>
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 *
 * @author: seancheer
 * @date: 2021/8/24
 **/
public class _108ConvertSortedArrToBST extends TreeParent {
    public static void main(String[] args) {
        var obj = new _108ConvertSortedArrToBST();
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        var res = obj.sortedArrayToBST(nums);
        System.out.println("res:" + toStringTreeLevelOrder(res)); //[0,-3,9,-10,null,5]  [0,-10,5,null,-3,null,9]

        nums = new int[]{1, 3};
        res = obj.sortedArrayToBST(nums);
        System.out.println("res:" + toStringTreeLevelOrder(res)); //[1,3]
    }


    /**
     * 解题思路：把递增数组转换为平衡二叉树
     * 每次都找数组中间的值作为root节点，然后递归处理即可，这样子生成出来的树必然是平衡的，
     * 因为两边的数量一样，且处理逻辑也相同
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }

        return sortedArrayToBSTInternal(nums, 0, nums.length - 1);
    }

    /**
     * 递归进行处理
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode sortedArrayToBSTInternal(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTInternal(nums, left, mid - 1);
        root.right = sortedArrayToBSTInternal(nums, mid + 1, right);
        return root;
    }
}
