/*
Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/	

/**
 * 
 * Brainstorming
 * 
 * This problem is not hard to implement but lengthy.
 * 
 * We will visit the matrix 2 times
 * First time, we will store the 0s information in first row and col. 
 * Second time, we will set the matrix based on info from first row/col
 * 
 * Since we will replace info in first row/col, we need extra loops for them.
 * 
 * Sol1 is the plain solution and treat first row/col respectively
 * Sol2 combines the check of first col/row inside loop, so it will be shorter
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 1:22:56 AM
 */

public class Set_Matrix_Zeroes_p73_sol1 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean setFirstRow = false;
        boolean setFirstCol = false;
        
        //look for 0s in first row and col. 
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                setFirstCol = true;
                break;
            }
        }
        
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0){
                setFirstRow = true;
                break;
            }
        }
        
        //look for 0s in matrix exclude first row/col
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        //set 0s for matrix
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }   
        
        //set 0s for first row
        if(setFirstRow) for (int i = 0; i < n; i++) matrix[0][i] = 0;
        
        //set 0s for first col
        if(setFirstCol) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }
}
