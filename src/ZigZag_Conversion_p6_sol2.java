/**
 * I would say this approach is more close to Math problem
 * 
 * The difficulty part is summarizing the equation and observing the corner case that the first and last row only has one gap
 * 
 * we observe that the shape of z can be treated as a square with 2 equilateral triangles.
 * so for the first two chars in two columns, their gap is actually 2 * height, where height = numRows. Actually, chars have 
 * same row index in two columns are always differed by 2 * height. 
 * o   o   o    o
 * o o o   o  o o
 * o   o   o o  o
 *  	   o    o	 
 * How about the char in diagonal? We found the char is actually just 2*h - 2*1 - 2*i far away from first column.
 * We need to convert length to index, so we subtract 2*1. Accordingly, the same char on diagonal is 2*i away from char in second column
 * So, by summarizing this equation, we can just start from the first char in each row, then insert all chars in the same row. We call
 * the gap between first col and diagonal as gap1, the gap between the diagonal with second col as gap2. We assume we always have 
 * those two gaps, then we can find next char based on the gap1 or gap2.
 * But we we won't have diagonal in first and last row, accordingly we would not insert gap2 in first row and gap1 in last row.
 * For other cases, we can just use this equation to insert chars
 * 
 * 
 * 
 * @author hpPlayer
 * @date Sep 24, 2015 7:24:52 PM
 */
public class ZigZag_Conversion_p6_sol2 {
    public String convert(String s, int numRows) {
        //we can put s in one row or one column
        if(numRows <= 1 || numRows > s.length()){
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        
        //looking at each row
        for(int i = 0; i < numRows; i++){
            //j is the pointer in each row
            int j = i;
            
            //to reach next char in same row, we have to go through two edges with same length, so we need *2 
            //the left gap is composed of 2* edge - 2 * i, but need convert from length to index, so we need -1
            //the inital gap is 2 * n 
            int gap1 = 2 * (numRows - 1 - i);
            
            //the right gap is composed of 2 * i
            int gap2 = 2 * i;
            
            //append the intial char
            sb.append(s.charAt(j));
            
            //we trying to insert all chars in same row
            while(j < s.length()){
                j += gap1;
                //In the boundary case, last row and first, our j + gap1 or j + gap2 may still within boundary
                //but they could not be attached as we don't have gap1 or gap2 in those boundary case
                if(i != numRows -1 && j < s.length()){
                    sb.append(s.charAt(j));
                }
                
                j += gap2;
                if(i != 0 && j < s.length()){
                    sb.append(s.charAt(j));
                }                
            }
        }
        
        return sb.toString();
    }
}
