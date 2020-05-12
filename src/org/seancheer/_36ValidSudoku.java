package org.seancheer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 36. Valid Sudoku
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition，注意，3*3 子block是不会重叠的，
 * 也就是说每个第一个block的宽度为3，那么第二个block将从列index为3的位置开始，而不是从1开始重新计算一个block
 */
public class _36ValidSudoku {
    public static void main(String[] args){
        _36ValidSudoku obj = new _36ValidSudoku();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean res = obj.isValidSudoku(board);
        boolean res2 = obj.isValidSudoku2(board);
        System.out.println("res:" + res + "   res2:" + res2);
    }


    /**
     * 判断二维数组是否合法
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0){
            return false;
        }

        //check row, 格子固定为9
        int len = board.length;
        for (int i = 0; i < len; ++i) {
            boolean[] row = new boolean[len];
            boolean[] col = new boolean[len];
            boolean[] block = new boolean[len];
            for (int j = 0; j < len; ++j) {
                //row
                int idx = board[i][j] - '1';
                if(board[i][j] != '.'){
                    if (row[idx]){
                        return false;
                    }else{
                        row[idx] = true;
                    }
                }

                //column
                idx = board[j][i] - '1';
                if (board[j][i] != '.'){
                    if (col[idx]){
                        return false;
                    }else{
                        col[idx] = true;
                    }
                }

                //block
                //注意，该解法的巧妙之处在这里，如果通过一次遍历的方式从而遍历整个subBlock
                int x = i / 3 * 3 + j / 3;
                int y = i % 3 * 3 + j % 3;
                if(board[x][y] != '.'){
                    idx = board[x][y] - '1';
                    if (block[idx]){
                        return false;
                    }else{
                        block[idx] = true;
                    }
                }
            }
        }

        return true;

    }

    /**
     * 网上别人的解法
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }
}
