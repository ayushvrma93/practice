package com.twentyfour.recursion.backtracking;

public class SearchWordInGrid {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == word.charAt(0)){
//                    boolean result = existsUtilGPT(board, word, 0, i, j, visited);
                    boolean result = existsUtil(board, word, 0, i, j, visited);
                    if(result){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //wrong method
    public boolean existsUtil(char[][] board, String word, int i, int m, int n, boolean[][] visited){
        if(visited[m][n] || word.charAt(i) != board[m][n]) return false;

        visited[m][n] = true;

        if(i==word.length()-1 && word.charAt(i) == board[m][n]){
            return true;
        }

        boolean nrow = false;
        boolean ncol = false;
        boolean prow = false;
        boolean pcol = false;

        if(m<board.length-1){
            nrow = existsUtil(board, word, i+1, m+1, n, visited);
        }

        if(n<board[0].length-1){
            ncol = existsUtil(board, word, i+1, m, n+1, visited);
        }

        if(m>0){
            prow = existsUtil(board, word, i+1, m-1, n, visited);
        }

        if(n>0){
            pcol = existsUtil(board, word, i+1, m, n-1, visited);
        }

        boolean exists = nrow || ncol || prow || pcol;

        visited[m][n] = false;

        return exists;

    }

    public boolean existsUtilGPT(char[][] board, String word, int index, int m, int n, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        if (m < 0 || m >= board.length || n < 0 || n >= board[0].length || visited[m][n] || board[m][n] != word.charAt(index)) {
            return false;
        }

        visited[m][n] = true;

        boolean exists = existsUtilGPT(board, word, index + 1, m + 1, n, visited) ||
                existsUtilGPT(board, word, index + 1, m - 1, n, visited) ||
                existsUtilGPT(board, word, index + 1, m, n + 1, visited) ||
                existsUtilGPT(board, word, index + 1, m, n - 1, visited);

        visited[m][n] = false;

        return exists;
    }

    public static void main(String[] args) {
        SearchWordInGrid search = new SearchWordInGrid();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board1 = {{'A','B'},{'C','D'}};
        String word = "ABCCED";
        String word1 = "SEE";
        String word2 = "ABCB";
        String word3 = "BACD";
        System.out.println(search.exist(board, word));
        System.out.println(search.exist(board, word1));
        System.out.println(search.exist(board, word2));
        System.out.println(search.exist(board1, word3));
    }
}
