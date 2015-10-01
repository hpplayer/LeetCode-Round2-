import java.util.Arrays;

/*
Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

/**
 * Backtracking problem
 * 
 * The tricky part is to find how can we visit the matrix. Actually, we don't need to pass a specific axis information to next
 * recursion since after we assign a number to '.', then it will become a normal cell, we can do nothing on it in next recursion.
 * The correct way to visit the matrix is just scanning all cells and stop at cell that has '.'.  If we found there is no '.' left
 * then we know we have solved the Sudoku
 * 
 * To save time, we firstly create 3 matrix that record used numbers in each col/row/block. This part is very similar to problem
 * Valid Sudoku (p36). Then we do recursion on each cell and try each valid number. If none of 1-9 values can meet requirement,
 * then we know one of above recursion has assigned an inappropriate value, so we will return back and try another value in above
 * recursions. Repeat this until we can fill the whole matrix. It is not tricky, just like a brute force or enumerate solution.
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 5:35:20 PM
 */

public class Sudoku_Solver_p37_sol1 {
	static char[] a= {'5', '3', '.', '.', '7', '.','.','.','.'};
	static char[] b= {'6', '.', '.', '1', '9', '5','.','.','.'};
	static char[] c= {'.', '9', '8', '.', '.', '.','.','6','.'};
	
	static char[] d= {'8', '.', '.', '.', '6', '.','.','.','3'};
	static char[] e= {'4', '.', '.', '8', '.', '3','.','.','1'};
	static char[] f= {'7', '.', '.', '.', '2', '.','.','.','6'};
	
	
	static char[] g= {'.', '6', '.', '.', '.', '.','2','8','.'};
	static char[] h= {'.', '.', '.', '4', '1', '9','.','.','5'};
	static char[] i= {'.', '.', '.', '.', '8', '.','.','7','9'};
	
	
	static char[][] board = {a,b,c,d,e,f,g,h,i};
	
	public static void main(String[] args){
		new Sudoku_Solver_p37_sol1().solveSudoku(board);
		for(int i = 0; i < board.length; i++){
			System.out.println(Arrays.toString(board[i]));
		}

	}
    public void solveSudoku(char[][] board) {
        //first part is similar to valid sudoku p36, we mark used number in matrix
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int val = board[i][j] - '0' - 1;
                int k = i/3*3 + j/3;//use the same trick to differ 2nd and 4th and other same-sum blocks
                
                rows[i][val] = cols[j][val] = blocks[k][val] = true;
            }
        }
        
        DFS(board, rows, cols, blocks);
    }
    
    public boolean DFS(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] blocks){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                //we stop at each "." and try to assign a number to it
                if(board[i][j] == '.'){
                    for(int l = 0; l < 9; l++){
                        //if it is a valid number
                    	int k = i/3*3 + j/3;
                        if(!rows[i][l] && !cols[j][l] && !blocks[k][l]){
                            board[i][j] = (char) (l + 1 + '0');//assign this value to board
                            rows[i][l] = cols[j][l] = blocks[k][l] = true;//mark this value as visited
                            //if this number can let us fill all "." in matrix, we will return it
                            if(DFS(board, rows, cols, blocks)) return true;
                            //otherwise, we will try next valid number
                            board[i][j] = '.';//assign this value to board
                            rows[i][l] = cols[j][l] = blocks[k][l] = false;//mark this value as visited                    
                        }   
                    }
                    //if we have tried all valid numbers and still cannot fill board, then one of the above
                    //recursion must be wrong, so we need return back
                    return false;
                }
            }
        }       
        
        return true;//if we check all board and all "." has been failed, then is would be our final solution
    }
}
