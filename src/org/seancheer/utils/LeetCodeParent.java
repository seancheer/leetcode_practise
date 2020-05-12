package org.seancheer.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 一些工具类，负责打印之类的工作
 */
public class LeetCodeParent {
    /**
     * 打印二维数组
     *
     * @param res
     */
    protected static void printRes(int[][] res) {
        for (int[] item : res) {
            System.out.print(Arrays.toString(item));
        }
        System.out.println();
    }

    /**
     * 打印列表中的列表
     *
     * @param res
     */
    protected static void printRes(List<List<Integer>> res) {
        for (List<Integer> item : res) {
            System.out.print(item);
        }
        System.out.println();
    }
}
