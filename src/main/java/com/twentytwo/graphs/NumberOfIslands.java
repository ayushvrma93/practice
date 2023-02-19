package com.twentytwo.graphs;

public class NumberOfIslands {

    final int ROW;
    final int COL;

    public NumberOfIslands(int row, int col) {
        ROW = row;
        COL = col;
    }

    public int countIslands(int[][] M){

        boolean[][] visited = new boolean[M.length][M[0].length];
        int count = 0;

        for(int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++){

                if(M[i][j] == 1 && !visited[i][j]){
                    DFS(M, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }


    private void DFS(int[][] M, int row, int col, boolean[][] visited) {

        int[] rowNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = {-1, 0, 1, -1, 1, -1, 0, 1};

        visited[row][col] = true;

        for(int i = 0; i < 8; i++){
            if(isSafe(M, row + rowNbr[i], col + colNbr[i], visited))
                DFS(M, row + rowNbr[i], col + colNbr[i], visited);
        }    }


    private boolean isSafe(int[][] M, int row, int col, boolean[][] visited) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && M[row][col] == 1 && !visited[row][col];
    }

    public static void main(String[] args) {

        int[][] M = {{1, 1, 0, 0, 0},
                     {0, 1, 0, 0, 1},
                     {1, 0, 0, 1, 1},
                     {0, 0, 0, 0, 0},
                     {1, 0, 1, 0, 1}};

        NumberOfIslands numberOfIslands = new NumberOfIslands(M.length, M[0].length);
        System.out.println(numberOfIslands.countIslands(M));
    }
}
