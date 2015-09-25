import java.util.*;
/**
 * String problem. I think it can also be treated as a two-pointer problem.
 * 
 * The difficulty part is to come up with the idea of using step + bound to guide the traversal of the matrix
 * 
 * Since the final string is the combination of each row, we can just store each row as a string. By doing that, we don't need to know
 * the length of each row. We then use an array to store those strings, so that each cell in array represents a row.
 * We will fill the virtual matrix column by column, but since it follows ZigZag style, instead of using nested loop, we use two pointer 
 * to visit rows. One pointer points to the current row, and one pointer points to the boundary. If our two pointer meet, then we will 
 * let current row pointer begin move to another direction. This can be achieved by using an extra variable "step". So "step" will guide the 
 * current row pointer.
 * 
 * Remark:
 * 1) we need firstly fill the string[] with "", otherwise each row will start with null
 * 2) to avoid the case of out of boundary , we check the boundary in the beginning of loop, so that our current row pointer will always in range
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 5:17:23 PM
 */

public class ZigZag_Conversion_p6_sol1 {
	public static void main(String[] args){
		System.out.println(new ZigZag_Conversion_p6_sol1().convert("AB", 2));
	}
    public String convert(String s, int numRows) {
        //two corner cases, we put s in one row or in one column
        if(numRows <= 1 || numRows >= s.length()) return s;
        
        //we don't know the length of final matrix, but we know the height
        //so we can just build string array, in which each cell contains the row information wihtout need 
        //for the length
        String[] rows = new String[numRows];
        
        //need initialize the array, otherwise we would start with null value 
        Arrays.fill(rows, "");
        
        int row = 0;//the row that we are looking at
        int step = 1;//direction guider
        
        for(int i = 0; i < s.length(); i++){
            //better put it here to check current row value to avoid overflow
            if(row == 0) step = 1;
            if(row == numRows-1) step = -1;
            
            rows[row] += s.charAt(i);
            
            row += step;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < rows.length; i++){
            sb.append(rows[i]);
        }
        
        return sb.toString();
    }
}
