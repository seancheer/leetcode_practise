package org.seancheer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 一些工具类，负责打印之类的工作
 */
public class LeetCodeParent {
    private static final String START_SEPERATOR = "--------------------------------------------------%s----------------------------------------------";
    private static final String  END_SEPERATOR = "----------------------------------------------------------------------------------------------";

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

    /**
     * generate a array
     * @param size
     */
    protected static int[] genArray(int size, int upBound){
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for(int i = 0;i < arr.length;++i){
            arr[i] = random.nextInt(upBound);
        }
        return arr;
    }

    /**
     * print array
     * @param arr
     * @param keyWord
     */
    protected static void printArray(int[] arr, String keyWord){
        System.out.println(String.format(START_SEPERATOR, keyWord));
        for(int i = 0;i < arr.length;++i){
            System.out.print(arr[i] + "\t");
            if (i > 0 && i % 50 == 0){
                System.out.print("\n");
            }
        }
        System.out.println("");
        System.out.println(END_SEPERATOR);
    }

    /**
     * get max item from arrary
     * @param arr
     * @return
     */
    protected static int getMaxItem(int[] arr){
        return Arrays.stream(arr).max().getAsInt();
    }

    /**
     * count bit for integer.
     * @param num
     * @return
     */
    protected static int howManyBit(int num){
        int count = 0;
        while (num > 0) {
            num /= 10;
            ++count;
        }
        return count;
    }

    /**
     * get the specific bit for a number
     * @param val
     * @param idx
     * @return
     */
    protected static int getSpecificBit(int val, int idx){
        int res = 0;
        int i = 0;
        while(i++ < idx){
            res = val % 10;
            val /= 10;
        }
        return res;
    }
}
