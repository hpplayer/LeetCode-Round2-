import java.util.*;
/*
Pascal's Triangle II

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

/**
 * List problem
 * 
 * The tricky part is to be clear with the logic, and come up with the idea of using list.set(). Also we need to find that the size of each level
 * is just the row index + 1
 * 
 * If we draw and format the Pascal's Triangle, we will find the first 1 is always fixed, we need add a new tail 1 for each row.
 * For other nums in each row, the new num is from the sum of old num above it with the num on the left of the old num above it.
 * If we don't want use extra space, we can repeatedly use same list but updating the list backward.
 * Also to control the space size with O(k), we will use set() to update the list
 * 
 * Remark:
 * Remember to skip the fixed 1 in head (i.e. update row from index i - 1 to 0 (exclusive) )!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Oct 27, 2015 2:47:34 AM
 */
public class Pascals_Triangle_p119_sol1 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        
        //update row by row
        for(int i = 0; i <= rowIndex; i++){
            //first 1 is fixed, so we need add an extra 1 in the tail for each row
            result.add(1);
            
            //we will update list backward, so we can use old left value 
            //each row will exactly have row index + 1 value.
            //The last value before tail 1 is in index i - 1
            
            //we will skip the first value which is fixed 1
            for(int j = i - 1; j > 0; j--){
                //use list.set() to update value at target index
                result.set(j, result.get(j) + result.get(j-1));
            }
        }
        
        return result;
    }
}
