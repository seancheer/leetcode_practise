package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

/**
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
 * <p>
 * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
 * <p>
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: rows = 1, cols = 4, rStart = 0, cStart = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 * Example 2:
 * <p>
 * <p>
 * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 * @author: seancheer
 * @date: 2021/8/30
 **/
public class _885SpiralMatrix_3 extends LeetCodeParent {
    public static void main(String[] args) {
        var obj = new _885SpiralMatrix_3();
        int rows = 1;
        int cols = 4;
        int rStart = 0;
        int cStart = 0;
        var res = obj.spiralMatrixIII(rows, cols, rStart, cStart);
        var res2 = obj.spiralMatrixIII2(rows, cols, rStart, cStart);
        System.out.println("res:" + toStringRes(res) + "   res2:" + toStringRes(res2));//[[0,0],[0,1],[0,2],[0,3]]

        rows = 2;
        cols = 3;
        rStart = 0;
        cStart = 2;
        res = obj.spiralMatrixIII(rows, cols, rStart, cStart);
        res2 = obj.spiralMatrixIII2(rows, cols, rStart, cStart);
        System.out.println("res:" + toStringRes(res) + "   res2:" + toStringRes(res2));//[[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
    }

    /**
     * 解题思路：可以看到，因为是螺旋前进的，所以我们每走两步，下一步就需要多走一格，根据这种算法，就可以完美完成目标
     * 其实基本上思路都是一样的，区别在于代码的复杂度，可以看解法2，解法2的代码更加精巧
     *
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        //invalid input
        if (rows <= 0 || cols <= 0 || rStart < 0 || cStart < 0) {
            return new int[0][0];
        }

        int resSize = rows * cols;
        int[][] res = new int[resSize][2];
        int step = 1;
        int curX = rStart, curY = cStart;
        res[0] = new int[]{curX, curY};
        int idx = 0;
        while (idx < resSize) {
            int i = 0;
            //从左到右
            while (i++ < step && idx < resSize) {
                if (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
                    res[idx++] = new int[]{curX, curY};
                }
                curY++;
            }
            //从上到下
            i = 0;
            while (i++ < step && idx < resSize) {
                if (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
                    res[idx++] = new int[]{curX, curY};
                }
                ++curX;
            }
            //每走两步，step增加1
            step++;
            //从右到左
            i = 0;
            while (i++ < step && idx < resSize) {
                if (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
                    res[idx++] = new int[]{curX, curY};
                }
                --curY;
            }

            //从下到上
            i = 0;
            while (i++ < step && idx < resSize) {
                if (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
                    res[idx++] = new int[]{curX, curY};
                }
                --curX;
            }
            step++;
        }
        return res;
    }


    /**
     * 解题思路：思路和解法1一致，但是下面的代码设计的更加精巧，应该好好学习下
     *
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIII2(int rows, int cols, int rStart, int cStart) {
        //invalid input
        if (rows <= 0 || cols <= 0 || rStart < 0 || cStart < 0) {
            return new int[0][0];
        }

        int resSize = rows * cols;
        int[][] res = new int[resSize][2];
        //表示启动的四个方向
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //控制走的长度
        int len = 0;
        int directIdx = 0;
        int idx = 0;
        int curX = rStart, curY = cStart;
        //注意先添加起步坐标，因为循环一开始并不会计算该坐标
        res[idx++] = new int[]{curX, curY};
        while (idx < resSize) {
            //表示每走两步，走的长度就需要加1，思路和解法1一致
            if (directIdx == 0 || directIdx == 2) {
                ++len;
            }
            //控制走的长度
            for (int i = 0; i < len && idx < resSize; ++i) {
                curX += directions[directIdx][0];
                curY += directions[directIdx][1];
                if (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
                    res[idx++] = new int[]{curX, curY};
                }
            }

            //因为只有四个方向
            directIdx = (directIdx + 1) % 4;
        }
        return res;
    }
}
