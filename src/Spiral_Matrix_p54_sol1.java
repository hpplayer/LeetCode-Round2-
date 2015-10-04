import java.util.*;

/*
Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

/**
 * Matrix problem
 * 
 * The tricky part is to handle the boundary case properly
 * (like move from left->right in top row, we are moving forward, while right->left in bot row, we are moving backward)
 * Also be careful with indexing, like we are looking at right column, we actually are changing rows from top to bot, and right is fixed
 * as the col number
 * 
 * The basic idea is using 4 bars to indicate the range of next visit. Each inner loop will eliminate one column or one row
 * The stop condition is that we have visited all cells in matrix, which means our left > right or top > bot, i.e. we have 
 * visited all rows/columns. Since our matrix is spiral and the final matrix may be accessed by any direction, we have to 
 * check the stop condition after each inner loop
 * 
 * Time complexity is O(mn)
 * 
 * @author hpPlayer
 * @date Oct 3, 2015 5:43:27 PM
 */
		
public class Spiral_Matrix_p54_sol1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0) return result;
        
        int top = 0, bot = matrix.length - 1;//top-bot boundary 
        int left = 0, right = matrix[0].length - 1;//left-right boundary
        
        while(true){
            //we will break the loop, when we meet the last cell, which may end from each direction
            //so we will check the break condition after each inner loop
            
            //left->right on top row
            for(int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top ++;//we finish top row, top ++
            if(top > bot) return result;//we allow top == bottom, i.e. including each valid row
            
            //top->bot on right col
            for(int i = top; i <= bot; i++){
                result.add(matrix[i][right]);
            }
            right --;//we finish right col, right --
            if(right < left) return result;
            
            //right->left on bot row
            for(int i = right; i >= left; i--){
                result.add(matrix[bot][i]);
            }
            bot --;//we finish bot row, bot --
            if(bot < top) return result;            
            
            //bot->top on left col
            for(int i = bot; i >= top; i--){
                result.add(matrix[i][left]);
            }
            left ++;// we finish left col, left ++
            if(left > right) return result;
        }
    }
}
