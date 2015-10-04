import java.util.*;
/**
 * 
 * Iterative version of backtracking
 * 
 * First of all don't be terrified by the length of solution. The isValid() and buildList() is from sol1. The real iterative solution is short
 * 
 * Like recursion, we will try all combinations of placement. The trial is done, after we reach the last column in row 0. Like sol1, if we reach
 * last row, we will add it to result, and try the next column in previous row. Similarly, for each row, if we reach end column and still can't 
 * find an appropriate placement, then we will move column in its previous row. 
 * 
 * Something that needs to be noticed:
 * 1) we may have cases that first rows are same, but differed in last rows. So after we get one solution, we cannot directly jump to row0, but
 * instead, we will look at the last second row, and try another placement
 * 2) To avoid overflow problem, we will check boundary before any operation. Those boundary check include reach last row or reach last column in
 * row
 * 3) Like sol1, if current column is valid, then we will move to next row, otherwise we will try next column
 * 
 * @author hpPlayer
 * @date Oct 4, 2015 1:35:08 AM
 */

public class N_Queens_p51_sol2 {
    int len = 0;
    public List<List<String>> solveNQueens(int n) {
        len = n;
        List<List<String>> result = new ArrayList<List<String>>();
        int rows[] = new int[n];//which column is used by each row
        int row = 0;//current row value
        
        //while we can still found solutions by changing values in rows
        while(row >= 0){
            //reach boundary
            if(row == n){
                result.add(buildList(rows));
                rows[--row] ++;//try another col in last row
            }else if(rows[row] == n){//we tried all columns in current row
                //we put two boundary check first since we don't want get overflow in later part
                rows[row] = 0;
                row --;//move to last row
                if(row < 0) return result;//if we have tried all combinations and now need help from -1 row
                rows[row] ++;//otherwise we tried next column in prev row
            }else{
                //else we are looking at one row
                if(isValid(rows, row, rows[row])){
                    //if current column can be used, then move to next row
                    row ++;
                }else{
                    //current column can't be used, try to next column
                	rows[row] ++;
                }
            }
        }
        
        return result;
    
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
