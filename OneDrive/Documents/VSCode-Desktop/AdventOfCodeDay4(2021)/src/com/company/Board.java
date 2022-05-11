package com.company;

import java.util.ArrayList;

public class Board {
    private int[][] board;
    private int[][] right;

    public Board() {
        this.board = new int[5][5];
        this.right = new int[5][5];
    }

    public Board (int[][] board) {
        this.board = board;
        this.right = new int[5][5];
    }

    public Board (Board b) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                this.board[i][j] = b.getNum(i, j);
                this.right[i][j] = b.getRight(i, j);
            }
        }
    }

    public int getNum(int row, int col) {
        return board[row][col];
    }

    public int getRight(int row, int col) {
        return right[row][col];
    }

    public void setRight(int row, int col) {
        right[row][col] = 1;
    }

    public boolean won() {
        for(int i=0; i<5; i++) {
            int sum = 0;
            for(int j=0; j<5; j++) {
                sum += right[i][j];
            }
            if (sum == 5) {
                return true;
            }
        }

        for(int i=0; i<5; i++) {
            int sum = 0;
            for(int j=0; j<5; j++) {
                sum += right[j][i];
            }
            if (sum == 5) {
                return true;
            }
        }
        return false;
    }

    public int calc() {
        int sum = 0;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(right[i][j] == 0) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }
}
