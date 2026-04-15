package org.seancheer.array;

import org.seancheer.utils.LeetCodeParent;

import java.util.Arrays;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 * 四条规则并且每次的状态都是同时发生的，因此不能使用更新后的值来判断
 * 额外要求：使用O(1)的空间复杂度
 * 四条规则分别是：
 * 1 任何一个活人，其八方向的邻居只有少于2个人活着，当前位置死掉
 * 2 任何一个活人，其邻居有2-3活着，当前位置活
 * 3 任何一个活人，邻居有超过3个人活着，当前位置死
 * 4 任何一个死人，邻居刚好有3个人活着，当前位置活
 *
 * @author: seancheer
 * @date: 2021/9/7
 **/
public class _289GameOfLife extends LeetCodeParent {

    public static void main(String[] args) {
        var obj = new _289GameOfLife();
        String str = "[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]";
        var board = convertStrTo2DemArr(str);
        obj.gameOfLife(board);
        System.out.println("res:" + toStringRes(board)); // [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

        str = "[[1,1],[1,0]]";
        board = convertStrTo2DemArr(str);
        obj.gameOfLife(board);
        System.out.println("res:" + toStringRes(board)); //  [[1,1],[1,1]]

        str = "[[0]]";
        board = convertStrTo2DemArr(str);
        obj.gameOfLife(board);
        System.out.println("res:" + toStringRes(board)); //  [[0]]
    }

    final int die = 0;
    final int live = (1 << 0);
    final int die2Live = (1 << 1);
    final int live2Die = (1 << 2);

    /**
     * 解题思路：就地改变对应的状态，应该怎么做呢？
     * 为了就地复活，我们增加两个状态，
     * 1 如果当前位置由死变活，那么为2
     * 2 如果当前位置由活变死，那么为4
     * 之所以是2和4，是为了方便位运算，好像直接等于就可以了
     * 这样子我们在处理其他位置的时候，就能得到以前的状态
     * 处理完成后，根据3和4设置为0活着1，就达到了最终的目的
     * 此题还可以精巧的使用位运算，参考 https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
     * 这样子在恢复状态的时候，只需要进行一个位运算就可以了
     * @param board
     */
    public void gameOfLife(int[][] board) {
        if (null == board || board.length <= 0 || board[0] == null) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        //八方向
        final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}};

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int neighbor = 0;
                for (int[] item : directions) {
                    neighbor += checkNeighbor(board, i + item[0], j + item[1], rows, cols);
                }

                if (board[i][j] == live && (neighbor < 2 || neighbor > 3)) {
                    board[i][j] = live2Die;
                }

                if (board[i][j] == die && neighbor == 3) {
                    board[i][j] = die2Live;
                }
            }
        }

        //最后一步，复盘，将board变为0和1
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (board[i][j] == die2Live) {
                    board[i][j] = live;
                } else if (board[i][j] == live2Die) {
                    board[i][j] = die;
                }
            }
        }
    }

    private int checkNeighbor(int[][] board, int x, int y, int rows, int cols) {
        if (x < 0 || y < 0 || x >= rows || y >= cols) {
            return 0;
        }

        //只要当前是die活着以前是die，都为die
        if (board[x][y] == die || board[x][y] == die2Live) {
            return 0;
        }
        return 1;
    }
}
