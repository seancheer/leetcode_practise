package org.seancheer.utils;

import java.util.ArrayList;
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
     * 打印二维数组，不过构造好要打印的东西，然后返回给caller，caller可以自定义输出格式
     * @param res
     * @return
     */
    protected static String toStringRes(int[][] res){
        StringBuilder sb = new StringBuilder();
        for (int[] item : res) {
            sb.append(Arrays.toString(item));
        }
        return sb.toString();
    }

    /**
     * 打印二维数组
     *
     * @param res
     */
    protected static void printRes(int[][] res) {
        System.out.print(toStringRes(res));
        System.out.println();
    }

    /**
     * 打印二维数组, List<int[]>的形式
     *
     * @param res
     */
    protected static void printListArr(List<int[]> res) {
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
     * 将类似于[[0,2],[1,3],[1,1]]这样的字符串，转换为二维数组，注意，需要字符串是一个二维数组字符串，也就意味着
     * row和col是固定的。
     *
     * @param str
     * @return
     */
    protected static int[][] convertStrTo2DemArr(String str) {
        if (null == str || str.isEmpty()) {
            return null;
        }

        str = str.trim().replaceAll(" ", "");
        str = str.substring(1, str.length() - 1).trim();
        if (str.isEmpty()){
            return new int[0][0];
        }
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
     * 把诸如[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]这样的字符串转换为list，和上面不同的是，
     * 这里的二维数组col是变化的
     *
     * @param arrStr
     * @return
     */
    protected static List<List<Integer>> convertStrTo2DemList(String arrStr) {
        if (null == arrStr || arrStr.isEmpty()) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        arrStr = arrStr.trim();
        arrStr = arrStr.substring(1, arrStr.length() - 1);
        arrStr = arrStr.replaceAll(" |\n|\t", "");
        if (arrStr.startsWith("[")) {
            arrStr = arrStr.substring(1, arrStr.length() - 1);
        }
        String[] itemArr = arrStr.split("]\\,\\[");
        for (String item : itemArr) {
            List<Integer> resItem = new ArrayList<>();
            String[] strItem = item.split(",");
            for (String str : strItem) {
                resItem.add(Integer.parseInt(str));
            }
            res.add(resItem);
        }
        return res;
    }

    /**
     * 将字符串转换为一维数组，类似的形式如：[1,2,3,4]
     * @param arrStr
     * @return
     */
    protected static int[] converStrTo1DemArr(String arrStr){
        if (null == arrStr){
            return new int[0];
        }
        arrStr = arrStr.strip().replaceAll(" |\n|\t|]|\\[", "");
        if (arrStr.isEmpty()){
            return new int[0];
        }

        String[] strs = arrStr.split(",");
        int[] res = new int[strs.length];
        for(int i = 0;i < strs.length;++i){
            res[i] = Integer.parseInt(strs[i]);
        }
        return res;
    }

    /**
     * 打印数组的特定位置，范围[0, threshold）
     * @param arr
     * @param threshold
     */
    protected static String toStringArr(int[] arr, int threshold){
        if (null == arr || arr.length == 0 || threshold < 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0;i < threshold && i < arr.length;++i){
            sb.append(arr[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String str = "[[0,2],[1,3],[1,1]]";
        var res = convertStrTo2DemArr(str);
        printRes(res);

        str = "  [[0,2,1],[1,3,2],[1,1,3]]  ";
        res = convertStrTo2DemArr(str);
        printRes(res);

        str = "[[2],[2] , [2, 6] ,[1],[1, 5], [1,2],[1],[2]] ";
        var res2 = convertStrTo2DemList(str);
        printRes(res2);


        str = " [1,2,3,4,5,6] ";
        var res3 = converStrTo1DemArr(str);
        System.out.println(Arrays.toString(res3));
    }
}
