import java.util.*;

/*
* 
* The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard 
* such that no two queens attack each other.
* 
* Given an integer n, return all distinct solutions to the n-queens puzzle.
* 
* Each solution contains a distinct board configuration of the n-queens' placement, 
* where 'Q' and '.' both indicate a queen and an empty space respectively.
* 
* For example,
* There exist two distinct solutions to the 4-queens puzzle:
* 
* [
*  [".Q..",  // Solution 1
*   "...Q",
*   "Q...",
*   "..Q."],
* 
*  ["..Q.",  // Solution 2
*   "Q...",
*   "...Q",
*   ".Q.."]
* ]
* 
*               
*/

/**
 * 
 * Backtracking solution
 * 
 * The tricky part is to be clear with rows/columns and write a clean code like we can use buildString() and isValid() functions
 * 
 * Since we will add each row as a list to result, we have to scan the matrix row by row.
 * We have an array called rows[], which records the column used by each row. 
 * Each recursion will check each column, if found a column that has not been used, then we will mark current row use this column
 * then go to next recursion. If we can fill last row, then we found a correct placement. Otherwise we will go above recursion, and
 * try another column in last recursion
 * 
 * Remark:
 * 1) Be careful, we don't allow two Queens on same row same columns and even same diagonals!<
 * 2) we may have two correct placements, that correct with previous part but differed in last rows. So we need to try each combination
 * 3) We use several tricks here, like build String only when we get one result, compare diffX and diffY to check if Q are overlap
 * in diagonals (diagonals has same diff x and diff y)
 * 
 * sol1 is recursive backtracking solution
 * sol2 is an amazing iterative solution of sol1, which does not use stack, just use the pure logic used in sol1, check it out!
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 12:35:58 AM
 */
public class N_Queens_p51_sol1 {
	public static void main(String[] args){
		for(List<String> lst : new N_Queens_p51_sol1().solveNQueens(4)){
			System.out.println(lst);
		}
	}
    int len = 0;
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        len = n;
        DFS(result, new int[len], 0);
        return result;
    }
    
    //rows record which col is used by each prev row
    public void DFS(List<List<String>> result, int[] rows, int row){
        if(row == len){
            //reach boundary, build list
            result.add(buildList(rows));
            return;
        }
        
        for(int i = 0; i < len; i++){
            //try each column
            if(isValid(rows, row, i)){
                //if found a valid col
                rows[row] = i;//declear current row has been occupied
                DFS(result, rows, row + 1);
            }
        }
    }
    
    public boolean isValid(int[] rows, int row, int col){
        for(int i = 0; i < row; i++){
            //we only need to check previous rows
            if(rows[i] == col) return false;//if col has been used by prev row, return false;
            
            //we also need to check the cell on diagonal, which is x + 1, y - 1 -> x + 2, y - 2, .. (top right) or
            //x - 1, y - 1 -> x -2, y -2, .. (top left)
            //we observe that (x2 - x1) = abs(y2 - y1), so compare delta x and delta y to check diagonal
            int xDiff = row - i;
            int yDiff = Math.abs(rows[i] - col);//we may use a col before or after input col in previous rows
            if(xDiff == yDiff) return false;
        }
        
        return true;
    }
    public List<String> buildList(int[] rows){
        List<String> temp = new ArrayList<String>();
        for(int i = 0; i < len; i++){
            StringBuilder sb = new StringBuilder();//each row is a string
            for(int j = 0; j < len; j++){
                if(rows[i] == j){
                    //if current col is marked as Q
                    sb.append("Q");
                }else{
                    //general case
                    sb.append(".");
                }
            }
            temp.add(sb.toString());
        }
        
        return temp;
    }
}
