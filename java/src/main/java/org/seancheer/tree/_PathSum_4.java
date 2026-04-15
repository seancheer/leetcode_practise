package org.seancheer.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 * 如果树的深度小于5，则该树可以由三位整数的列表表示。
 * 对于此列表中的每个整数：
 * 百位数表示该节点的深度D，1 <= D <= 4。
 * 2.十位数表示该节点在其所属级别中的位置P，1 <= P <= 8.该位置与完整二叉树中的位置相同。
 * 3.单位数字表示该节点的值V，0 <= V <= 9。
 * <p>
 * 给定一个表示深度小于5的二叉树的升序三位整数列表，您需要返回从根到叶子的所有路径和的总和。
 * <p>
 * 样例
 * 例 1:
 * <p>
 * 输入: [113, 215, 221]
 * 输出: 12
 * 解释:
 * 该树如下图所示:
 * 3
 * / \
 * 5   1
 * <p>
 * 所有的路径和为 (3 + 5) + (3 + 1) = 12.
 * 例 2:
 * <p>
 * 输入: [113, 221]
 * 输出: 4
 * 解释:
 * 该树如下所示:
 * 3
 * \
 * 1
 * <p>
 * 所有的路径和为 (3 + 1) = 4.
 * 这道题LeetCode是收费的，可以参考下面的网站：
 * https://www.lintcode.com/problem/1098/
 * 数组是排好序的，所以无需担心顺序问题
 *
 * @author: seancheer
 * @date: 2021/8/20
 **/
public class _PathSum_4 {
    public static void main(String[] args) {
        var obj = new _PathSum_4();
        int[] nums = new int[]{113, 215, 221};
        var res = obj.pathSumIV(nums);
        System.out.println("res:" + res); //12

        nums = new int[]{113, 221};
        res = obj.pathSumIV(nums);
        System.out.println("res:" + res); //4

        nums = new int[]{110, 211, 227, 318, 328, 331, 349, 431, 448, 457, 478, 489};
        res = obj.pathSumIV(nums);
        System.out.println("res:" + res); //100

        nums = new int[]{456, 331, 320, 229, 215, 113, 468, 342, 431};
        res = obj.pathSumIV(nums);
        System.out.println("res:" + res); //63
    }

    /**
     * 解题思路：可以看出来传入的是一个层次遍历的结果，因为计算的root到叶子节点的路径和，所以我们在代码逻辑上必须判断
     * 当前节点是否为叶子节点；
     * 1 使用一个map存储 每一个节点的标识 -> 节点值  的映射，从根节点开始，每次往其儿子节点进行遍历，节点标识由 level+nodeIdx组成key
     * 2 访问儿子的时候，拼装出儿子的key，递归进行下一步的处理
     *
     * @param nums: the list
     * @return: the sum of all paths from the root towards the leaves
     */
    public int pathSumIV(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        //本来题目要求是排好序的，但是lintcode这个网站却出现了没有排序的用例，因此这里多余个操作，排个序
        Arrays.sort(nums);
        Map<String, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(getKey(num), num % 10);
        }
        //接下来递归处理map
        res = 0;
        pathSumIVInternal(getKey(nums[0]), map, 0);
        return res;
    }

    private int res = 0;

    private void pathSumIVInternal(String cur, Map<String, Integer> map, int parentSum) {
        if (!map.containsKey(cur)) {
            return;
        }

        int level = Integer.parseInt(cur.substring(0, 1));
        int nodeIdx = Integer.parseInt(cur.substring(1, 2));
        int value = map.get(cur);

        String leftChildKey = String.valueOf(level + 1) + (2 * nodeIdx - 1);
        String rightChildKey = String.valueOf(level + 1) + 2 * nodeIdx;
        //叶子节点，必须要统计结果了
        if (!map.containsKey(leftChildKey) && !map.containsKey(rightChildKey)) {
            res += (parentSum + value);
            return;
        }

        pathSumIVInternal(leftChildKey, map, parentSum + value);
        pathSumIVInternal(rightChildKey, map, parentSum + value);
    }


    /**
     * key为level数+节点编号
     *
     * @param num
     * @return
     */
    private String getKey(int num) {
        int level = num / 100;
        int nodeIdx = num / 10 % 10;
        return String.valueOf(level) + nodeIdx;
    }
}
