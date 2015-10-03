/**
 * 
 * Brainstorming
 * 
 * This solution is very similar to sol1, but here we just used one variable setFirstCol. setFirstRow has been stored in matrix[0][0].
 * Thus, in sol2 we treat first row as other rows but still store column information in first row.
 * We combine the extra loops for first row/col inside the big loop. So we need to be careful about corner case, i.e. first row and first col
 * Thus we will update the matrix backward (bot->top, right -> left) to avoid the overwrite of info on first col/row
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 1:33:49 AM
 */
public class Set_Matrix_Zeroes_p73_sol2 {
    public void setZeroes(int[][] matrix) {
        //we treat first row as general row, use matrix[0][0] to store the setFirstRow information
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean setFirstCol = false;
        
        //we start mark info on first col and row
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0) setFirstCol = true;
            //skip first col, since we are setting it, otherwise we may incorrectly set [0][0] to 0
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        //we will update the matrix backward (bot->top, right -> left) to avoid the overwrite of info on first col/row
        //treat first row as general row, so we will cover it
        for(int i = m -1; i >= 0; i --){
            for(int j = n - 1; j >= 1; j--){
                //since we used [0][0] to store setFirstRow, we couldn't set first col same as other cols
                //we will set it based on setFirstCol
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
            
            //set first col specifically
            if(setFirstCol) matrix[i][0] = 0;
        }
    }
}
