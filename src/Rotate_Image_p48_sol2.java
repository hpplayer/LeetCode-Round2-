/**
 * 
 * Math rotation.
 * 
 * To rotate the matrix by 90 degree, we just need to reverse the whole matrix then transpose based on diagonal
 * ex:
 * 1 2   3 4    3 1
 * 3 4   1 2    4 2 which is exactly the rotated matrix of original
 * 
 * This approach is very straightforward. We just flip the matrix, then transpose.
 * But the idea is hard to come up with.
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 11:57:53 PM
 */
public class Rotate_Image_p48_sol2 {
    public void rotate(int[][] matrix) {
        //firstly rotate the matrix
        
        int top = 0, bot = matrix.length - 1;
        
        while(top < bot){
            for(int i = 0; i < matrix.length; i++){
                int temp = matrix[top][i];
                matrix[top][i] = matrix[bot][i];
                matrix[bot][i] = temp;
            }
            top ++;
            bot --;
        }
        
        //then transpose based on diagnoal
        //we only need to scan the part below diagonal, i.e. all j < i
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
            
        }
    }
}
