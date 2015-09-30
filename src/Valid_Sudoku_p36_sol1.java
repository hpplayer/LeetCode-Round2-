/*
* 
* Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
* 
* The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
* 
* A partially filled sudoku which is valid.
* 
* Note:
* A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
* 
*               
*/


/**
 * 
 * Matrix + math
 * 
 * This problem can be solved by different solutions, but the tricky part is to get a short and clean code. The code below
 * uses a trick to visit blocks
 * 
 * We will still have two nested loops. The outer loop will visit each row, while the inner loop will visit each column.
 * We can easily check each column during the inner loop, checking row is also not hard during the outer loop, the problem
 * is how to check the small 3*3 blocks. We still have 9 blocks, so a matrix with 9*9 should be enough. But how to distribute
 * 9 blocks to 9 rows? we found for first block, all row/3 and col/3 will get 0, in the second block, all row/3 will get 0
 * and col/3 will get 1...in the last block, all row/3 and col/3 will get 2, so it seems if we use row/3 + col/3, then we can
 * find a way to distribute them. But we may have the blocks that have same sum, like fourth block and the second block. So
 * the trick here is to multiple the row/3 * 3, thus second block still has 1, but fourth block will have 3. So now we found 
 * the way to evenly distribute 9 blocks
 * 
 * @author hpPlayer
 * @date Sep 29, 2015 11:56:30 PM
 */
public class Valid_Sudoku_p36_sol1 {
    public boolean isValidSudoku(char[][] board) {
        //3 matrix works as hashMap
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                char c = board[i][j];
                if(c == '.') continue;//no need to validate .
                
                int val = c - '0' - 1;//convert to int then convert to index
                //we want to distinguish 9 blocks and put 9 cells in one block together.
                //if we just use i/3 + j/3, then 1 + 0 and 0 + 1 blocks will be put together,
                //so we use a trick, let i/3*3, then we will get 1+0=>3+0 which will be differed from 0 + 1
                int k = i/3*3 + j/3;
                
                //if current val in any of row/col/block has been visited, then we will report false
                if(rows[i][val] || cols[j][val] || blocks[k][val]) return false;
                
                //otherwise, we set the corresponding value to be true, then continue loop
                rows[i][val] = cols[j][val] = blocks[k][val] = true; 
            }
        }
        
        return true;
    }
}
