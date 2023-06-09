package Assignments.Assignment_6;

import java.util.Arrays;

public class SudokuProblem {
  
    public static void main(String[] args) {

        int[][] board = new int[][]{
            {0, 9, 0, 0, 4, 2, 6, 0, 0},
            {4, 0, 0, 0, 5, 0, 0, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 7, 2, 0, 0, 3, 4},
            {0, 0, 6, 0, 0, 0, 1, 0, 0},
            {7, 3, 0, 0, 9, 4, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 7, 0, 0, 0, 8},
            {0, 0, 9, 5, 3, 0, 0, 2, 0}
        };
       solveSudoku(board, 0);
    }

	public static boolean solveSudoku(int[][] sudoku, int position){
        if(position >= 81){
            for (int i = 0; i < 9; i++) {
                System.out.println(Arrays.toString(sudoku[i]));
            }
            return true;
        }
        if (sudoku[position/9][position%9] != 0) {
            return solveSudoku(sudoku, position + 1);
        } else{
            for (int i = 1; i < 10; i++) {
                if (valid(sudoku, i, position/9, position%9)) {
                    sudoku[position/9][position%9] = i;
                    if (solveSudoku(sudoku, position+1)) {
                        return true;
                    } else{
                        sudoku[position/9][position%9] = 0;
                    }
                }
            }
        }
        return false;
    }


    public static boolean valid(int sudoku[][], int number, int row, int col)
    {
		//check box
        int x = row/3;
        int y = col/3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(sudoku[i +(3*x)][j+(3*y)] == number){
                    return false;
                }
            }
        }

        //check row
        for (int i = 0; i < sudoku[row].length; i++) {
            if (sudoku[row][i] == number){
                return false;
            }
        }
        //checks column
        for (int i = 0; i < sudoku[0].length; i++) {
            if (sudoku[i][col] == number){
            return false;
            }
        }
        return true;
        
    
    }

    
}