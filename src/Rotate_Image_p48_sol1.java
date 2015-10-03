/*
Rotate Image

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/		

/**
 * 
 * Matrix problem
 * 
 * The tricky part is don't be confused with indexing.
 * 
 * We will rotate the matrix layer by layer.
 * To save space, we will actually rotate the matrix cell by cell. 
 * We observed that cell in top row, say index start + i, will be replaced by cell in left column, end - i. Notice: i here should be 
 * pure increment, which means if we got index like j, and this layer start from 2, then the pure increment is i = j - 2
 * the cell end - i in left column will be replaced by cell end - i in bottom
 * the cell end - i in bottom will be replaced by cell start + i in right
 * Finally cell start + i in right will be replaced by cell start + i in top.
 * So its like a big swap involved four cells, we just need to use a temp variable to record one cell, then we can just swap
 * them like general swap
 * 
 * Remark:
 * Better draw a 4*4 matrix on paper, and look at the swap of index 1 for each row/col
 *  
 * sol1 is a general solution use 4 swaps, rotate image cell by cell.
 * sol2 uses a mathematical solution that rotate the matrix part by part
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 11:34:02 PM
 */
public class Rotate_Image_p48_sol1 {
    public void rotate(int[][] matrix) {
        //we will rotate the matrix layer by layer
        //we totally have n/2 layers
        
        for(int i = 0; i < matrix.length/2; i++){
            int start = i;//start of layer
            int end = matrix.length -1 - i;//end of layer
            
            //Since we got overlap for boundary cell, we only need to do len -1 swaps for each layer
            for(int j = start; j < end; j++){
                int offset = j - start;//j is increment based on start, we substract start to get real increment, remember this!!!!!
                
                int top = matrix[start][j];
                
                //left to top
                matrix[start][j] = matrix[end - offset][start];
                
                //bot to left
                matrix[end - offset][start] = matrix[end][end - offset];
                
                //right to bot
                matrix[end][end - offset] = matrix[j][end];
                
                //top to right
                matrix[j][end] = top;
                
            }
        }
    }
}
