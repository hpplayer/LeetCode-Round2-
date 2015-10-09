import java.util.Arrays;

/**
 * DP solution
 * 
 * To calculate an area of a rectangle, we need two parameters, height and width. In sol2, we will record height and calculate 
 * width by record left and right boundary. So basically, we will scan the input matrix row by row, and calculate the rectangle 
 * area end at this row. Differed from sol1, where we need an extra function to calculate function, here we use (left, right) and 
 * height to directly calculate area on fly. In our dp approach, we use three arrays to record the historical information on 
 * these three parameters. For each rectangle ended at current row, its left boundary will be limited by max(left bound in prev row, 
 * left bound in current row), its right boundary will be limited by min(right bound in prev row, right bound in current row), its
 * height can be easily got by decide whether to extend height from prev row or just reset to 0. We use two pointers "curr_left" and 
 * "curr_right" to find each rectangle's left and right boundary. If current cell's value is 0, then the left boundary will be in later
 * cells, while the right boundary must be in prev cells. So, we simply move pointers and reset values in dp[] to prepare for a new 
 * start, if current matrix value is 0. If current matrix value is 1, then we at least have a rectangle of area 1 ended at this
 * cell, we will decide its real left/right boundary by looking up left[], right[] and pointers.
 * 
 * 
 * Remark:
 * 
 * 1) I can combine three loops into one (left, right, height), but I prefer leave it as it is to make the code more understandable
 * 2) The similar idea that scan the array twice (forward and backward) to decide the range can also be found in problem Candy (p135)
 * and problem Trapping rain water(p42)
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 12:16:05 AM
 */
public class Maximal_Rectangle_p85_sol2 {
    public int maximalRectangle(char[][] matrix) {
        //boundary case
        if(matrix.length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        //three dp arrays for three parameters to calculate rectangle area
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        
        Arrays.fill(right, n - 1);//we fill the initial value for right
        int result = 0;
        
        //we will scan each row by row to find the max rectangle
        for(int i = 0; i < m; i++){
            //curr_left is left boundary in current row
            //curr_right is right boundary in current row
            int curr_left = 0, curr_right = n - 1;
            
            for(int j= 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    //we can extend height
                    height[j] ++;
                }else{
                    //we can't extend and reset heigth[j]
                    height[j] = 0;
                }
            }
            
            for(int j= 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    //update left boundary for current cell, for a rectangle end at this cell
                    //the left boundary is determined by the max(left boundary from previous row left[j],
                    //left boundary from current row currLeft);
                    left[j] = Math.max(left[j], curr_left);
                }else{
                    //we can't extend and reset left[j] to prepare for a new start
                    //don't be afraid we will include this in calculation, since the height is 0
                    //we sill get 0 area in this index when calculating current row
                    left[j] = 0;
                    curr_left = j + 1;//left boundary can only after index j
                }
            }           
            
            for(int j = n - 1; j >= 0; j--){
                if(matrix[i][j] == '1'){
                    //update right boundary for current cell, for a rectangle end at this cell
                    //the right boundary is determined by the min(right boundary from previous row right[j],
                    //right boundary from current row curr_right);
                    right[j] = Math.min(right[j], curr_right);
                }else{
                    //we can't extend and reset right[j] to prepare for a new start
                    right[j] = n - 1;
                    curr_right = j - 1;//right boundary can only before index j
                }
            }               
            
            //scan each rectangle end at current row, calculate their area based on left,right and height
            //update result if necessary
            for(int j = 0; j < n; j++){
                //since index in left and right are 0 based, to convert them to 1 based length, we have to add 1
                //like [0,1], 1 - 0 + 1 = 2 is the correct length for this array
                result = Math.max(result, height[j] * (right[j] - left[j] + 1));
            }
            
        }
        
        return result;
    }
}
