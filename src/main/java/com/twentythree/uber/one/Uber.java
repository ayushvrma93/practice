package com.twentythree.uber.one;
import java.util.ArrayList;
import java.util.List;

/**

 Given a 2-dimensional grid of characters, and a dictionary, find all words in the grid that also appear in the dictionary.
 A word can be formed by traversing the grid by going either left, right, top, or down, but NOT diagonal.
 Also, a single grid position can not be used more than once in a word.

 In the following 3x3 grid, with a dictionary of [ CAT, COPY, ASK, SOS ]
 C A T
 O S K
 P Y U

 output => [ CAT, COPY, ASK ]


 Approach:
 1. create a boolean array of same dimension which denotes whether a given cell is already visited.
 2. When picking any cell check if it is a valid cell or not.
 3. Start with an empty string and concat first character from the matrix(mark it as true in visited array) and check if the current string
    is present in the dictionary, if yes add it to the output list.
 4. Repeat the above step in all the four directions mentioned.

 */

public class Uber {

    public static void main(String[] args) {

        Character[][] mat = new Character[3][3];
        List<String> dict = new ArrayList<>();

        mat[0][0] = 'C';
        mat[0][1] = 'A';
        mat[0][2] = 'T';

        mat[1][0] = 'O';
        mat[1][1] = 'S';
        mat[1][2] = 'K';

        mat[2][0] = 'P';
        mat[2][1] = 'Y';
        mat[2][2] = 'U';

        dict.add("CAT");
        dict.add("COPY");
        dict.add("ASK");
        dict.add("CATT");

        System.out.println(getWords(mat, dict));
    }

    private static List<String> getWords(Character[][] mat, List<String> dict){

        int M = mat.length;
        int N = mat[0].length;

        boolean[][] visited = new boolean[M][N];

        System.out.print(visited);
        List<String> result = new ArrayList<>();

        for(String word : dict){
            for(int i=0; i<M; i++){
                for(int j=0; j<N; j++){
                    if(mat[i][j] == word.charAt(0)){
                        if(doesWordExist(mat, i, j, "", M, N, visited, word)){
                            result.add(word);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValid(int x, int y, int M, int N, boolean[][] visited){

        if(x < 0 || x >= M || y < 0 || y >= N) return false;

        if(visited[x][y]) return false;

        return true;
    }

    private static boolean doesWordExist(Character[][] mat, int x, int y, String output, int M, int N, boolean[][] visited, String currWord){

        if(!isValid(x, y, M, N, visited)){
            return false;
        }

        visited[x][y] = true;

        output = output + mat[x][y];

        if(output.equals(currWord)) return true;

        boolean found = doesWordExist(mat, x-1, y, output, M, N, visited, currWord) ||
                doesWordExist(mat, x+1, y, output, M, N, visited, currWord) ||
                doesWordExist(mat, x, y-1, output, M, N, visited, currWord) ||
                doesWordExist(mat, x, y+1, output, M, N, visited, currWord);

        visited[x][y] = false;
        return found;
    }
}
