package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        SolveNQueens solveNQueens = new SolveNQueens();
        System.out.println("Res : " + solveNQueens.solveNQueens(8));

    }

    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtracking(n, 0, board);
        return res;
    }

    void backtracking(int n, int row, char[][] board) {
        if (row == n) {
            List<String> path = new ArrayList<>();
            for(int i = 0;i<board.length;i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0;j<board[0].length;j++) {
                    sb.append(board[i][j]);
                }
                path.add(sb.toString());
            }
            res.add(path);
            return;
        }

        for(int col = 0; col<n; col++ ) {
            if (isVaild(row, col, board, n)) {
                board[row][col] = 'Q';
                backtracking(n, row + 1, board);
                board[row][col] = '.';
            }
        }
    }

    boolean isVaild(int row, int col , char[][] board, int n) {
        for(int i = 0;i<row;i++) {
            if (board[i][col] == 'Q') return false;
        }

        for(int i = row-1, j = col -1 ; i>=0&&j>=0 ;i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        for(int i = row-1, j= col +1; i>=0&&j<n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}
