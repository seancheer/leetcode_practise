package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 * <p>
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 * 1 每个孩子至少有1个糖果
 * 2 相邻的孩子，分数高的糖果数多，也就意味着不相邻的糖果数没有绝对的关系多少，也意味着相等的糖果数也不一定要相等
 *
 * @author: seancheer
 * @date: 2021/8/17
 **/
public class _135Candy extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _135Candy();
        int[] ratings = new int[]{1, 0, 2};
        var res = obj.candy(ratings);
        System.out.println("res: " + res); //5

        ratings = new int[]{1, 2, 2};
        res = obj.candy(ratings);
        System.out.println("res: " + res); //4

        ratings = new int[]{1,3,4,5,2};
        res = obj.candy(ratings);
        System.out.println("res: " + res); //11

        ratings = new int[]{1,2,87,87,87,2,1};
        res = obj.candy(ratings);
        System.out.println("res: " + res);  //13
    }

    /**
     * 解题思路：一种比较取巧的做法，其实就是寻找递增序列和递减序列
     * 1 首先赋予所有人1个糖果
     * 2 正向扫描，如果发现ratings[i] > ratings[i - 1]，那么candy[i] = candy[i - 1] + 1;
     * 3 反向扫描，如果发现ratings[i - 1] > ratings[i]，那么此时candy[i - 1] = Math.max(candy[i - 1], candy[i] + 1)，这里很好
     *   理解，因为candy不仅要满足正方向的要求，也要满足反方向的要求，所以取较大者
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (null == ratings || ratings.length <= 0) {
            return 0;
        }
        int len = ratings.length;
        int[] res = new int[len];
        Arrays.fill(res, 1);
        //满足分数高的比分数低的糖果多的要求
        for (int i = 1; i < len; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }

        for (int i = len - 1; i >= 1; --i) {
            //反向寻找递增序列
            if (ratings[i - 1] > ratings[i]) {
                //因为不仅要满足正向的要求，也要满足反向的要求，所以这里取较大者
                res[i - 1] = Math.max(res[i] + 1, res[i - 1]);
            }
        }
        return Arrays.stream(res).sum();
    }
}
