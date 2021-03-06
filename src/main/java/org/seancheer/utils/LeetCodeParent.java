package org.seancheer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 一些工具类，负责打印之类的工作
 */
public class LeetCodeParent {
    private static final String START_SEPERATOR = "--------------------------------------------------%s----------------------------------------------";
    private static final String END_SEPERATOR = "----------------------------------------------------------------------------------------------";

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
     *
     * @param size
     */
    protected static int[] genArray(int size, int upBound) {
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt(upBound);
        }
        return arr;
    }

    /**
     * print array
     *
     * @param arr
     * @param keyWord
     */
    protected static void printArray(int[] arr, String keyWord) {
        System.out.println(String.format(START_SEPERATOR, keyWord));
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + "\t");
            if (i > 0 && i % 50 == 0) {
                System.out.print("\n");
            }
        }
        System.out.println("");
        System.out.println(END_SEPERATOR);
    }

    /**
     * get max item from arrary
     *
     * @param arr
     * @return
     */
    protected static int getMaxItem(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    /**
     * count bit for integer.
     *
     * @param num
     * @return
     */
    protected static int howManyBit(int num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            ++count;
        }
        return count;
    }

    /**
     * get the specific bit for a number
     *
     * @param val
     * @param idx
     * @return
     */
    protected static int getSpecificBit(int val, int idx) {
        int res = 0;
        int i = 0;
        while (i++ < idx) {
            res = val % 10;
            val /= 10;
        }
        return res;
    }

    /**
     * 将类似于[[0,2],[1,3],[1,1]]这样的字符串，转换为二维数组。
     *
     * @param str
     * @return
     */
    protected static int[][] convertStrTo2DemArr(String str) {
        if (null == str || str.isEmpty()) {
            return null;
        }

        str = str.trim().replaceAll(" ", "");
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split("]");
        int row = strs.length;
        int col = strs[0].split(",").length;
        int[][] res = new int[row][col];
        for (int i = 0; i < strs.length; ++i) {
            String item = strs[i];
            if (item.startsWith(",")) {
                item = item.substring(2, item.length());
            } else {
                item = item.substring(1, item.length());
            }

            String[] itemArr = item.split(",");
            assert (col == itemArr.length);
            for (int j = 0; j < itemArr.length; ++j) {
                res[i][j] = Integer.parseInt(itemArr[j]);
            }
        }
        return res;
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = "[[0,2],[1,3],[1,1]]";
        var res = convertStrTo2DemArr(str);
        printRes(res);

        str = "  [[0,2,1],[1,3,2],[1,1,3]]  ";
        res = convertStrTo2DemArr(str);
        printRes(res);
    }
}
