package com.twentythree.practice;

public class RottenOranges {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = orangesRotting(grid, m-1, n-1, 0);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1)
                    return -1;
            }
        }
        return res;
    }


    public int orangesRotting(int[][] grid, int m, int n, int res) {

        if(m<0 || n<0) return Integer.MAX_VALUE;

        if(grid[m][n] == 0) return 0;

        grid[m][n] = Math.min(orangesRotting(grid, m-1, n, res+1), orangesRotting(grid, m, n-1, res+1));

        return res;

    }

    public static void main(String[] args) {
        RottenOranges rottenOranges = new RottenOranges();
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};

        System.out.println(rottenOranges.orangesRotting(grid1));

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(grid1[i][j] + " ");
            }
            System.out.println();
        }

    }
}
