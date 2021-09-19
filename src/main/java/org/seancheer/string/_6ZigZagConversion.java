package org.seancheer.string;

import org.seancheer.utils.LeetCodeParent;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 * <p>
 * Input: s = "A", numRows = 1
 * Output: "A"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 *
 * @author: seancheer
 * @date: 2021/9/15
 **/
public class _6ZigZagConversion extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _6ZigZagConversion();
        String str = "PAYPALISHIRING";
        int numRows = 3;
        var res = obj.convert(str, numRows);
        var res2 = obj.convert2(str, numRows);
        System.out.println("res:" + res + "   res2:" + res2); // PAHNAPLSIIGYIR

        str = "PAYPALISHIRING";
        numRows = 4;
        res = obj.convert(str, numRows);
        res2 = obj.convert2(str, numRows);
        System.out.println("res:" + res + "   res2:" + res2); // PINALSIGYAHRPI

        str = "A";
        numRows = 1;
        res = obj.convert(str, numRows);
        res2 = obj.convert2(str, numRows);
        System.out.println("res:" + res + "   res2:" + res2); // A

        str = "ABC";
        numRows = 1;
        res = obj.convert(str, numRows);
        res2 = obj.convert2(str, numRows);
        System.out.println("res:" + res + "   res2:" + res2); // ABC

        str = "ABCDEFGHIJKLMN";
        numRows = 5;
        res = obj.convert(str, numRows);
        res2 = obj.convert2(str, numRows);
        System.out.println("res:" + res + "   res2:" + res2); // "AIBHJCGKDFLNEM"
    }


    /**
     * 解题思路：按照zigzag的方式返回最终的字符串
     * 开辟一片内存，专门存储对应的位置的字符，最后返回最终结果即可，注意计算所需要行列个数时候的细节，面试的话，建议直接使用字符串
     * 的长度来存储作为行和列
     * 这种解法比较直观，建议看解法2，不需要额外申请内存，直接找出index和其位置的关系
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (null == s || s.isEmpty() || numRows <= 1) {
            return s;
        }

        int len = s.length();
        int rows = numRows;
        //中间斜着的部分会计算列数，注意，因为有可能后面的对数不满足要求，但是剩余的字符可能会占用多个列数，所以这里把其当成一个完整的部分
        int cols = ((len / ((numRows - 1) * 2)) + 1) * (numRows - 1) + 1;
        Character[][] resChar = new Character[rows][cols];
        int i = 0;
        int col = 0;
        while (i < len) {
            //从上往下打印
            int j = 0;
            while (i < len && j < rows) {
                resChar[j++][col] = s.charAt(i++);
            }

            //下一列了
            ++col;
            //从下往上打印斜着打印
            j = rows - 2;
            while (i < len && col < cols && j > 0) {
                resChar[j--][col++] = s.charAt(i++);
            }
        }
        //统计最终的结果
        StringBuilder res = new StringBuilder();
        for (i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (resChar[i][j] != null) {
                    res.append(resChar[i][j]);
                }
            }
        }
        return res.toString();
    }


    /**
     * 解题思路：其实最终的zigzag和字符串的index存在某种对应关系，这种方法就是找出这种对应关系
     * 其实对应关系很简单，即gap = (numRows - 1) * 2
     * 第一行每个字符之间的index相差gap
     * 第二行第一对字符之间的index相差gap - 2，第二对相差2
     * 第三行第一对字符之间相差gap - 4，第二对相差4
     * 以此类推即可
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if (null == s || s.isEmpty() || numRows <= 1) {
            return s;
        }
        int len = s.length();
        int gap = (numRows - 1) * 2;
        StringBuilder res = new StringBuilder();
        int curGap = gap;
        for (int i = 0; i < numRows; ++i) {
            //final row
            if (curGap == 0) {
                curGap = gap;
            }
            int j = i;
            while (j < len) {
                res.append(s.charAt(j));
                j += curGap;
                //提前计算第二对的间距
                int curGap2 = gap - curGap;
                //curGap2 != 0是为了识别第一行和最后一行
                if (j < len && curGap2 != 0) {
                    res.append(s.charAt(j));
                    j += curGap2;
                }
            }
            curGap -= 2;
        }
        return res.toString();
    }
}
