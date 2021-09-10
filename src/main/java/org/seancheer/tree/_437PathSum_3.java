package org.seancheer.tree;

import org.seancheer.array._1TwoSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 * @author: seancheer
 * @date: 2021/8/19
 **/
public class _437PathSum_3 extends TreeParent {

    public static void main(String[] args) {
        var obj = new _437PathSum_3();
        String root = "[10,5,-3,3,2,null,11,3,-2,null,1]";
        int targetSum = 8;
        var res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        var res2 = obj.pathSum2(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res + "   res2:" + res2); //3

        root = "[5,4,8,11,null,13,4,7,2,null,null,5,1]";
        targetSum = 22;
        res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        res2 = obj.pathSum2(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res + "   res2:" + res2); //3

        root = "[1,-2,-3]";
        targetSum = -1;
        res = obj.pathSum(genTreeFromLevelOrder(root), targetSum);
        res2 = obj.pathSum2(genTreeFromLevelOrder(root), targetSum);
        System.out.println("res:" + res + "   res2:" + res2); //3
    }

    /**
     * 解题思路：和之前的一样，二叉树的值有可能为正值也有可能为负值，所以无法进行剪枝操作，只能无限遍历，
     * 这道题有点修改：不需要一定leaf节点，并且，不一定要从root节点开始，可能是中间的某一段路，注意区别
     * <p>
     * 因为可能从中间节点开始路径，所以我们需要使用一个List来记录路径上的数字，每遇到一个新的节点，还要往回判断
     * 加上该节点的值后是否满足要求，如果满足要求，记录结果，不满足的话依次倒序加上当前节点的值，构成新的路径，传给下一层
     * 继续遍历
     * <p>
     * 解法1相对来说还是比较低效，且看解法2
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return 0;
        }

        return pathSumInternal(root, targetSum, new ArrayList<>());
    }

    private int pathSumInternal(TreeNode root, int targetSum, List<Integer> paths) {
        if (null == root) {
            return 0;
        }

        int res = 0;
        List<Integer> newPaths = new ArrayList<>();
        //开始判断往前的路径是否有满足要求的结果
        for (int i = paths.size() - 1; i >= 0; --i) {
            Integer cur = paths.get(i);
            //倒序更新路径，为什么倒序？因为我们的路径是不可以跨节点的，每个节点都必须计算
            if (cur + root.val == targetSum) {
                ++res;
            }
            //新的路径结果
            newPaths.add(cur + root.val);
        }

        //注意将自身也要加进去，自身也是一个新的起点，且单个节点也算一个路径
        if (root.val == targetSum) {
            res += 1;
        }
        newPaths.add(root.val);

        res += pathSumInternal(root.left, targetSum, newPaths);
        res += pathSumInternal(root.right, targetSum, newPaths);
        return res;
    }


    /**
     * 解题思路：其实和解法1的总体思路是一致的，关键在于如何快速识别到当前node的值加上以前的路径刚好等于target的方式，解法1采用的
     * 是List遍历的方式，该方法较为低效，本方法借用{@link _1TwoSum}的思路，采用如下逻辑：
     * 1 使用map存储   迄今为止从root到当前节点的路径和 -> 构成该和的组合数，比如一条路径为：[1,2,-1,0]，那么从root节点到任意
     * 节点路径和为2的方式有：[1,2,-1]和[1,2,-1,0]两种方式，那么map中就存储 2 -> 2
     * 2 当我们遍历到任意的一个节点curNode的时候，假设其值为val，使得curSum = prevSum + val，然后我们用tmp = curSum - target的结果，在
     * map中查找，因为map中存储的都是从root节点到任意路径的和，所以相当于我们用root节点到curNode的和curSum减去root节点到
     * 任意节点的和（tmp = curSum - target），那么中间的那段路径的和不就刚好等于target么？(curSum - tmp = curSum - (curSum - target) = target)
     * 注意往map中默认存储0
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum2(TreeNode root, int targetSum) {
        if (null == root) {
            return 0;
        }
        sumMap.clear();
        //放入0的目的请看下面代码
        sumMap.put(0, 1);
        return pathSum2Internal(root, targetSum, 0);
    }

    private Map<Integer, Integer> sumMap = new HashMap<>();

    /**
     * 遍历所有可能的路径
     * @param root
     * @param targetSum
     * @param curSum
     * @return
     */
    private int pathSum2Internal(TreeNode root, int targetSum, int curSum) {
        if (null == root) {
            return 0;
        }

        int newSum = root.val + curSum;
        //因为newSum有可能刚好等于targetSum，那么tmp就为0，所以我们要提前往map中放入0 -> 1
        int tmp = newSum - targetSum;
        int res = 0;
        Integer count = sumMap.get(tmp);
        if (null != count) {
            //说明在此之前有count中方式可以构成tmp，那么就意味者tmp+newSum之后构成targetSum的方式也有count中
            res += count;
        }

        //本次又是一种新的方式可以构成newSum，所以更新map
        if (sumMap.containsKey(newSum)) {
            sumMap.put(newSum, sumMap.get(newSum) + 1);
        } else {
            //第一次出现该sum
            sumMap.put(newSum, 1);
        }
        //继续往下层遍历
        res += pathSum2Internal(root.left, targetSum, newSum);
        res += pathSum2Internal(root.right, targetSum, newSum);
        //本次递归完成之后记得把map里面的值恢复原状，类似于回溯
        sumMap.put(newSum, sumMap.get(newSum) - 1);
        return res;
    }
}
