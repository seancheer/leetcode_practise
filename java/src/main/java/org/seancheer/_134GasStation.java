package org.seancheer;

import org.seancheer.utils.LeetCodeParent;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * <p>
 * Note:
 * <p>
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 */
public class _134GasStation extends LeetCodeParent {

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        _134GasStation obj = new _134GasStation();
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        var res = obj.canCompleteCircuit(gas, cost);
        System.out.println("res:" + res);  //3

        gas = new int[]{5,1,2,3,4};
        cost = new int[]{4,4,1,5,1};
        res = obj.canCompleteCircuit(gas, cost);
        System.out.println("res:" + res);  //3
    }

    /**
     * 思路：两个差值即为能否到达的标志，如果值为正值，那么说明可以到达，否则的话，不能到达
     * 一直进行遍历循环，从某个正值开始后，如果直到结尾，一直都是正值，那么说明从该位置开始后是可达的。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (null == gas || gas.length == 0 || null == cost || cost.length == 0){
            return -1;
        }

        int len = gas.length;
        int res = 0;
        int tank = 0;
        int sum = 0;
        //如果最终的sum少于0，那么说明根本就不可达。否则的话，res一定会是一个有效的值
        for(int i = 0;i < len;++i){
            int tmp = (gas[i] - cost[i]);
            sum += tmp;
            tank += tmp;
            if (tank < 0){
                res = i + 1;
                tank = 0;
            }
        }

        if (sum < 0){
            return -1;
        }

        return res;
    }
}
