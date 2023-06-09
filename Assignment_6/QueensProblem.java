package Assignments.Assignment_6;

public class QueensProblem {
    
    public boolean safeQueensProblem(int[][] board, int row, int col){
        
        int i, j;
        for(i=0; i <col; i++){
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 1){
                return false;
            }
        }

        for(i = row, j = col; j >= 0 && i <8; i++, j--){
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public boolean solveQueen(int[][] board, int position) {
        if (position >= 8) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            if(safeQueensProblem(board, i, position)){
                board[i][position] = 1;

                if(solveQueen(board, position +1) == true){
                    return true;
                }

                board[i][position] = 0;
            }
        }
        return false;
    }

    public void printSol(int board[][]){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + board[i][j]
                                 + " ");
            }
            System.out.println();
        }
    }
    public boolean solveQueenProblem(){
        int board[][] = { { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 }};

        if (solveQueen(board, 0) == false) {
            System.out.println("Solution doesn't exist");
            return false;
        }
        printSol(board);
        return true;
    }


    public static void main(String[] args) {
        QueensProblem QueenProblem = new QueensProblem();
        QueenProblem.solveQueenProblem();
    }
}
