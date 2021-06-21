package com.company;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// 542 01 矩阵
public class UpdateMatrix {
    int[][] directions = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) {

    }
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] res = new int[m][n];
        boolean[][] vistors = new boolean[m][n];
        Queue<int[]> queue= new LinkedList<>();

        for(int i = 0; i<m; i++) {
            for(int j = 0; j < n;j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                    vistors[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int cost = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int s=0;s<size;s++) {
                int[] cur = queue.poll();
                int i = cur[0], j = cur[1];
                if(mat[i][j]==1) {
                    res[i][j]=cost;
                }
                for(int[] dir:directions) {
                    int x =i +dir[0];
                    int y = j + dir[1];
                    if(x>=0 && x<m && y>=0 && y<n && !(vistors[x][y])) {
                        queue.offer(new int[]{x,y});
                        vistors[x][y]=true;
                    }
                }
            }
            cost++;
        }

        return res;

    }
}
